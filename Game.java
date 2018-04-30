import java.util.Scanner;
import java.io.*;
import javax.swing.*;


import java.awt.*;
import java.awt.event.*;

public class Game {
    public static int numPlayers = 0;
    public static int maxScore = 0;
    public static int maxNumTurns = 0;
    public static player playerArray[];
    public static Scanner in = new Scanner(System.in);
    public static JFrame mainFrame = new JFrame("Farkle");


    static Boolean[] array = new Boolean[6];
    static JPanel buttPanel = new JPanel();
    static JPanel firstPanel = new JPanel();
    static JFrame frame = new JFrame("Button Play");
    static int playerNumber = 1;

    /**
     * Game Constructor
     * creates a game object
     */
    public Game()
    {
        mainFrame.setSize(500,500);
        getConfiguration();
    }

    public static void main(String[] args)
    {
        Game farkleGame = new Game();
        int turnNum = 0;
        boolean isGameComplete = false;

        // loop for the overall game
        // will run until game is complete
        while(!isGameComplete)
        {
            turnNum++;
            // for loop to ensure each player
            // has a turn to roll
            for (int i = 0; i < numPlayers; i++)
            {
                System.out.println("Player " + (i + 1) + "'s turn!");

                boolean isValidHand = true;
                boolean isTurnOver = false;

                int turnScore = 0;
                hand myHand = new hand(6);

                // inner loop to run one turn
                while(isValidHand && !isTurnOver)
                {
                    String toScore;
                    System.out.println("Your turn score is: " + turnScore);

                    myHand.roll();
                    myHand.displayRoll();
                    scorer validityChecker = new scorer(myHand);
                    isValidHand = validityChecker.checkValidity();
                    scorer addScore = new scorer(myHand);

                    if(isValidHand)
                    {
                        boolean isValidSelection = false;
                        while (!isValidSelection)
                        {
                            int rollScore;
                            System.out.println("Please select the dice you would like to score");
                            toScore = in.nextLine();

                            for (int k = 0; k < myHand.numOfDice; k++)
                            {
                                if(toScore.charAt(k) == 'n')
                                {
                                    myHand.setRolling(k, true);
                                }
                            }

                            addScore = new scorer(myHand);
                            rollScore = addScore.Score();

                            if (rollScore >= 0)
                            {
                                isValidSelection = true;
                                turnScore = turnScore + rollScore;
                            }
                        }

                        System.out.println("Would you like to end your turn? (y/n)");
                        String endTurn = in.nextLine();

                        if (endTurn.charAt(0) == 'y')
                        {
                            playerArray[i].addPoints(turnScore);
                            isTurnOver = true;
                        }
                        else if ((myHand.numOfDice - addScore.playerHand.numOfDice) == 0)
                        {
                            myHand = new hand(6);
                        }
                        else
                        {
                            myHand = new hand(myHand.numOfDice - addScore.playerHand.numOfDice);
                        }
                    }
                    else
                    {
                        System.out.println("Farkle!");
                        isValidHand = false;
                    }

                }
            }

            isGameComplete = checkEndConditions(turnNum);
            //ScoreBoardPanel finalScores = new ScoreBoardPanel();
            //mainFrame.setVisible(true);
            if(isGameComplete){
            }

        }
        System.out.println("");
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
        mainFrame.add(setupPanel);
        JLabel scoreLabel = new JLabel("Enter Score To Reach: ");
        JLabel turnLabel = new JLabel("Enter Number Of Turns: ");
        JLabel playerLabel = new JLabel("Enter Number Of Players: ");
        JButton play = new JButton("Play");
        setupPanel.setLayout(new BoxLayout(setupPanel, BoxLayout.Y_AXIS));

        // creates frame
        //setupFrame.setSize(300,200);
        //setupFrame.add(setupPanel);

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

        //setupFrame.setVisible(true);
        mainFrame.setVisible(true);

        JPanel GamePanel = new JPanel();
        //JButton finalScores = new JButton("Final Scores");
        //GamePanel.add(finalScores);
        //panelAction playAction = new panelAction(GamePanel,500,500);
        //play.addActionListener(playAction);
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
        /*
        finalScores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                GamePanel.remove(finalScores);
                if (event.getSource() == finalScores) {
                    JPanel finalScores = ScoreBoardPanel.createScorePanel();
                    mainFrame.add(finalScores);
                    mainFrame.setVisible(true);

                }

            }

        });

        playerArray = new player[numPlayers];
        for(int i = 0; i < numPlayers; i++)
        {
            playerArray[i] = new player();
        }
        */
    }

