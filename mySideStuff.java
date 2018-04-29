import java.awt.*;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.*;

public class mySideStuff {
	public static void main(String[] args) { 
		
		JFrame frame = new JFrame("Button Play");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		
		// label panel
		JPanel panel = new JPanel();
		JLabel lbl = new JLabel("Select which dice you want to keep:");
		panel.add(lbl);
		
		// button panel
		JPanel buttPanel = new JPanel();
		buttPanel.setLayout(new BoxLayout(buttPanel, BoxLayout.X_AXIS));
		
		JButton button1 = new JButton("    1    ");
		JButton button2 = new JButton("    1    ");
		JButton button3 = new JButton("    1    ");
		JButton button4 = new JButton("    1    ");
		JButton button5 = new JButton("    1    ");
		JButton button6 = new JButton("    1    ");
		
		buttPanel.add(button1);
		buttPanel.add(button2);
		buttPanel.add(button3);
		buttPanel.add(button4);
		buttPanel.add(button5);
		buttPanel.add(button6);
		
		// adding panels
		frame.add(panel);
		frame.add(buttPanel);
		
		// adding listeners
		ColorAction action1 = new ColorAction(button1);
		ColorAction action2 = new ColorAction(button2);
		ColorAction action3 = new ColorAction(button3);
		ColorAction action4 = new ColorAction(button4);
		ColorAction action5 = new ColorAction(button5);
		ColorAction action6 = new ColorAction(button6);
		
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
		

		
		}
	
	/*
     * An action listener that sets the panel's background color.
     */
    static private class ColorAction implements ActionListener
    {
    	JButton newButton;
    	int numOfClicks = 0;
    	
    	private ColorAction(JButton button)
    	{
    		newButton = button;
    	}
    	public void actionPerformed(ActionEvent event)
    	{
    		if (numOfClicks % 2 == 0)
    		{
    			newButton.setBackground(Color.BLUE);
    		} else {
    			newButton.setBackground(null);
    		}
    		numOfClicks++;
    	}
    }
	
		
		
}
