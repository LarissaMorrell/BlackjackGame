package blackjackControls;

import java.util.ArrayList;
import java.util.Collections;


public class Deck {
	private ArrayList<Card> cardDeck = new ArrayList<Card>();
	
	public Deck(int numberDecks){		//constructor builds a shuffled deck

		for(int d = 0; d < numberDecks; d++){  //for each deck
			for(int s = 0; s < 4; s++){		//for each suit			
				for(int r = 1; r <= 13; r++){  //for each rank, start at 1 - overstep place holder
					
					Card card = new Card(Suit.values()[s], r);
					cardDeck.add(card);					
				}
			}
			Collections.shuffle(cardDeck);
		}
		//displayDeck();
	}
	
	public void displayDeck() {
		for(int i = 0; i < cardDeck.size(); i++){
			System.out.println(cardDeck.get(i));}
	}
	public ArrayList<Card> getDeck(){
		return cardDeck;
	}
	
	public Card getTopCard(){			//deal a card
		Card c = cardDeck.get(0);
		//System.out.println(c);
		cardDeck.remove(0);
		
		return c;
	}
	
	public boolean deckEmpty(){
		if(cardDeck.size() == 0)
			return true;
		else
			return false;
	}
	

}
