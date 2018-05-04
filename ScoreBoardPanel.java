import java.awt.*;

import javax.swing.*;
import java.awt.event.*;

public class ScoreBoardPanel {

    public static void createScorePanel(){
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        JLabel information = new JLabel("Final Scores");
        information.setFont(new Font("TimesRoman", Font.BOLD, 32));
        scorePanel.add(information, BorderLayout.PAGE_START);
        scorePanel.setBackground(Game.VERY_DARK_GREEN);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 60;
        for(int i= 0; i < Game.numPlayers; i++) {
            JLabel temp = new JLabel("Player " + Game.playerArray[i].getName() +  "'s Final Score: " + Game.playerArray[i].getScore());
            temp.setFont(new Font("TimesRoman",Font.PLAIN, 24));
            scorePanel.add(temp, c);
            c.gridy = GridBagConstraints.RELATIVE;
            c.insets = new Insets(10,0,0,0);
        }
        Game.mainFrame.setContentPane(new JPanel());
        Game.mainFrame.pack();
        c.insets = new Insets(20,0,0,0);
        Game.mainFrame.add(scorePanel, c);
        Game.mainFrame.setSize(650, 550);
        Game.mainFrame.getContentPane().setBackground(Game.VERY_DARK_GREEN);
        Game.mainFrame.setVisible(true);
    }

}
