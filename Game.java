import java.util.Scanner;
import java.io.*;
import javax.swing.*;
import java.awt.Color;

import java.awt.*;
import java.awt.event.*;

public class Game {
    public static int numPlayers = 0;
    private static int maxScore = 0;
    private static int maxNumTurns = 0;
    public static player playerArray[];
    private static Scanner in = new Scanner(System.in);
    public static JFrame mainFrame = new JFrame("Farkle");
    private static hand myHand = new hand(6);
    public static final Color VERY_DARK_GREEN = new Color(0,153,0);


    static JPanel buttPanel = new JPanel();
    static JPanel firstPanel = new JPanel();
    static JPanel playPanel = new JPanel();
    static JPanel scoreBoard = new JPanel();
    static JPanel directionPanel = new JPanel();
    static int playerNumber = 1;
    static int roundScore = 0;
    static int roundNumber = 1;

    /**
     * Game Constructor
     * creates a game object
     */
    public Game()
    {
        mainFrame.setSize(200,200);
        getConfiguration();
        System.out.println("here");
    }

    public static void main(String[] args)
    {
        Game farkleGame = new Game();
    }

    /**
     * This function takes user input for the number of players they would to play with.
     * @return integer numPlayers
     */
    public int setNumPlayers(){
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter how many players you have: ");
        numPlayers = console.nextInt();

        return numPlayers;
    }


    /**
     * This function takes user input for the maximum Score they would like to set. Max Score is the
     * target for each player to reach in the game.
     * @return integer maxScore
     */
    public int setMaxScore(){
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter the max score you would like to set: ");
        maxScore = console.nextInt();

        return maxScore;
    }


    /**
     * This function takes user input for the maximum amount of turns they would like
     * to allow before scoring the game.
     * @return integer numTurns
     */
    public int setNumTurns(){
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter the max number of turns you will allow for each player: ");
        maxNumTurns = console.nextInt();

        return maxNumTurns;
    }


