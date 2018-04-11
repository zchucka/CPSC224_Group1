import java.util.Scanner;
import java.io.*;

public class yahtzee {
	static int numberOfSides;
	static int numberOfDice;
	static int numberOfRolls;
	
	/*
	 * asks the user if the the default settings stored in yahtzeeConfig.txt are 
	 * the settings the user wants to play with. if they opt to change the settings
	 * the new settings are updated into the text file
	 * @param array of strings that isn't used
	 * @returns nothing
	 * @throw file not found exception if the input file cannot be found
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		Scanner infile = new Scanner(new File("yahtzeeConfig.txt"));
		Scanner console = new Scanner(System.in);
		
		String input;
		
		input = infile.nextLine();
		numberOfSides = Integer.parseInt(input);
		
		input = infile.nextLine();
		numberOfDice = Integer.parseInt(input);
		
		input = infile.nextLine();
		numberOfRolls = Integer.parseInt(input);
		
		
		
		System.out.println("you are playing with " + numberOfDice + " " + numberOfSides + "-sided dice");
		System.out.println("you get " + numberOfRolls + " rolls per hand");
		
		System.out.println("enter 'y' if you would like to change the configuration ");
		input = console.nextLine();
		char[] inputArray = input.toCharArray();
		
		// need to write it into the file
		if (inputArray[0] == 'y') 
		{
			PrintStream outfile = new PrintStream("yahtzeeConfig.txt");
			
			System.out.print("enter the number of sides on each die ");
			numberOfSides = console.nextInt();
			outfile.println(numberOfSides);
			
			System.out.print("enter the number of dice in play ");
			numberOfDice = console.nextInt();
			outfile.println(numberOfDice);
			
			System.out.print("enter the number of rolls per hand ");
			numberOfRolls = console.nextInt();
			outfile.println(numberOfRolls);
			
			outfile.close();
		}
		
		runGame(numberOfDice, numberOfSides, numberOfRolls);
		
		console.close();
		infile.close();
		
	}
	
	/*
	 * using both scoreCard.java and hand.java, runGame takes the user through 13 rounds
	 * of yahtzee
	 * @param number of dice, number of sides, and number of rolls
	 * @returns nothing
	 * @throw no exceptions thrown
	 */
	public static void runGame(int numOfDice, int numOfSides, int numOfRolls)
	{
		Scanner console = new Scanner(System.in);
		hand player1 = new hand(numOfDice, numOfSides, numOfRolls);
		scoreCard scorePlayer1 = new scoreCard(numOfSides);
		String input = "n";
		int roundNumber = 0;
	
		char[] inputArray;
		
		while (roundNumber < 13) // for the appropriate number of rounds
		{
			int[] frequency = new int[numberOfSides];
			int totalDiceValue = 0;
			int index = 0;	
			Boolean isValidScore = false;
			Boolean isDoneRolling = false;
			
			player1.roll(input);
			System.out.print("Your roll was : ");
			player1.displayRoll();
		
			// allows the user to either continue rolling under the max number of rolls 
			// or enter yyyyy to stop rolling
			while (!isDoneRolling && player1.numberOfRolls() < numberOfRolls)
			{
				System.out.println("enter dice to keep (y or n) ");
				input = console.next();
				inputArray = input.toCharArray();
				isDoneRolling = true;
				
				for (int k = 0; k < inputArray.length; k++)
				{
					if (inputArray[k] == 'n')
					{
						isDoneRolling = false;
					}
						
				}
				
				if(!isDoneRolling)
				{
					player1.roll(input);
					System.out.print("Your roll was : ");
					player1.displayRoll();
				}
			}
			
			if(player1.sortTheRoll(numOfDice))
			{
				System.out.print("Here is you sorted hand : ");
				player1.displayRoll();
			}
			
			// put the dice values in a easier to access array
			// calculate the total value of the dice
			for (int k = 0; k < numberOfDice; k++)
			{
				frequency[player1.displayTheDiceValue(k) - 1]++;
				totalDiceValue = player1.displayTheDiceValue(k) + totalDiceValue;
			}
			
			displayPossibleScores(scorePlayer1, player1, frequency, totalDiceValue);
			
			while(!isValidScore)
			{
				System.out.println("Which line do you want to enter your score in? (enter the description, if nothing possible, type none) ");
				String desc = console.nextLine();
				desc = console.nextLine();
				for (int k = 0; k < numberOfSides + 7; k++) // loop over the categories to find the matching description
				{
					if (desc.equals(scorePlayer1.giveDesc(k))) // if the given description matches ours
					{
						isValidScore = true;
						index = k;
					}
				}
				if (!isValidScore && isPossibleScore(scorePlayer1, player1, index, frequency))
				{
					System.out.println("The description provided did not match any possible description");
				}
				if (desc.equals("none"))
				{
					isValidScore = true;
					index = -1;
				}
			}
			
			// rechecks if the spot is a possible score
			if (index < numberOfSides && index >= 0)
			{
				scorePlayer1.addScore(index, frequency[index] * (index+1));
			} else if (index >= numberOfSides)
			{
				if (index == numberOfSides + 2)
				{
					scorePlayer1.addScore(index, 25);
				} else if (index == numberOfSides + 3) {
					scorePlayer1.addScore(index, 30);
				} else if (index == numberOfSides + 4)
				{
					scorePlayer1.addScore(index, 40);
				} else if (index == numberOfSides + 5) {
					scorePlayer1.addScore(index, 50);
				} else {
					scorePlayer1.addScore(index, totalDiceValue);
				}
			} 
			
			player1.resetRoll();
			scorePlayer1.display();
			roundNumber++;
			System.out.println("");
		}
		
		System.out.println("Your final score was " + totalScore(scorePlayer1));

		console.close();
	}
	
