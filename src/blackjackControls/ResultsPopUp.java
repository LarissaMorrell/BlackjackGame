package blackjackControls;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ResultsPopUp extends JFrame {
	
	JFrame popUp = new JFrame("");
	JPanel winLosePanel = new JPanel();
	ImageIcon image;
	JLabel imageLbl = new JLabel(image);
	JLabel roundResult = new JLabel("");
	
	public ResultsPopUp(){
		
		popUp.setSize(500, 325);
		popUp.getContentPane().add(winLosePanel);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		popUp.setLocation(dim.width/2-popUp.getSize().width/2, dim.height/2-popUp.getSize().height/2);
		
		popUp.setVisible(false);
	}
	
	
	
	
	public void winPopUp(String winStatement){
		
		roundResult.setText(winStatement);
		roundResult.setBorder(new EmptyBorder(15, 0, 5, 0));
		winLosePanel.add(roundResult);
		
		image = new ImageIcon(getClass().getResource("win.png"));
		imageLbl = new JLabel (image);
		winLosePanel.add(imageLbl);
		
		popUp.setAlwaysOnTop(true);
		popUp.setVisible(true);
	}
	
	public void losePopUp(String loseStatement){
		roundResult.setText(loseStatement);
		roundResult.setBorder(new EmptyBorder(15, 0, 0, 0));
		winLosePanel.add(roundResult);
		
		image = new ImageIcon(getClass().getResource("youLose.png"));
		imageLbl = new JLabel (image);
		winLosePanel.add(imageLbl);
		
		popUp.setAlwaysOnTop(true);
		popUp.setVisible(true);
	}
	
	public void bustedPopUp(String bustedStatement){
		popUp.setSize(475, 300);
		roundResult.setBorder(new EmptyBorder(15, 0, 15, 0));
		
		roundResult.setText(bustedStatement);
		winLosePanel.add(roundResult);
		
		image = new ImageIcon(getClass().getResource("busted.gif"));
		imageLbl = new JLabel (image);
		winLosePanel.add(imageLbl);
		
		popUp.setAlwaysOnTop(true);
		popUp.setVisible(true);
	}

}
