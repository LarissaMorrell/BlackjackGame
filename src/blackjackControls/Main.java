package blackjackControls;

public class Main {
	
	public static final void main (String[] args){
		GameConsole game = new GameConsole();
		BackPanel window = new BackPanel(game);
		
		game.gamePlay(window);
		
		
		
	}

}