	/*
	 * finds if the
	 * @param the scoreCard and hand objects that were use to run the game
	 *        the index where the score is, the frequency array that describes the dice values, and the total value of the die
	 * @returns true if the entry is a possible score
	 *          false if the entry isnt a possible score
	 * @throw no exceptions thrown
	 */
	public static Boolean isPossibleScore(scoreCard scoreSheet, hand player, int index, int[] frequency)
	{
		int counter = 0;
		
		if (index < numberOfSides) // adds the score to the upper score card
		{
			if(frequency[index] > 0 && scoreSheet.getScore(index) == 0)
			{
				return true;
			} else {
				return false;
			}
		} else if (index == numberOfSides) // three of a kind
		{
			for(int k = 0; k < numberOfSides; k++)
			{
				if (frequency[k] >= 3 && scoreSheet.getScore(index) == 0)
				{
					return true;
				}
			}
			return false;
		} else if (index == numberOfSides + 1) // four of a kind
		{
			for(int k = 0; k < numberOfSides; k++)
			{
				if (frequency[k] >= 4 && scoreSheet.getScore(index) == 0)
				{
					return true;
				}
			}
			return false;
		} else if (index == numberOfSides + 2) // full house
		{
			for(int k = 0; k < numberOfSides; k++)
			{
				for (int j = 0; j < numberOfSides; j++)
				{
					if (frequency[k] >= 3)
					{
						if (frequency[j] >= 2 && j != k && scoreSheet.getScore(index) == 0)
						{
							return true;
						}
					}
				}
			}
			return false;
		} else if (index == numberOfSides + 3) // small straight
		{
			for(int k = 0; k < numberOfSides; k++)
			{
				if (frequency[k] > 0)
				{
					counter++;
				} else {
					counter = 0;
				}
					
				if (counter >= 4 && scoreSheet.getScore(index) == 0) // small straight
				{
					return true;
				}
			}
			return false;
		} else if (index == numberOfSides + 4)// large straight
		{
			for(int k = 0; k < numberOfSides; k++)
			{
				if (frequency[k] > 0)
				{
					counter++;
				} else {
					counter = 0;
				}
				
				if (counter >= 5 && scoreSheet.getScore(index) == 0) // large straight
				{
					return true;
				}
			}
			return false;
		} else if (index == numberOfSides + 5) // yahtzee
		{
			for(int k = 0; k < numberOfSides; k++)
			{
				if (frequency[k] >= 5 && scoreSheet.getScore(index) == 0) 
				{
					return true;
				}
			}
			return false;
		} else if (index == numberOfSides + 6) // chance
		{
			if (scoreSheet.getScore(index) > 0)
			{
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	/*
	 * displays the possible locations for the player to score
	 * @param the scoresheet and hand plus an array of the frequency of each side and sum of the die's value
	 * @returns nothing
	 * @throws nothing
	 */
	public static void displayPossibleScores(scoreCard scoreSheet, hand player, int[] freq, int diceValue)
	{
		System.out.println("The possible lines to score are: ");
		for (int k = 0; k < numberOfSides + 7; k++)
		{
			if (isPossibleScore(scoreSheet, player, k, freq) && k < numberOfSides)
			{
				System.out.println("the " + scoreSheet.giveDesc(k) + " line gives a score of " + freq[k] * (1+k));
			} else if (isPossibleScore(scoreSheet, player, k, freq))
			{
				if (k == numberOfSides + 2)
				{
					System.out.println("the " + scoreSheet.giveDesc(k) + " line gives a score of 25");
				} else if (k == numberOfSides + 3) {
					System.out.println("the " + scoreSheet.giveDesc(k) + " line gives a score of 30");
				} else if (k == numberOfSides + 4)
				{
					System.out.println("the " + scoreSheet.giveDesc(k) + " line gives a score of 40");
				} else if (k == numberOfSides + 5) {
					System.out.println("the " + scoreSheet.giveDesc(k) + " line gives a score of 50");
				} else {
					System.out.println("the " + scoreSheet.giveDesc(k) + " line gives a score of " + diceValue);
				}
			}
		}
	}
	
	/*
	 * returns the total final score for the player
	 * @param the scoresheet
	 * @returns the sum of the scoresheet
	 * @throws no exception
	 */
	public static int totalScore(scoreCard scoreSheet)
	{
		int sum = 0;
		for (int k = 0; k < numberOfDice + 7; k++)
		{
			sum = sum + scoreSheet.getScore(k);
		}
		return sum;
	}
}