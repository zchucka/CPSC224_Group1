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
		frame.setSize(500, 500);
		frame.setLayout(new GridBagLayout());
		
		// button panel
		
		
		JLabel playerLabel = new JLabel("Player " + playerNumber +"'s Turn");
		playerLabel.setHorizontalAlignment(JLabel.LEFT);
		firstPanel.add(playerLabel, BorderLayout.NORTH);
		JLabel direction = new JLabel("Select which dice you want to keep");
		
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
		
		JPanel playPanel = new JPanel();
		playPanel.add(play);
		playPanel.add(endTurn);
		
		JPanel directionPanel = new JPanel();
		directionPanel.add(direction);
		
		// adding panels
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		frame.add(firstPanel, c);
		
		
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		frame.add(directionPanel, c);
		
		c.gridy = GridBagConstraints.RELATIVE;
		frame.add(buttPanel, c);
		
		
		c.gridy = GridBagConstraints.RELATIVE;
		c.insets = new Insets(20, 0, 0, 0);
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
