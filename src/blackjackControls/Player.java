package blackjackControls;

import java.util.ArrayList;

public class Player {

	private String userName;
	private ArrayList<Card> hand = new ArrayList<Card>();
	private int money;
	private int bet;
	private int dealerHitCount = 0;
	
	public Player(String name){			//store the name of the player 
		this.userName = name;			//and construct an empty hand		
	}
	
	public void setMoney(int money){
		this.money = money;
	}
	
	public int getMoney(){
		return money;
	}
	
	public void setBet(int bet){
		this.bet = bet;
	}
	
	public int getBet(){
		return bet;
	}
	
	public void addToHand(Card newCard){ 		//adds a card to the player's hand
//		System.out.println(newCard);
		hand.add(newCard);
	}
	
	public Card getTopCard(){
		return hand.get(0);
	}
	
	public Card getLastCard(){
		return hand.get(hand.size() - 1);
	}
	
	public int getDealerHit(){
		return dealerHitCount;
	}
	
	public void emptyHand(){
		hand.clear();
	}
	
	
	
	
	/**
	 * This method determines if the hand is a soft handValue
	 * @return
	 */
	
	public boolean handContainsAce(){

		for(int a = 0; a < hand.size(); a++){
			if(1 == (hand.get(a)).getRank()){
				return true;    	  //There is an Ace
			}
		}
		
		return false; //there are no Aces
	}
	
	
	
	/** * * * * * * * * * * * * * * * * * * * * * *
	 * Find the total value in the hand
	 * @return
	 * * * * * * * * * * * * * * * * * * * * * * */
	
	public int sumOfHand(){			//will determine the total value of the hand
		int sum = 0;
		int aces = 0;
		int cardVal = 0;
		
		for (int a = 0; a < hand.size(); a++){
			cardVal = (hand.get(a)).getRank();		
			
			if(cardVal == 1){  	//ACES will store as 11 to start
				sum += 11;		//then if sum > 10 it will revert back to 1 
				aces++;
			} else if (cardVal >= 10){	//a 10 or a face card is dealt
				sum += 10;
			} else {			//for all other cards, add the value
				sum += cardVal;
			}
			
			//if the ace val at 11 causes bust, revert to val of 1
			if (sum > 21 && aces > 0){	
				sum -= 10;
				aces--;
			}
		}
		return sum;
	}
	
	
	
	
	/** * * * * * * * * * * * * * * * * * * * * * *
	 * Print what the hand currently is holding
	 * * * * * * * * * * * * * * * * * * * * * * */
	
	public String printHand(){
		dealerHitCount++;
		String handString = "";

		for(Card card: hand){
			handString += card;
			handString += "\n";
		}
		
		handString += "\n" + userName + " is at ";
		handString += sumOfHand();
		
//		if(sumOfHand() > 21 && userName.equals("The dealer")){
//			System.out.println("The dealer has busted.");
//		}
		
		
		return handString;
		
	}
	
}
