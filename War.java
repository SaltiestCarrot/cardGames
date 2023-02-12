import java.util.*;

class War{
  public Deck warDeck = new Deck();
  public Deck opponentW = new Deck();

  public Deck warDeckDiscard = new Deck();
  public Deck opponentWDiscard = new Deck();
  
  public War(){
    warDeck.shuffle();
    opponentW.uDeck.clear();
    for(int i = 0; i < warDeck.uDeck.size(); i++){
      opponentW.uDeck.add(warDeck.uDeck.remove(i));
    }

    warDeck.shuffle();
    opponentW.shuffle();

    warDeckDiscard.uDeck.clear();
    opponentWDiscard.uDeck.clear();
  }

  public void wPlay(){
    boolean running = true;
    Scanner kbreader = new Scanner(System.in);
    Card currentWC = new Card(0, 0);
    Card currentOC = new Card(0, 0);
    int wDiscardSize = 0;
    int flipAmount = 0;

    System.out.println("Welcome to War! Press 'Enter' to play");
    
    while(running == true){

      // win/lose detection
      if((warDeck.uDeck.size() + warDeckDiscard.uDeck.size()) == 0){
        running = false;
        System.out.println("You lost :(");
        break;
      }
      if(opponentW.uDeck.size() + opponentWDiscard.uDeck.size() == 0){
        running = false;
        System.out.println("You WON :)");
        break;
      }

      //checks for each 'Enter'
      if(kbreader.nextLine() == ""){

        //resets the deck once you get out of cards
        if(warDeck.uDeck.size() == 0){
          resetDeck(warDeck, warDeckDiscard, wDiscardSize);
        }
        if(opponentW.uDeck.size() == 0){
          resetDeck(opponentW, opponentWDiscard, wDiscardSize);
        }

        //Checks to see if you are out of cards, and catches an error if you are
        try{  
          currentWC = warDeck.flip(0);
          currentOC = opponentW.flip(0);
        }catch(Exception e){
          System.out.println("Why are you all the way down here");
        }

        
        System.out.println("You flipped a " + currentWC.name());
        System.out.println("Your opponent flipped a " + currentOC.name());
      }

      //if WC is better then OC
      if(currentWC.nType > currentOC.nType){
        warDeckDiscard.uDeck.add(0, warDeck.uDeck.remove(0));
        warDeckDiscard.uDeck.add(0, opponentW.uDeck.remove(0));

        System.out.println("Deck size: " + warDeck.uDeck.size());
        System.out.println("Opponent deck Size: " + opponentW.uDeck.size());

        System.out.println("Discard size: " + warDeckDiscard.uDeck.size());
        System.out.println("Opponent discard size: " + opponentWDiscard.uDeck.size());
      }
      //if OC is better then WC
      if(currentOC.nType > currentWC.nType){
        opponentWDiscard.uDeck.add(0, opponentW.uDeck.remove(0));
        opponentWDiscard.uDeck.add(0, warDeck.uDeck.remove(0));
        
        System.out.println("Deck size: " + warDeck.uDeck.size());
        System.out.println("Opponent deck Size: " + opponentW.uDeck.size());
        
        System.out.println("Discard size: " + warDeckDiscard.uDeck.size());
        System.out.println("Opponent discard size: " + opponentWDiscard.uDeck.size());
     }

      
      //This is the code if a war is initiated
      if(currentOC.nType == currentWC.nType){
    
        if(warDeck.uDeck.size() < 4 || opponentW.uDeck.size() < 4 && (Math.min(warDeck.uDeck.size(), opponentW.uDeck.size()) > 1)){
          flipAmount = Math.min(warDeck.uDeck.size(), opponentW.uDeck.size());
        }else if(Math.min(warDeck.uDeck.size(), opponentW.uDeck.size()) <= 1){
            if(warDeck.uDeck.size() <= 1){
              resetDeck(warDeck, warDeckDiscard, wDiscardSize);
            }
            if(opponentW.uDeck.size() <= 1){
              resetDeck(opponentW, opponentWDiscard, wDiscardSize);
            }
            
            if((warDeck.uDeck.size() < 4 || opponentW.uDeck.size() < 4)){
              flipAmount = Math.min(warDeck.uDeck.size(), opponentW.uDeck.size());
            }else{
              flipAmount = 4;
            }
        }else{
          flipAmount = 4;
        }
        
        currentWC = warDeck.flip(flipAmount - 1);
        currentOC = opponentW.flip(flipAmount - 1);
        
        
        //For a War inside of a war
        if(currentOC.nType == currentWC.nType){
          System.out.println("War Inside of a war!");
          
          if((warDeck.uDeck.size() < 8 || opponentW.uDeck.size() < 8) && (Math.min(warDeck.uDeck.size(), opponentW.uDeck.size()) > 2)){
            flipAmount = Math.min(warDeck.uDeck.size(), opponentW.uDeck.size());
            System.out.println("test for inside of small double war" + flipAmount);
          }else if(Math.min(warDeck.uDeck.size(), opponentW.uDeck.size()) <= 2){
            if(warDeck.uDeck.size() <= 2){
              resetDeck(warDeck, warDeckDiscard, wDiscardSize);
            }
            if(opponentW.uDeck.size() <= 2){
              resetDeck(opponentW, opponentWDiscard, wDiscardSize);
            }
            
            if((warDeck.uDeck.size() < 8 || opponentW.uDeck.size() < 8)){
              flipAmount = Math.min(warDeck.uDeck.size(), opponentW.uDeck.size());
            }else{
              flipAmount = 8;
            }
          }else{
            flipAmount = 8;
          }
        
          currentWC = warDeck.flip(flipAmount - 1);
          currentOC = opponentW.flip(flipAmount - 1);
          
          cardsMatch(currentWC, currentOC, flipAmount);
          continue;
        }

        cardsMatch(currentWC, currentOC, flipAmount);
      }

    }
  }

