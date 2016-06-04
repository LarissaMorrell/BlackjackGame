package blackjackControls;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class ValidateInput {
	int errorCount = 1;
	
	
	/**
	 * This method will check that a string only contains integers
	 */
	
	public int intInput(String input){
		JFrame frame = new JFrame();
		
		try {
			int num = Integer.parseInt(input);
			errorCount = 1;  //reset to 1 for next validation
			return num;
		} catch (NumberFormatException e) {
			if(errorCount == 3){
				JOptionPane.showMessageDialog(frame,  "PROGRAM EXIT: Invalid Input", 
						"ERROR: Exit Program", JOptionPane.ERROR_MESSAGE);
				System.exit(-1);
			}
			JOptionPane.showMessageDialog(frame,  "WARNING: You must enter a whole number", 
					"ERROR", JOptionPane.WARNING_MESSAGE);
			errorCount++;
			return -1;	
		}
	}
	
	
	
	
	
	
	/**
	 * This method will check that a string only contains integers
	 */
	
	public int intPosInput(String input){
		JFrame frame = new JFrame();
		
		try {
			int num = Integer.parseInt(input);
			if(num <= 0){
				throw new NumberFormatException();
			}
				
			errorCount = 1;  //reset to 1 for next validation
			return num;
		} catch (NumberFormatException e) {
			if(errorCount == 3){
				JOptionPane.showMessageDialog(frame,  "PROGRAM EXIT: Invalid Input", 
						"ERROR: Exit Program", JOptionPane.ERROR_MESSAGE);
				System.exit(-1);
			}
			JOptionPane.showMessageDialog(frame,  "WARNING: You must enter a whole number greater than zero", 
					"ERROR", JOptionPane.WARNING_MESSAGE);
			errorCount++;
			return -1;	
		}
	}

	
	
	
	
	
	/**
	 * This method will produce a popUp if the user puts anything 
	 * other than letters and a space. 
	 */
	
	public boolean stringInput(String input) {
		try{									//name can only have letters and spaces
			char[] chars = input.toCharArray();
			if(chars.length == 0){				//Require the user to enter a character
				throw new NonLetterException();
			}
			for (char c: chars){
				if(!Character.isLetter(c) && c != ' '){  
					throw new NonLetterException();
				}
				if(c == ' ' && chars.length == 1){	//error message if only input is whitespace
					throw new NonLetterException();
				}
			}
			errorCount = 0;
			return true;				
			
		}catch (NonLetterException e){
			JFrame frame = new JFrame();
			if(errorCount == 3){
				JOptionPane.showMessageDialog(frame,  "PROGRAM EXIT: Invalid Input", 
						"ERROR: Exit Program", JOptionPane.ERROR_MESSAGE);
				System.exit(-1);
			}
			JOptionPane.showMessageDialog(frame,  "WARNING: Only letters may be entered", 
					"ERROR", JOptionPane.WARNING_MESSAGE);
			errorCount++;
			return false;
		}			
	}
	
	
	
	
	
	/**
	 * This method will search a string for chars contained in another String
	 * Checks to see if input is matches one letter and of a range of desired letters
	 */
	public boolean containDesiredLetter(String input, String lookFor){
		try{									
			if(input.length() != 1)
				throw new InvalidInputException();
			char[] chars = lookFor.toCharArray();

			for(char c: chars){
				if(c == input.charAt(0)){
					return true;
				}
			}
			throw new InvalidInputException();  //if the char is not == then exception thrown
			
		} catch(InvalidInputException e){
			JFrame frame = new JFrame();
			if(errorCount == 3){
				JOptionPane.showMessageDialog(frame,  "PROGRAM EXIT: Invalid Input", 
						"ERROR: Exit Program", JOptionPane.ERROR_MESSAGE);
				System.exit(-1);
			}
			JOptionPane.showMessageDialog(frame,  "WARNING: Invalid Input", 
					"ERROR", JOptionPane.WARNING_MESSAGE);
			errorCount++;
			return false;
		}
	}
	
	
	public class NonLetterException extends Exception {	
		public NonLetterException() { super(); }
		public NonLetterException(String message) { super(message); }
		public NonLetterException(String message, Throwable cause) { super(message, cause); }
		public NonLetterException(Throwable cause) { super(cause); }
	}
	
	public class InvalidInputException extends Exception {
		public InvalidInputException() { super(); }
		public InvalidInputException(String message) { super(message); }
		public InvalidInputException(String message, Throwable cause) { super(message, cause); }
		public InvalidInputException(Throwable cause) { super(cause); }
	}

}
