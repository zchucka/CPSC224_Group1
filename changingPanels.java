import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.*;

public class changingPanels {
	public static void main(String[] args) { 
		JFrame frame = new JFrame("Learning how to change panels");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		
		JPanel firstPanel = new JPanel();
		JLabel firstLabel = new JLabel("The first Panel");
		firstLabel.setVisible(true);
		JButton firstButton = new JButton("Click to Change Panel");
		firstPanel.add(firstLabel);
		firstPanel.add(firstButton);
		
		frame.add(firstPanel);
		
		JPanel secondPanel = new JPanel();
		JLabel secondLabel = new JLabel("The second Panel");
		JButton secondButton = new JButton("Click to change panel");
		secondPanel.add(secondLabel);
		secondPanel.add(secondButton);
		
		firstPanel.setVisible(true);
		
		panelAction action = new panelAction(secondPanel, frame);
		firstButton.addActionListener(action);
		
		panelAction actionBack = new panelAction(firstPanel, frame);
		secondButton.addActionListener(actionBack);
	}
	
	static private class panelAction implements ActionListener
	{
		JPanel panelHelper;
		JFrame frameHelper;
		
		private panelAction(JPanel panel, JFrame frame)
		{
			panelHelper = panel;
			frameHelper = frame;
		}
		
		public void actionPerformed(ActionEvent event)
		{
			frameHelper.setContentPane(panelHelper);
			frameHelper.pack();
		}
	}
}