  public void resetDeck(Deck currentDeck, Deck CDDeck, int dSize){
    dSize = CDDeck.uDeck.size();
      for(int i = 0; i < dSize; i++){
        currentDeck.uDeck.add(0, CDDeck.uDeck.remove(0));
      }
      System.out.println(currentDeck.uDeck.size());
      currentDeck.shuffle();
  }

  
  public void cardsMatch(Card currentWC, Card currentOC, int flipAmount){
    if(currentWC.nType > currentOC.nType){
          for(int i = 0; i < flipAmount; i++){
            System.out.println(warDeck.uDeck.get(0).name());
            System.out.println(opponentW.uDeck.get(0).name());

            warDeckDiscard.uDeck.add(0, warDeck.uDeck.remove(0));
            warDeckDiscard.uDeck.add(0, opponentW.uDeck.remove(0));
          }

          System.out.println("You won the war, and won: " + flipAmount + " cards!");
      
          System.out.println("Deck size: " + warDeck.uDeck.size());
          System.out.println("Opponent deck Size: " + opponentW.uDeck.size());
        
          System.out.println("Discard size: " + warDeckDiscard.uDeck.size());
          System.out.println("Opponent discard size: " + opponentWDiscard.uDeck.size());
        }
      if(currentOC.nType > currentWC.nType){
          
        for(int i = 0; i < flipAmount; i++){
          System.out.println(warDeck.uDeck.get(0).name());
          System.out.println(opponentW.uDeck.get(0).name());

          opponentWDiscard.uDeck.add(0, warDeck.uDeck.remove(0));
          opponentWDiscard.uDeck.add(0, opponentW.uDeck.remove(0));
            
        }

        System.out.println("You lost the war, and lost: " + flipAmount + " cards!");
        
        System.out.println("Deck size: " + warDeck.uDeck.size());
        System.out.println("Opponent deck Size: " + opponentW.uDeck.size());
        
        System.out.println("Discard size: " + warDeckDiscard.uDeck.size());
        System.out.println("Opponent discard size: " + opponentWDiscard.uDeck.size());
      }
  }
  
}