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
        getConfiguration();
        playerArray = new player[numPlayers];
    }

    public static void main(String[] args)
    {
        Game farkleGame = new Game();

        boolean isGameComplete = false;

        while(!isGameComplete)
        {
            boolean isFarkle = false;
            boolean isTurnOver = false;

            for (int i = 0; i < numPlayers; i++)
            {
                hand myHand = new hand(6);

                while(!isFarkle && !isTurnOver)
                {
                    String toScore;
                    
                    myHand.roll();
                    myHand.displayRoll();
                    isFarkle = scorer.checkValidity(myHand);

                    if(!isFarkle)
                    {
                        System.out.println("Please select the dice you would like to score");
                        toScore = in.nextLine();

                        for (int k = 0; k < 6; k++)
                        {
                            if(toScore.charAt(k) == 'y')
                            {
                                myHand.setRolling(k);
                            }
                        }
                    }
                    else
                    {
                        System.out.println("You farkled you dumb mf");
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
