

package blackjackControls;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class BackPanel extends JFrame {
	
	
	public BackPanel(GameConsole game){
		
		//Set up GUI variables
		JFrame frame = new JFrame("Blackjack Game");
		JPanel panel = new JPanel();
		
		ImageIcon background;
		JLabel backgroundLbl;
		
		JButton newRoundButton;
		JButton walkAwayButton;
		
		ImageIcon image;
		
		
		final GameConsole game1 = game;
		frame.setSize(800, 625);
		
		frame.getContentPane().add(panel);   //add the panel to the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		//put panel in the middle of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		

		background = new ImageIcon(getClass().getResource("cardBackground.jpg"));
		backgroundLbl = new JLabel(background);
		backgroundLbl.setBorder(new EmptyBorder(0, 0, 10, 0));
		
//		backgroundLbl.setBounds(0, 0, 800, 600);
		panel.add(backgroundLbl);
		
		
		//create 2 buttons on the panel
		JButton nextRoundButton= new JButton("Start another round");
		nextRoundButton.setPreferredSize(new Dimension(150, 50));;
		nextRoundButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				game1.userTurn();
			}
		});
		
		walkAwayButton = new JButton("Walk Away");
		walkAwayButton.setPreferredSize(new Dimension(150, 50));
		walkAwayButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				game1.cashOut();
				System.exit(0);
			}
		});
		
		panel.add(walkAwayButton);
		panel.add(nextRoundButton);
		
		panel.setBackground(new ColorUIResource(203, 242, 222));
		
		frame.setVisible(true);
	}
}