import java.util.*;

class Jack{
  public Deck game = new Deck();
  public Deck hand = new Deck();
  public Deck dHand = new Deck();
  public int account = 10;
  public int bet = 0;
  public int playerS = 0;
  public int dealerS = 0;
  public int aceHold = 0;
  public int checkForFakeStand = 0;
  
  public Scanner kbreader = new Scanner(System.in);
  
  public Jack(){
    game.shuffle();
    convert();
    hand.clear();
    dHand.clear();
    
  }

  public void bjPlay(){
    boolean run = false;
    System.out.println("Welcome to Black Jack! You start with 10 chips in your account. Press enter to start!");
    if(kbreader.nextLine().equals("")){
      run = true;
    }

    while(run == true){


      System.out.println("How much would you like to bet? (type '-1' to stop playing)");
      bet = kbreader.nextInt();
      if(bet == -1){
        run = false;
        continue;
      }
      if(bet > account){
        System.out.println("Sorry you can not bet that much! You currently have " + account + " chips remaining");
        continue;
      }
      
      
      hand.uDeck.add(game.flipRem(0));
      dHand.uDeck.add(game.flipRem(0));
      hand.uDeck.add(game.flipRem(0));
      dHand.uDeck.add(game.flipRem(0));

      System.out.println("Your two cards are the "  + hand.uDeck.get(0).name() + " and the " + hand.uDeck.get(1).name());
      System.out.println("And the Dealer has a " + dHand.uDeck.get(0).name());

      System.out.println("Please type '1' to stand, '2' to hit, '3' to double down, or '4' to split ");
      int choice = kbreader.nextInt();
      
      switch(choice){
        case 1:
          stand();
          break;
        case 2:
          hit();
          break;
        case 3:
          ddown();
          break;
        case 4:
          split();
          break;
        default:
          System.out.println("I am sorry " + choice + " is not an option");
          continue;
      } 
      
    }
  }
  public void convert(){
    for(int i = 0; i < game.uDeck.size(); i++){
      if(game.uDeck.get(i).type.equals("King") || game.uDeck.get(i).type.equals("Queen") || game.uDeck.get(i).type.equals("Jack")){
        game.uDeck.get(i).nType = 10;
      }else if(game.uDeck.get(i).type.equals("Ace")){
        game.uDeck.get(i).nType = -2;
      }else{
        game.uDeck.get(i).nType = game.uDeck.get(i).nType + 1;
      }
    }
  }

  public void stand(){
    aceHold = 0;
    playerS = 0;
    dealerS = 0;
    
    //checks the player
    for(int i = 0; i < hand.uDeck.size(); i++){
      if(hand.uDeck.get(i).type.equals("Ace")){
        System.out.println("Would you like your ace to count for 1 or 11");
        hand.uDeck.get(i).nType = kbreader.nextInt();
      }
      playerS = playerS + hand.uDeck.get(i).nType;
      //System.out.println("Player Score " + playerS);
      if(playerS > 21){
        playerS = playerS * -1;
      }
    }
    
    //checks the dealer
    for(int i = 0; i < dHand.uDeck.size(); i++){
      //pick till a score of 17, and use score to determine if an ace is 1 or 11
      aceCheck();
      dealerS = dealerS + dHand.uDeck.get(i).nType;
    }
    System.out.println("First draw score " + dealerS);
    
    checkDealer();
    
    if(checkForFakeStand >= 0){
      checkFWin();
    }
  }  
  
  public void aceCheck(){
    if(dHand.uDeck.get(0).type.equals("Ace")){
      aceHold++;
      dHand.uDeck.get(0).nType = 11;
    }
  }
  
  public void reset(){
    dHand.uDeck.clear();
    hand.uDeck.clear();
  }
  
  public void hit(){
    checkForFakeStand = -1;
    stand();
    hand.uDeck.add(0, game.flipRem(0));
    System.out.println("You hit and got a " + hand.uDeck.get(0).name());
    
    if(hand.uDeck.get(0).nType == -2){
        System.out.println("Would you like your ace to count for 1 or 11");
        hand.uDeck.get(0).nType = kbreader.nextInt();
      }
    playerS = playerS + hand.uDeck.get(0).nType;
    System.out.println("Your new score is " + playerS);
    checkForFakeStand = 0;
    if(playerS > 21){
      playerS = playerS * -1;
      checkFWin();
    }else{
    
      System.out.println("Would you like to hit again? ('1' for yes, and '0' for no");
      int cchoice = kbreader.nextInt();
      if(cchoice == 1){
        System.out.println("Your current score after hitting is " + playerS);
        hit();
      }else if(cchoice != 1){
        System.out.println("Your current score after hitting is " + playerS);
        if(playerS > 21){
          playerS = playerS * -1;
        }
        checkFWin(); 
      }
    }
  }
  
  public void checkFWin(){
    if(dealerS < 0 && aceHold > 0){
      for(int i = 0; i < dHand.uDeck.size(); i++){
        if(dHand.uDeck.get(i).nType == 11){
          dHand.uDeck.get(i).nType = 1;
        }
      }
      checkDealer();
    }
    
    if(playerS < 0){
      System.out.println("You went over 21, and you lost! You bet " + bet + ". So you lost " + bet + ". You have " + (account-bet) + " tokens left!");
      account = account - bet;
    }else if(dealerS > 0 && (playerS < dealerS)){
      System.out.println("You lost to the dealer. The dealer had a score of" + dealerS + " You bet " + bet + ". So you lost " + bet + ". You have " + (account-bet) + " tokens left!");
      account = account - bet;
    }else if(playerS > dealerS){
      System.out.println("\n"+playerS + "\n\tVS." + "\n" + dealerS + "\nYou Won! You won " + bet + " tokens.");
      account = account + bet;
    }else if(dealerS == playerS){
      System.out.println("You and the dealer tied with points, so you get your bet back! You still have " + account + " points left!");
    }
    reset();
  }
  
  public void ddown(){
    
  }
  
  public void split(){
    
  }

  public void checkDealer(){
    if(dealerS < 17){
      dHand.uDeck.add(0, game.flipRem(0));
      //System.out.println(dHand.uDeck.get(0).name());
      aceCheck();
      dealerS = dealerS + dHand.uDeck.get(0).nType;
      //System.out.println(dealerS);

      if(dealerS > 21){
        dealerS = dealerS * -1;
      }else if(dealerS < 17){
        dHand.uDeck.add(0, game.flipRem(0));
        //System.out.println(dHand.uDeck.get(0).name());
        aceCheck();
        dealerS = dealerS + dHand.uDeck.get(0).nType;
        //System.out.println(dealerS);
        
        if(dealerS > 21){
          dealerS = dealerS * -1;
        }else if(dealerS < 17){
          dHand.uDeck.add(0, game.flipRem(0));
          //System.out.println(dHand.uDeck.get(0).name());
          aceCheck();
          dealerS = dealerS + dHand.uDeck.get(0).nType;
          //System.out.println(dealerS);
        }
      }
    }
  }
}