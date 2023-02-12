import java.util.*;
class Main {
  public static void main(String[] args) {

    //Makes the deck    
    /*Deck bDeck = new Deck();
    //used to test to see if the deck fo cards was in order
    for(int i = 0; i < 52; i++){
      System.out.println(bDeck.uDeck.get(i).name());
    }*/


    //uncomment to play War!
    //War playW = new War();
    //playW.wPlay();

    //jSwing
    jSwing startUp = new jSwing();

    //BlackJack
    Jack playBJ =  new Jack();
    playBJ.bjPlay();

    
    
    
    /*bDeck.shuffle();
    //used to test to see if the shuffle works
    for(int i = 0; i < 52; i++){
      System.out.println(bDeck.uDeck.get(i).name());
    }*/
    
  }
}