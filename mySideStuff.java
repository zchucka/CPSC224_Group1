import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.*;

public class mySideStuff {
	static Boolean[] array = new Boolean[6];
	static JPanel buttPanel = new JPanel();
	static JPanel firstPanel = new JPanel();
	static JFrame frame = new JFrame("Button Play");
	static int playerNumber = 1;
	
	public static void main(String[] args) { 
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(550, 550);
		frame.setLayout(new GridBagLayout());
		
		// making scoreboard
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
		
		// creating the dice
		for (int k = 0; k < 6; k++)
		{
			JButton helper = new JButton("  " + (k+1) + "  ");
			helper.addActionListener(new ColorAction(helper, k));
			buttPanel.add(helper);
		}
		
		JButton play = new JButton("Continue");
		JButton endTurn = new JButton("End Turn");
		
		// making end turn button and keep playing button
		JPanel playPanel = new JPanel();
		playPanel.add(play);
		playPanel.add(endTurn);
		
		// making instruction
		JPanel directionPanel = new JPanel();
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
		
		/*
		ColorAction yellowAction = new ColorAction(Color.yellow);
        ColorAction blueAction = new ColorAction(Color.blue);
        ColorAction redAction = new ColorAction(Color.red); */
		
		// associating listeners
		play.addActionListener(player);
		endTurn.addActionListener(endIt);
		
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
    		mySideStuff.array[actionNumber] = false;
    	}
    	public void actionPerformed(ActionEvent event)
    	{
    		if (numOfClicks % 2 == 0)
    		{
    			newButton.setBackground(Color.BLUE);
    			// set to true
    			mySideStuff.array[actionNumber] = true;
    		} else {
    			newButton.setBackground(null);
    			// set to false
    			mySideStuff.array[actionNumber] = false;
    		}
    		numOfClicks++;
    	}
    }
    
    static private class panelAction implements ActionListener
	{
		int numOfRemove = 0;
		Boolean[] hasBeenRemoved = new Boolean[6];
		
		private panelAction()
		{
			resetBool();
		}
		
		private void resetBool()
		{
			for (int k = 0; k < 6; k++)
			{
				hasBeenRemoved[k] = false;
			}
			if (numOfRemove == 6)
			{
				for (int k = 0; k < 6; k++)
				{
					JButton helper = new JButton("  " + (k+1) + "  ");
					helper.addActionListener(new ColorAction(helper, k));
					buttPanel.add(helper);
				}
			}
			frame.setVisible(true);
		}
		
		public void actionPerformed(ActionEvent event)
		{
			
			for (int k = 0; k < 6; k++)
			{
				if (mySideStuff.array[k] == true && hasBeenRemoved[k] == false)
				{
					int adjustedNeeded = 0;
					for (int i = 0; i < k; i++)
					{
						if (hasBeenRemoved[i] == true)
						{
							adjustedNeeded++;
						}
					}
					mySideStuff.buttPanel.remove(k - adjustedNeeded);
					hasBeenRemoved[k] = true;
					numOfRemove++;
				}
			}
			buttPanel.setLayout(new BoxLayout(buttPanel, BoxLayout.LINE_AXIS));

			if (numOfRemove == 6)
			{
				resetBool();
				numOfRemove = 0;
			}
			buttPanel.repaint();
		}
		
	}
    
    static private class endTurnAction implements ActionListener {
    	
    	public void actionPerformed(ActionEvent event)
    	{
    		// add score, reset frame, change player
    		playerNumber++;
    		JLabel playerLabel = new JLabel("Player " + playerNumber +"'s Turn");
    		firstPanel.removeAll();
    		firstPanel.add(playerLabel);
    		
    		buttPanel.removeAll();
			for (int k = 0; k < 6; k++)
			{
				JButton helper = new JButton("  " + (k+1) + "  ");
				buttPanel.add(helper);
			}
			
			frame.setVisible(true);
    	}
    }
}
