package blackjackControls;

public class Card{
	
	private Suit cardSuit;
	private int rank;
	
	public Card(Suit cardSuit, int rank){
		this.cardSuit = cardSuit;
		this.rank = rank;
	}
	
	public String toString(){	   //place holder so index = cardVal
		String[] value = {"Place Holder", "Ace", "2", "3", "4", "5", "6",
						"7", "8", "9", "10", "Jack", "Queen", "King"};  
		
		return value[this.rank] + " of " + cardSuit;		
	}
	
	public int getRank(){
		return this.rank;
	}
	
	public Suit getSuit(){
		return this.cardSuit;
	}
	

}
