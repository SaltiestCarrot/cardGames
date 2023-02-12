class Card{
  public int nType;
  public int nSuit;
  public String type;
  public String suit;
  
  public Card(int nt, int ns){
    nType = nt;
    nSuit = ns;

    //makes the type of the card
    switch(nType){
      case 0:
        type = "Ace";
        nType = 13;
        break;
      case 10:
        type = "Jack";
        break;
      case 11:
        type = "Queen";
        break;
      case 12:
        type = "King";
        break;
      default:
        type = (nType + 1) + "";
    }
    
    //makes the suit of the card
    switch(nSuit){
      case 0:
        suit = "Hearts";
        break;
      case 1:
        suit = "Diamonds";
        break;
      case 2:
        suit = "Spades";
        break;
      case 3:
        suit = "Clubs";
        break;
      default:
        suit = null;
    }
  }

  public String name(){
    String name = type + " of " + suit;
    return name;
  }
    

  
}