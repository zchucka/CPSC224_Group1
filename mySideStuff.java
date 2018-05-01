import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.*;

public class mySideStuff {
	static hand gameHand = new hand(6);
	static JPanel buttPanel = new JPanel();
	static JPanel firstPanel = new JPanel();
	static JPanel directionPanel = new JPanel();
	static JFrame frame = new JFrame("Button Play");
	static int playerNumber = 1;
	static int roundScore = 0;
	static JPanel playPanel = new JPanel();
	
	public static void main(String[] args) { 
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(550, 550);
		frame.setLayout(new GridBagLayout());
		
		// making score board
		JPanel scoreBoard = new JPanel();
		scoreBoard.setLayout(new BoxLayout(scoreBoard, BoxLayout.Y_AXIS));

		JLabel header = new JLabel("Current Scoreboard");
		JLabel divider = new JLabel("-----------------------------");
		scoreBoard.add(header);
		scoreBoard.add(divider);
		
		for (int k = 0; k < 4; k++)
		{
			JLabel temp = new JLabel("Player " + (k+1) + ": 2000");
			scoreBoard.add(temp);
		}
		
		
		// making player turn label
		firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.Y_AXIS));
		JLabel playerLabel = new JLabel("Player " + playerNumber +"'s Turn");
		playerLabel.setHorizontalAlignment(JLabel.LEFT);
		JLabel currentScore = new JLabel("Round Score = " + " 100");
		firstPanel.add(playerLabel, BorderLayout.NORTH);
		firstPanel.add(currentScore);
		
		buttPanel.setLayout(new BoxLayout(buttPanel, BoxLayout.LINE_AXIS));
		
		gameHand.roll();
		
		// creating the dice
		for (int k = 0; k < gameHand.numOfDice; k++)
		{
			JButton helper = new JButton("  " + gameHand.displayTheDiceValue(k) + "  ");
			helper.addActionListener(new ColorAction(helper, k));
			buttPanel.add(helper);
		}
		
		JButton play = new JButton("Continue");
		JButton endTurn = new JButton("End Turn");
		
		// making end turn button and keep playing button
		playPanel.add(play);
		playPanel.add(endTurn);
		
		// making instruction
		JLabel direction = new JLabel("Select which dice you want to keep");
		directionPanel.add(direction);
		
		// adding panels
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 125, 0);
		frame.add(firstPanel, c);
		
		c.gridx = GridBagConstraints.RELATIVE;
		c.insets = new Insets(0, 200, 125, 0);
		frame.add(scoreBoard, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 50);
		c.fill = GridBagConstraints.HORIZONTAL;
		frame.add(directionPanel, c);
		
		c.gridy = GridBagConstraints.RELATIVE;
		frame.add(buttPanel, c);
		
		
		c.gridy = GridBagConstraints.RELATIVE;
		c.insets = new Insets(20, 0, 50, 50);
		frame.add(playPanel, c);
		
		// adding listeners
		panelAction player = new panelAction();
		endTurnAction endIt = new endTurnAction();
		
		// associating listeners
		play.addActionListener(player);
		endTurn.addActionListener(endIt);
		
		frame.setVisible(true);
		}
	
	
	/*
     * An action listener that sets the panel's background color.
     */
    static private class ColorAction implements ActionListener
    {
    	JButton newButton;
    	int numOfClicks = 0;
    	int actionNumber;
    	
    	private ColorAction(JButton button, int inter)
    	{
    		newButton = button;
    		actionNumber = inter;
    	}
    	public void actionPerformed(ActionEvent event)
    	{
    		if (numOfClicks % 2 == 0)
    		{
    			newButton.setBackground(Color.BLUE);
    			// set to true
    			mySideStuff.gameHand.setRolling(actionNumber, false);
    		} else {
    			newButton.setBackground(null);
    			// set to false
    			mySideStuff.gameHand.setRolling(actionNumber, true);
    		}
    		numOfClicks++;
    	}
    }
    
    static private class panelAction implements ActionListener
	{
		Boolean[] hasBeenRemoved = new Boolean[6];
		
		private panelAction()
		{
			for (int k = 0; k < 6; k++)
			{
				hasBeenRemoved[k] = false;
				gameHand.setRolling(k, true);
			}
		}
		
		private void resetBool()
		{
			gameHand = new hand(6);
			gameHand.roll();

			for (int k = 0; k < 6; k++)
			{
				hasBeenRemoved[k] = false;
				gameHand.setRolling(k, true);
			}
			
			gameHand.roll();
			
			for (int k = 0; k < 6; k++)
			{
				JButton helper = new JButton("  " + gameHand.displayTheDiceValue(k) + "  ");
				helper.addActionListener(new ColorAction(helper, k));
				buttPanel.add(helper);
			}
			
			frame.setVisible(true);
		}
		
		public void actionPerformed(ActionEvent event)
		{
			buttPanel.removeAll();
			buttPanel.repaint();
			
			// send hand to score
			
			gameHand = new hand(true, gameHand);

			gameHand.roll();
			gameHand.displayRoll();
			
			for (int k = 0; k < gameHand.numOfDice; k++)
			{
				JButton helper = new JButton("  " + gameHand.displayTheDiceValue(k) + "  ");
				helper.addActionListener(new ColorAction(helper, k));
				buttPanel.add(helper);
			}
			
			if (false) // add that check validity shit
			{
				buttPanel.removeAll();
				JLabel farkle = new JLabel("Get Farked Up");
				buttPanel.add(farkle);
				roundScore = 0;
				buttPanel.repaint();
				buttPanel.setVisible(true);
				frame.setVisible(true);
				endTurnAction.isFarkle(true);
				playPanel.remove(0);
			}
			
			if (gameHand.numOfDice == 0)
			{
				resetBool();
			}
			buttPanel.setLayout(new BoxLayout(buttPanel, BoxLayout.LINE_AXIS));
			buttPanel.repaint();
			buttPanel.setVisible(true);
			frame.setVisible(true);
		}
		
	}
    
    static private class endTurnAction implements ActionListener {
    	static Boolean didIFarkle = false;
    	
    	public void actionPerformed(ActionEvent event)
    	{
    		// send hand to scorer and add to player
    		//reset round score
    		if (playerNumber == 4)
    		{
    			// check end game conditions
    			playerNumber = 0;
    		}
    		playerNumber++;
    		firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.Y_AXIS));
    		JLabel playerLabel = new JLabel("Player " + playerNumber +"'s Turn");
    		playerLabel.setHorizontalAlignment(JLabel.LEFT);
    		JLabel currentScore = new JLabel("Round Score = " + " 100");
    		
    		
    		firstPanel.removeAll();
    		firstPanel.add(playerLabel, BorderLayout.NORTH);
    		firstPanel.add(currentScore); 
    		
    		buttPanel.removeAll();
    		
    		for (int k = 0; k < 6; k++)
    		{
    			gameHand.setRolling(k, true);
    		}
    		gameHand.roll();
    		
    		if (false) // add that check validity shit
			{
				buttPanel.removeAll();
				JLabel farkle = new JLabel("Get Farked Up");
				buttPanel.add(farkle);
				roundScore = 0;
				buttPanel.repaint();
				buttPanel.setVisible(true);
				frame.setVisible(true);
				endTurnAction.isFarkle(true);
				playPanel.remove(0);
			} else {
				for (int k = 0; k < 6; k++)
				{
					JButton helper = new JButton("  " + gameHand.displayTheDiceValue(k) + "  ");
					helper.addActionListener(new ColorAction(helper, k));
					buttPanel.add(helper);
				}
			}
			
			if (didIFarkle == true)
			{
				playPanel.removeAll();
				
				JButton play = new JButton("Continue");
				JButton endTurn = new JButton("End Turn");
				
				// making end turn button and keep playing button
				playPanel.add(play);
				playPanel.add(endTurn);
				
				panelAction player = new panelAction();
				endTurnAction endIt = new endTurnAction();
				
				play.addActionListener(player);
				endTurn.addActionListener(endIt);
				
				playPanel.repaint();
				didIFarkle = false;
			}
			
			frame.setVisible(true);
    	}
    	
    	static public void isFarkle(Boolean didILose)
    	{
    		didIFarkle = didILose;
    	}
    }
}
