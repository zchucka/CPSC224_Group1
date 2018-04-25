import java.util.Scanner;
import java.io.*;

public class Game {
    public static int numPlayers = 0;
    public static int maxScore = 0;
    public static int maxNumTurns = 0;
    public static player playerArray[] = new player[4];
    public static Scanner in = new Scanner(System.in);

    /**
     * Game Constructor
     * creates a game object
     */
    public Game() 
    {
        getConfiguration();

        /*numPlayers = 4;
        maxScore = 10000;
        maxNumTurns = 10;
        playerArray = new player[numPlayers];
        for(int i = 0; i < numPlayers; i++)
        {
            playerArray[i] = new player();
        }*/
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
    public void getConfiguration(){
        setMaxScore();
        setNumPlayers();
        setNumTurns();
        for(int i = 0; i < numPlayers; i++)
        {
            playerArray[i] = new player();
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
}
