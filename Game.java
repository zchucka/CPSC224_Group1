import java.util.Scanner;
import java.io.*;

public class Game {
    public static int numPlayers;
    public int maxScore;
    public int numTurns;
    public player playerArray[];
    public static Scanner in = new Scanner(System.in);

    /**
     * Game Constructor
     * creates a game object
     */
    public Game() 
    {
        //getConfiguration();

        numPlayers = 4;
        maxScore = 10000;
        numTurns = 10;
        playerArray = new player[numPlayers];
    }

    public static void main(String[] args)
    {
        Game farkleGame = new Game();

        boolean isGameComplete = false;

        while(!isGameComplete)
        {
            for (int i = 0; i < numPlayers; i++)
            {
                System.out.println("Player " + (i + 1) + "'s turn!");

                boolean isValidHand = true;
                boolean isTurnOver = false;
                int turnScore = 0;
                hand myHand = new hand(6);

                while(isValidHand && !isTurnOver)
                {
                    String toScore;

                    System.out.println("Your turn score is: " + turnScore);
                    
                    myHand.roll();
                    myHand.displayRoll();
                    scorer validityChecker = new scorer(myHand);
                    isValidHand = validityChecker.checkValidity();

                    if(isValidHand)
                    {
                        System.out.println("Please select the dice you would like to score");
                        toScore = in.nextLine();

                        for (int k = 0; k < myHand.numOfDice; k++)
                        {
                            if(toScore.charAt(k) == 'n')
                            {
                                myHand.setRolling(k, true);
                            }
                        }
                        
                        scorer addScore = new scorer(myHand);
                        turnScore = turnScore + addScore.Score();

                        if (myHand.numOfDice == 0)
                        {
                            myHand = new hand(6);
                        }
                        else
                        {
                            System.out.println("num of dice is: " + myHand.numOfDice);
                            myHand = new hand(myHand.numOfDice); 
                        }
                    }
                    else
                    {
                        System.out.println("Farkle!");
                        isValidHand = false;
                    }

                }
            }
        }

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
        numTurns = console.nextInt();

        return numTurns;
    }


    /**
     * This function gets the game configurations by setting
     * the numPlayer, numTurns, and maxScore variables.
     * @parameter none
     * @returns void
     * @throw no exceptions thrown
     */
    public void getConfiguration(){
        setMaxScore();
        setNumPlayers();
        setNumTurns();
    }

}
