/**
 * @author Larissa Morrell-Perkins
 * Copyright 2014, All rights reserved
 *
 */


package blackjackControls;


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;	
import javax.swing.plaf.ColorUIResource;

							
public class GameConsole {
	Player user;
	Player dealer;
	Deck playingDeck;
	ValidateInput validate = new ValidateInput();
	int numRound = 0;
	int bet;
	ImageIcon cardIcon = new ImageIcon(GameConsole.class.getResource("cardIcon.png"));
	
	public void gamePlay(BackPanel window){
		
		String name = "error";
		UIManager UI = new UIManager();
		UI.put("OptionPane.background", new ColorUIResource(203, 242, 222));
		UI.put("Panel.background",new ColorUIResource(203, 242, 222));
	
		try{     //try-catch will take all NullPointerExceptions and exit the program. 

			for(int x = 0; x < 1;){
				name = JOptionPane.showInputDialog(null, "Welcome. What is your name?", "Name", 
						JOptionPane.INFORMATION_MESSAGE);
				boolean isItName = validate.stringInput(name);
				if(isItName)
					x++;
			}
			
			
			
			user = new Player(name);   //assign the user a name from the String they inputed
			dealer = new Player("The dealer");
	
			
			int money;
			do{
				String moneyString = JOptionPane.showInputDialog(null, "How much money do you have to gamble?",
						             "Your Bet", JOptionPane.INFORMATION_MESSAGE);
				money = validate.intPosInput(moneyString);
				user.setMoney(money);
			} while(money < 0);
			
			
			int decks;
			do{
				String numDecks = JOptionPane.showInputDialog(null, "How many decks would you like to play with?",
						"Decks", JOptionPane.INFORMATION_MESSAGE); 
				decks = validate.intPosInput(numDecks);
		
			} while(decks < 0);
			playingDeck = new Deck(decks);     	

			userTurn();
	
			
		} catch (NullPointerException e){
			JOptionPane.showMessageDialog(null, "You have chosen to quit.\nThank you for playing Blackjack.", 
					"Quit Blackjack", JOptionPane.INFORMATION_MESSAGE, cardIcon);
			System.exit(0);
		}
	}
	
	
	
	
	/**
	 * 
	 */
	public void userTurn(){
		try{
			if(user.getMoney() == 0){
				JOptionPane.showMessageDialog(null, "You do not have any money left. You must walk away.", 
						"Insufficient Funds", JOptionPane.INFORMATION_MESSAGE, cardIcon);
				return;
			}
			
			numRound++;
			
			int invalidBetCount = 1;
			do{
				String betString = JOptionPane.showInputDialog(null, "What is your bet for Round " + numRound + 
						"?\nYou currently have $" + user.getMoney() + ".", "Your Bet", JOptionPane.INFORMATION_MESSAGE);
				bet = validate.intPosInput(betString);
				
				if(bet > user.getMoney()){			//bet can not be greater than the money
					if(invalidBetCount == 3){
						JOptionPane.showMessageDialog(null,  "PROGRAM EXIT: Invalid Bet", 
								"ERROR: Exit Program", JOptionPane.ERROR_MESSAGE, cardIcon);
						System.exit(-1);
					}
					invalidBetCount++;
					JOptionPane.showMessageDialog(null, "You can not place a bet higher than "
							+ "the amount of money you have.\nYou currently have $" + user.getMoney() + ".", 
							"Warning", JOptionPane.WARNING_MESSAGE);
				}
			} while(bet > user.getMoney() || bet <= 0);
			user.setBet(bet);
			
			user.emptyHand();      //empty the hands to start the round over
			dealer.emptyHand();
			
			/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
			 * Deal two cards to each player & then game play begins
			 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
			//playingDeck.displayDeck();
	
			user.addToHand(playingDeck.getTopCard());
			dealer.addToHand(playingDeck.getTopCard());
			user.addToHand(playingDeck.getTopCard());
			dealer.addToHand(playingDeck.getTopCard());
			
			
			boolean turn = true;
			do{
				
				JOptionPane.showMessageDialog(null, "You have bet $" + user.getBet() 
						+ "\n\nThe cards you were dealt are: \n\n" + user.printHand(), "Round " + numRound, 
						JOptionPane.INFORMATION_MESSAGE, cardIcon);
				
//				if(user.sumOfHand() == 21){  //no need to continue if user has 21
//					endRound();
//					return;      else if{...
				if (user.sumOfHand() > 21){
					endRound();
					return;
				}
				
				
				String HSInput = null;     //Hit or Stay prompt
				for(int x = 0; x < 1;){
				HSInput = JOptionPane.showInputDialog(null, "\nThe dealer is showing a " + dealer.getTopCard()
						+ "\n\nYou are currently at " + user.sumOfHand() 
						+ "\n\nWhat would you like to do?\nHit (H) or Stay (S)?", "Round " + numRound, 
						JOptionPane.INFORMATION_MESSAGE);
					if(validate.containDesiredLetter(HSInput, "HhSs")) //only accept h and s either caps
						x++;
				}
				
				
				if(HSInput.equalsIgnoreCase("H")){	//if the user wants to stay
					user.addToHand(playingDeck.getTopCard()); //deal new card then repeat loop
				} 
				else {
					turn = false;
					}
			} while (turn == true);
			
			dealerTurn();
			

		} catch(IndexOutOfBoundsException e){
			if(playingDeck.deckEmpty()){
				JOptionPane.showMessageDialog(null,  "You have run out of cards.\nYou must now cash out your winnings.",
						"ERROR: Exit Program", JOptionPane.ERROR_MESSAGE);
			} else{
				e.printStackTrace();;
			}
			
		}
		
	}
	
	
	
	
	/**
	 * This method iterates through the dealer's turn
	 */
	
