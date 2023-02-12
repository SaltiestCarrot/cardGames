import java.util.*;

class Deck{
  public ArrayList<Card> uDeck = new ArrayList<Card>();
  public int count = 0;
  public Deck(){
    for(int i = 0; i < 13; i++){
      for(int j = 0; j < 4; j++){
        uDeck.add(count, new Card(i, j));
        count++;
      }
    }
  }
  
  public void shuffle(){
    ArrayList<Card> shufDeck = new ArrayList<Card>();
    ArrayList<Card> usedUp = new ArrayList<Card>();

    //makes usedUp a copy of uDeck becuase usedUp = uDeck makes usedUp a pointer to uDeck and we dont want that
    for(int j = 0; j < uDeck.size(); j++){
      usedUp.add(j, uDeck.get(j));
    }
    
    for(int i = 0; i < uDeck.size(); i++){
      shufDeck.add(usedUp.remove((int)(Math.random() * usedUp.size())));     
    }
    uDeck = shufDeck;
  }
  //flips a card
  public Card flip(int place){
    Card useMe = uDeck.get(place);
    //System.out.println("You flipped " + useMe.name());
    return useMe;
  }
  //flips a card and removes it
  public Card flipRem(int place){
    Card useMe = uDeck.remove(place);
    return useMe;
  }
  
  //clears a deck without having to call the .uDeck in the code
  public void clear(){
    uDeck.clear();
  }
  
}