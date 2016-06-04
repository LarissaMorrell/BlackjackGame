package blackjackControls;

public enum Suit {
	
	HEARTS,
	DIAMONDS,
	CLUBS,
	SPADES;
	
	
	
	public String toString() {
		switch(this){
			case HEARTS: return "Hearts";
			case DIAMONDS: return "Diamonds";
			case CLUBS: return "Clubs";
			case SPADES: return "Spades";
			default: throw new IllegalArgumentException();
		}
	}

}