	public void dealerTurn(){
		try{
		
		/*
		 * The user has chosen to stay, so the dealer show full hand, then hits if needed
		 */
		JOptionPane.showMessageDialog(null, "The dealer turns over his card. The dealer's hand is:\n" 
				+ dealer.printHand(), "Round " + numRound, JOptionPane.INFORMATION_MESSAGE, cardIcon);

		while((dealer.sumOfHand() == 17 && dealer.handContainsAce()) 
				|| (dealer.sumOfHand() < 17 && dealer.sumOfHand() < user.sumOfHand())){
			
			dealer.addToHand(playingDeck.getTopCard());
			JOptionPane.showMessageDialog(null, "The dealer hits and gets a\n" 
					+ dealer.getLastCard() + "\n\nThe dealer is at " + dealer.sumOfHand(),
					"Round " + numRound, JOptionPane.INFORMATION_MESSAGE, cardIcon);
		}
		endRound();
		} catch(IndexOutOfBoundsException e){
			if(playingDeck.deckEmpty()){
				JOptionPane.showMessageDialog(null,  "You have run out of cards.\nYou must now cash out your winnings.",
						"ERROR: Exit Program", JOptionPane.ERROR_MESSAGE);
			} else{
				e.printStackTrace();
			}
			
		}
	}

		
	
	
		/**
		 * This method compares the scores, and decides which popUp window will announce 
		 * whether or not the user wins or loses
		 */
	
	public void endRound(){
		ResultsPopUp popUp = new ResultsPopUp();
		
		int betBalance;
		//The user has BUSTED
		if(user.sumOfHand() > 21){
			betBalance = user.getMoney() - user.getBet();   //Subtract the bet amount from balance
			user.setMoney(betBalance);
			
			String bustedStatement = "You have drawn a ";
			bustedStatement += user.getLastCard().toString();
			bustedStatement += ". You have a ";
			bustedStatement += user.sumOfHand();
			bustedStatement += ". You now have $";
			bustedStatement += betBalance;
			bustedStatement += ".";
			
			popUp.bustedPopUp(bustedStatement);
			
			
			
			
		//The user won the hand
		}else if(user.sumOfHand() > dealer.sumOfHand() && user.sumOfHand() <= 21){
			betBalance = user.getMoney() + user.getBet();
			user.setMoney(betBalance);
			
			String balanceStatement = "You now have $";
			balanceStatement += betBalance;				//Add the bet to the balance	
			balanceStatement += ".";
			
			popUp.winPopUp("Congratulations. You have won this round of Blackjack. " + balanceStatement);
			
			
			
			
		//The user tied with the dealer... and dealer wins
		} else if(user.sumOfHand() == dealer.sumOfHand() && user.sumOfHand() <= 21){
			betBalance = user.getMoney() - user.getBet();     //Subtract the bet amount from balance
			user.setMoney(betBalance);				
			
			String tiedStatement = "You now have $";
			tiedStatement += betBalance;
			tiedStatement += ".";
			
			popUp.losePopUp("You have the same as the dealer. House wins. " + tiedStatement);
		
		
			
			
		//The dealer has BUSTED
		} else if(dealer.sumOfHand() > 21){
			betBalance = user.getMoney() + user.getBet();     //Add the bet amount from balance
			user.setMoney(betBalance);
			
			String balanceStatement = "You now have $";
			balanceStatement += betBalance;
			balanceStatement += ".";
			
			popUp.winPopUp("The dealer has busted. You win this round. " + balanceStatement);
		
			
			
			
		//The dealer won the hand
		} else{
			betBalance = user.getMoney() - user.getBet();
			user.setMoney(betBalance);
			
			String loseStatement = "The dealer's hand of ";
			loseStatement += dealer.sumOfHand();
			loseStatement += " is larger than your hand of ";
			loseStatement += user.sumOfHand();
			loseStatement += ". You now have $";
			loseStatement += betBalance;
			loseStatement += ".";
			
			popUp.losePopUp(loseStatement);
		}
	}
	
	public void cashOut(){
		JOptionPane.showMessageDialog(null, "You have chosen to cash out.\n"
				+ "You are walking away with $" + user.getMoney() + ".\n\nThank you for playing Blackjack.", "Goodbye", JOptionPane.INFORMATION_MESSAGE, cardIcon);
	}
}