    /**
     * This function gets the game configurations by setting
     * the numPlayer, numTurns, and maxScore variables.
     * @parameter none
     * @returns void
     * @throw no exceptions thrown
     */
    public void getConfiguration()
    {
        //JFrame setupFrame = new JFrame();
        JPanel setupPanel = new JPanel();
        mainFrame.getContentPane().setBackground(VERY_DARK_GREEN);
        mainFrame.add(setupPanel);
        JLabel scoreLabel = new JLabel("Enter Score To Reach: ");
        JLabel turnLabel = new JLabel("Enter Number Of Turns: ");
        JLabel playerLabel = new JLabel("Enter Number Of Players: ");
        JButton play = new JButton("Play");
        setupPanel.setLayout(new BoxLayout(setupPanel, BoxLayout.Y_AXIS));
        setupPanel.setBackground(VERY_DARK_GREEN);

        // creates fields
        JTextField scoreField = new JTextField(7);
        JTextField turnField = new JTextField(7);
        JTextField playersField = new JTextField(7);

        // sets fields visible
        scoreField.setVisible(true);
        turnField.setVisible(true);
        playersField.setVisible(true);

        // inserts the fields and labels
        setupPanel.add(scoreLabel);
        setupPanel.add(scoreField);
        setupPanel.add(turnLabel);
        setupPanel.add(turnField);
        setupPanel.add(playerLabel);
        setupPanel.add(playersField);
        setupPanel.add(play);

        mainFrame.setVisible(true);

        play.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent event){
                        if(event.getSource()==play){
                            try {
                                String scoreString = scoreField.getText();
                                maxScore = Integer.parseInt(scoreString);

                                String playerString = playersField.getText();
                                numPlayers = Integer.parseInt(playerString);

                                String turnString = turnField.getText();
                                maxNumTurns = Integer.parseInt(turnString);
                            }catch(NumberFormatException e) {

                            }
                            playerArray = new player[numPlayers];
                            for(int i = 0; i < numPlayers; i++)
                            {
                                playerArray[i] = new player();
                                playerArray[i].setName(i+1);
                            }
                            //play.addActionListener(playAction);
                            mainFrame.getContentPane().remove(setupPanel);
                            mainFrame.pack();
                            createGamePanel();
                            mainFrame.setVisible(true);
                        }
                    }
                }
        );
    }

    /*
     *
     */
    public static boolean checkEndConditions(int turnNum)
    {
        int highestScore = 0;
        for (int k = 0; k < numPlayers; k++)
        {
            if (highestScore < playerArray[k].getScore())
            {
                highestScore = playerArray[k].getScore();
            }
        }
        if (maxNumTurns <= turnNum || highestScore >= maxScore)
        {
            return true;
        } else {
            return false;
        }
    }

    static private class panelAction implements ActionListener
    {
        JPanel panelHelper;

        private panelAction(JPanel panel, int x, int y)
        {
            panelHelper = panel;
            mainFrame.setSize(x,y);
        }

        public void actionPerformed(ActionEvent event)
        {
            mainFrame.setContentPane(panelHelper);
            mainFrame.pack();
            mainFrame.setSize(500,500);
        }
    }

    public static void createGamePanel() {
        firstPanel.setBackground(VERY_DARK_GREEN);
        buttPanel.setBackground(VERY_DARK_GREEN);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(650, 550);
        mainFrame.setLayout(new GridBagLayout());

            // making scoreboard
            scoreBoard.setLayout(new BoxLayout(scoreBoard, BoxLayout.Y_AXIS));

            JLabel header = new JLabel("Current Scoreboard");
            JLabel divider = new JLabel("-----------------------------");
            scoreBoard.add(header);
            scoreBoard.add(divider);
            System.out.println(numPlayers);
            for (int k = 0; k < numPlayers; k++)
            {
                JLabel temp = new JLabel("Player " + (k+1) + ":    " + playerArray[k].getScore());
                scoreBoard.add(temp);
            }


            // making player turn label
            firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.Y_AXIS));
            JLabel playerLabel = new JLabel("Player " + playerNumber +"'s Turn");
            playerLabel.setHorizontalAlignment(JLabel.LEFT);
            JLabel currentScore = new JLabel("Round Score = " + roundScore);
            firstPanel.add(playerLabel, BorderLayout.NORTH);
            firstPanel.add(currentScore);

            buttPanel.setLayout(new BoxLayout(buttPanel, BoxLayout.LINE_AXIS));

            myHand.roll();

            // creating the dice
            for (int k = 0; k < myHand.numOfDice; k++)
            {
                JButton helper = new JButton("  " + myHand.displayTheDiceValue(k) + "  ");
                helper.addActionListener(new ColorAction(helper, k));
                buttPanel.add(helper);
            }

            JButton play = new JButton("Continue");
            JButton endTurn = new JButton("End Turn");

            // making end turn button and keep playing button
            playPanel.setBackground(VERY_DARK_GREEN);
            playPanel.add(play);
            playPanel.add(endTurn);

            // making instruction
            directionPanel.setBackground(VERY_DARK_GREEN);
            JLabel direction = new JLabel("Select which dice you want to keep");
            directionPanel.add(direction);

            // adding panels
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(0, 0, 125, 0);
        mainFrame.add(firstPanel, c);

            c.gridx = GridBagConstraints.RELATIVE;
            c.insets = new Insets(0, 200, 125, 0);
        mainFrame.add(scoreBoard, c);

            c.gridx = 1;
            c.gridy = 1;
            c.insets = new Insets(0, 0, 0, 50);
            c.fill = GridBagConstraints.HORIZONTAL;
        mainFrame.add(directionPanel, c);

            c.gridy = GridBagConstraints.RELATIVE;
        mainFrame.add(buttPanel, c);


            c.gridy = GridBagConstraints.RELATIVE;
            c.insets = new Insets(20, 0, 50, 50);
        mainFrame.add(playPanel, c);

            // adding listeners
            panelAction2 player = new panelAction2();
            endTurnAction endIt = new endTurnAction();

            // associating listeners
            play.addActionListener(player);
            endTurn.addActionListener(endIt);
            mainFrame.setVisible(true);

    }

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
                newButton.setOpaque(true);
                myHand.setRolling(actionNumber, false);
            } else {
                newButton.setBackground(null);
                myHand.setRolling(actionNumber, true);
            }
            numOfClicks++;
        }
    }


    static private class panelAction2 implements ActionListener
    {
        Boolean[] hasBeenRemoved = new Boolean[6];

        private panelAction2()
        {
            for(int k = 0; k < 6; k++ ){
                hasBeenRemoved[k] = false;
                myHand.setRolling(k, true);
            }
        }

        private void resetBool()
        {
            myHand = new hand(6);
            myHand.roll();

            for (int k = 0; k < 6; k++)
            {
                hasBeenRemoved[k] = false;
                myHand.setRolling(k, true);
            }

            myHand.roll();

            for (int k = 0; k < 6; k++)
            {
                JButton helper = new JButton("  " + myHand.displayTheDiceValue(k) + "  ");
                helper.addActionListener(new ColorAction(helper, k));
                buttPanel.add(helper);
            }

            mainFrame.setVisible(true);
        }

        public void actionPerformed(ActionEvent event)
        {
            // send hand to score
            scorer addScore = new scorer(myHand);
            int tempScore = addScore.Score();

            if (tempScore == -1)
            {
                directionPanel.removeAll();
                directionPanel.add(new JLabel("Invalid input"));
                directionPanel.repaint();
                directionPanel.setVisible(true);
                mainFrame.setVisible(true);
            } else {
                directionPanel.removeAll();
                JLabel direction = new JLabel("Select which dice you want to keep");
                directionPanel.add(direction);
                directionPanel.setBackground(VERY_DARK_GREEN);
                directionPanel.repaint();
                directionPanel.setVisible(true);
                mainFrame.setVisible(true);

                buttPanel.removeAll();
                buttPanel.repaint();
                roundScore = roundScore + tempScore;
                firstPanel.remove(1);
                firstPanel.add(new JLabel("Round Score = " + roundScore));
                firstPanel.repaint();
                mainFrame.setVisible(true);
                // if (score = -1) don't let them select it

                myHand = new hand(true, myHand);
                myHand.roll();
                for (int i = 0; i < myHand.numOfDice; i++) {
                    myHand.setRolling(i, false);
                }
                scorer aValidityChecker = new scorer(myHand);
                boolean isValidHand = aValidityChecker.checkValidity();

                for (int k = 0; k < myHand.numOfDice; k++) {
                    myHand.setRolling(k, true);
                    JButton helper = new JButton("  " + myHand.displayTheDiceValue(k) + "  ");
                    helper.addActionListener(new ColorAction(helper, k));
                    buttPanel.add(helper);
                }

                if (isValidHand == false && myHand.numOfDice != 0) {
                    buttPanel.removeAll();
                    JLabel farkle = new JLabel("Farkle");
                    buttPanel.add(farkle);
                    roundScore = 0;
                    buttPanel.repaint();
                    buttPanel.setVisible(true);
                    mainFrame.setVisible(true);
                    playPanel.remove(0);
                    playPanel.repaint();
                    playPanel.setVisible(true);
                }

                if (myHand.numOfDice == 0) {
                    resetBool();
                }

                buttPanel.setLayout(new BoxLayout(buttPanel, BoxLayout.LINE_AXIS));
                buttPanel.repaint();
                buttPanel.setVisible(true);
                mainFrame.setVisible(true);
            }
        }

    }

    static private class endTurnAction implements ActionListener {

        public void actionPerformed(ActionEvent event)
        {
            System.out.println("calling the end turn action listener");
            int tempScore = 0;
            Boolean [] helperArray = new Boolean[6];
            for (int j = 0; j < myHand.numOfDice; j++)
            {
                helperArray[j] = true;
                if (myHand.cupOfDice[j].moreRolling())
                {
                    helperArray[j] = false;
                }
                myHand.setRolling(j, false);
            }
            scorer validChecker = new scorer(myHand);

            boolean isValid = validChecker.checkValidity();
            if (isValid) {
                for (int l = 0; l < myHand.numOfDice; l++) {
                    if (helperArray[l] == false) {
                        myHand.setRolling(l, true);
                    }
                }
                validChecker = new scorer(myHand);
                tempScore = validChecker.Score();
                if (tempScore == -1) {
                    directionPanel.removeAll();
                    directionPanel.add(new JLabel("Invalid input"));
                    directionPanel.repaint();
                    directionPanel.setVisible(true);
                    mainFrame.setVisible(true);
                } else {
                    directionPanel.removeAll();
                    JLabel direction = new JLabel("Select which dice you want to keep");
                    directionPanel.add(direction);
                    directionPanel.setBackground(VERY_DARK_GREEN);
                    directionPanel.repaint();
                    directionPanel.setVisible(true);
                    mainFrame.setVisible(true);

                    roundScore = roundScore + tempScore;
                    playerArray[playerNumber - 1].addPoints(roundScore);
                    scoreBoard.removeAll();
                    JLabel header = new JLabel("Current Scoreboard");
                    JLabel divider = new JLabel("-----------------------------");
                    scoreBoard.add(header);
                    scoreBoard.add(divider);

                    for (int k = 0; k < numPlayers; k++) {
                        JLabel temp = new JLabel("Player " + (k + 1) + ":    " + playerArray[k].getScore());
                        scoreBoard.add(temp);
                    }
                    scoreBoard.repaint();
                    mainFrame.setVisible(true);
                }
            }
                if (tempScore >= 0) {
                    roundScore = 0;
                    Boolean endTheGame = false;
                    // send hand to scorer and add round score to player
                    //roundScore = 0;
                    if (playerNumber == numPlayers) {
                        playerNumber = 0;
                        if (checkEndConditions(roundNumber)) {
                            // end that bitch
                            System.out.println("ending the game");
                            ScoreBoardPanel.createScorePanel();
                            endTheGame = true;
                            mainFrame.setVisible(true);
                        } else {
                            roundNumber++;
                        }
                    }

                    if (!endTheGame) {
                        playerNumber++;

                        firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.Y_AXIS));
                        JLabel playerLabel = new JLabel("Player " + playerNumber + "'s Turn");
                        playerLabel.setHorizontalAlignment(JLabel.LEFT);
                        JLabel currentScore = new JLabel("Round Score = " + roundScore);


                        firstPanel.removeAll();
                        firstPanel.add(playerLabel, BorderLayout.NORTH);
                        firstPanel.add(currentScore);

                        buttPanel.removeAll();
                        myHand = new hand(6);
                        myHand.roll();

                        for (int k = 0; k < myHand.numOfDice; k++) {
                            myHand.setRolling(k, false);
                        }
                        scorer a3ValidityCheck = new scorer(myHand);

                        isValid = a3ValidityCheck.checkValidity();

                        for (int k = 0; k < 6; k++) {
                            myHand.setRolling(k, true);

                        }
                        // check if valid

                        playPanel.removeAll();

                        JButton play = new JButton("Continue");
                        JButton endTurn = new JButton("End Turn");

                        // making end turn button and keep playing button
                        playPanel.add(play);
                        playPanel.add(endTurn);

                        panelAction2 player = new panelAction2();
                        endTurnAction endIt = new endTurnAction();

                        play.addActionListener(player);
                        endTurn.addActionListener(endIt);

                        playPanel.repaint();

                        if (isValid == false) // add that check validity shit
                        {
                            buttPanel.removeAll();
                            JLabel farkle = new JLabel("Get Farked Up");
                            buttPanel.add(farkle);
                            roundScore = 0;
                            buttPanel.repaint();
                            buttPanel.setVisible(true);
                            mainFrame.setVisible(true);
                            playPanel.remove(0);
                        } else {
                            for (int k = 0; k < 6; k++) {
                                JButton helper = new JButton("  " + myHand.displayTheDiceValue(k) + "  ");
                                helper.addActionListener(new ColorAction(helper, k));
                                buttPanel.add(helper);
                            }
                        }
                        mainFrame.setVisible(true);
                    }
            }
        }
    }
}