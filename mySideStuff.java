import java.awt.*;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.*;

public class mySideStuff {
	static Boolean[] array = new Boolean[6];
	static JPanel buttPanel = new JPanel();
	
	public static void main(String[] args) { 
		int number = 1;
		JFrame frame = new JFrame("Button Play");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLayout(new GridBagLayout());
		
		// button panel
		JPanel firstPanel = new JPanel();
		
		JLabel playerLabel = new JLabel("Player One's Turn");
		firstPanel.add(playerLabel, BorderLayout.PAGE_START);
		JLabel direction = new JLabel("Select which dice you want to keep");
		
		buttPanel.setLayout(new BoxLayout(buttPanel, BoxLayout.LINE_AXIS));
		
		JButton button1 = new JButton("  " + number +"  ");
		JButton button2 = new JButton("  2  ");
		JButton button3 = new JButton("  3  ");
		JButton button4 = new JButton("  4  ");
		JButton button5 = new JButton("  5  ");
		JButton button6 = new JButton("  6  ");
		JButton play = new JButton("Press me to Continue");
		
		JPanel playPanel = new JPanel();
		playPanel.add(play);
		
		JPanel directionPanel = new JPanel();
		directionPanel.add(direction);
		
		buttPanel.add(button1);
		buttPanel.add(button2 );
		buttPanel.add(button3 );
		buttPanel.add(button4 );
		buttPanel.add(button5 );
		buttPanel.add(button6 );
		
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
		frame.add(playPanel, c);
		
		// adding listeners
		ColorAction action1 = new ColorAction(button1, 0);
		ColorAction action2 = new ColorAction(button2, 1);
		ColorAction action3 = new ColorAction(button3, 2);
		ColorAction action4 = new ColorAction(button4, 3);
		ColorAction action5 = new ColorAction(button5, 4);
		ColorAction action6 = new ColorAction(button6, 5);
		panelAction action7 = new panelAction(buttPanel, frame);
		
		/*
		ColorAction yellowAction = new ColorAction(Color.yellow);
        ColorAction blueAction = new ColorAction(Color.blue);
        ColorAction redAction = new ColorAction(Color.red); */
		
		// associating listeners
		button1.addActionListener(action1);
		button2.addActionListener(action2);
		button3.addActionListener(action3);
		button4.addActionListener(action4);
		button5.addActionListener(action5);
		button6.addActionListener(action6);
		play.addActionListener(action7);
		
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
		JPanel panelHelper;
		JFrame frameHelper;
		
		private panelAction(JPanel panel, JFrame frame) // x and y
		{
			panelHelper = panel;
			frameHelper = frame;
		}
		
		public void actionPerformed(ActionEvent event)
		{
			/*
			frameHelper.setContentPane(panelHelper);
			frameHelper.pack();
			*/
			int count = 0;
			while (count < 6)
			{
				
				if (mySideStuff.array[count] == true)
				{
					mySideStuff.buttPanel.remove(count);
				}
				
				System.out.println(mySideStuff.array[count]);
				count++;
			}
			
		}
		
	}	
}