    private class startGame implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //setupFrame.setVisible(false);
        }
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

    /*
    public static void drawWindow()
    {
        mainFrame.setSize(600,600);
        mainFrame.setVisible(true);
        mainFrame.setBackground(new Color(51, 153, 0));

        JLabel label1 = new JLabel();
        label1.setText("Farkle");
        label1.setFont(new Font("Copperplate", Font.PLAIN, 36));
        label1.setBackground(new Color(51, 153, 0));
    }
    */

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
        /*
        Boolean[] array = new Boolean[6];
        JPanel buttPanel = new JPanel();
        JPanel firstPanel = new JPanel();
        JFrame frame = new JFrame("Button Play");
        int playerNumber = 1;
        */
        System.out.println("in create game");
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(550, 550);
        mainFrame.setLayout(new GridBagLayout());

            // making scoreboard
            JPanel scoreBoard = new JPanel();
            scoreBoard.setLayout(new BoxLayout(scoreBoard, BoxLayout.Y_AXIS));

            JLabel header = new JLabel("Current Scoreboard");
            JLabel divider = new JLabel("-----------------------------");
            scoreBoard.add(header);
            scoreBoard.add(divider);
            for (int k = 0; k < 4; k++)
            {
                JLabel temp = new JLabel("Player " + (k+1) + playerArray[k].getScore());
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

		/*
		ColorAction yellowAction = new ColorAction(Color.yellow);
        ColorAction blueAction = new ColorAction(Color.blue);
        ColorAction redAction = new ColorAction(Color.red); */

            // associating listeners
            play.addActionListener(player);
            endTurn.addActionListener(endIt);

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
            array[actionNumber] = false;
        }
        public void actionPerformed(ActionEvent event)
        {
            if (numOfClicks % 2 == 0)
            {
                newButton.setBackground(Color.BLUE);
                newButton.setOpaque(true);
                // set to true
                array[actionNumber] = true;
            } else {
                newButton.setBackground(null);
                // set to false
                array[actionNumber] = false;
            }
            numOfClicks++;
        }
    }


    static private class panelAction2 implements ActionListener
    {
        int numOfRemove = 0;
        Boolean[] hasBeenRemoved = new Boolean[6];

        private panelAction2()
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
            mainFrame.setVisible(true);
        }

        public void actionPerformed(ActionEvent event)
        {

            for (int k = 0; k < 6; k++)
            {
                if (array[k] == true && hasBeenRemoved[k] == false)
                {
                    int adjustedNeeded = 0;
                    for (int i = 0; i < k; i++)
                    {
                        if (hasBeenRemoved[i] == true)
                        {
                            adjustedNeeded++;
                        }
                    }
                    buttPanel.remove(k - adjustedNeeded);
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
            if(playerNumber == numPlayers){
                playerNumber = 0;
            }

            playerNumber++;
            // add score, reset frame, change player;
            firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.Y_AXIS));
            JLabel playerLabel = new JLabel("Player " + playerNumber +"'s Turn");
            playerLabel.setHorizontalAlignment(JLabel.LEFT);


            firstPanel.removeAll();
            firstPanel.add(playerLabel);

            buttPanel.removeAll();
            for (int k = 0; k < 6; k++)
            {
                JButton helper = new JButton("  " + (k+1) + "  ");
                buttPanel.add(helper);
            }

            mainFrame.setVisible(true);
        }
    }
}