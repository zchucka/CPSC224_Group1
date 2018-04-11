public class hand {
	dice[] cupOfDice;
	int numOfRolls = 0;
	int numOfDice = 6;
	int numOfSides = 6;
	
	/*
	 * creates the hand which contains an array of dice
	 * also assigns number of dice, sides, and the max number of rolls 
	 * @param number of dice, number of Sides, and number of rolls
	 * @returns an object containing an array of dice and two integer values
	 *          one containing the number of rolls allowed and the other containing 
	 *          the number of dice
	 * @throw no exceptions thrown
	 */
	public hand()
	{
		cupOfDice = new dice[6];
	}
	
	/*
	 * rolls the specified dice contained in the array
	 * @param a string containing y and n and a boolean variable, isDoneRolling, that 
	 *        says if the input wants the user to be done rolling
	 * @returns nothing
	 * @throw no exceptions thrown
	 */
	public void roll(Boolean[] input)
	{
		dice tempDice  = new dice(numOfSides);

		if (numOfRolls == 0) // if it is the first roll, the input doesn't matter
		{
			numOfRolls++;
			for (int k = 0;  k < numOfDice; k++)
			{
				tempDice.roll();
				cupOfDice[k] = tempDice.clone();
			}
		} else { // takes the input array and rolls the dice that correspond with the 'n' values
			
			numOfRolls++;
			for (int k = 0;  k < numOfDice; k++)
			{
				if (input[k] == true) 
				{
					cupOfDice[k].roll();
				}
			}
		}
	}
	
	/*
	 * displays the array of die
	 * @param none
	 * @returns nothing
	 * @throw no exceptions thrown
	 */
	public void displayRoll()
	{
		// loops over the dice in the array to print them
		for (int k = 0; k < numOfDice; k++)
		{
			System.out.print(cupOfDice[k].diceValue() + " ");
		}
		System.out.println();
	}
	
	/*
	 * returns the number of rolls allowed
	 * @param none
	 * @returns the number of rolls allowed
	 * @throw no exceptions thrown
	 */
	public int numberOfRolls()
	{
		return numOfRolls;
	}
	
	/*
	 * returns the value of the die at the specified index
	 * @param an index of the die that you want to know its value
	 * @returns the value of the die
	 * @throw no exceptions thrown
	 */
	public int displayTheDiceValue(int index)
	{
		return cupOfDice[index].diceValue();
	}
	
	/*
	 * sorts the die in the roll by numerical order
	 * @param number of die left to sort
	 * @returns if the array is sorted
	 * @throw no exceptions thrown
	 */
	public boolean sortTheRoll(int numOfUnsorted)
	{
		dice tempDice = new dice(numOfSides);

		for (int k = 0; k  < numOfUnsorted - 1; k++) //follows selection sort
		{
			if (cupOfDice[k].diceValue() > cupOfDice[k + 1].diceValue())
			{
				tempDice = cupOfDice[k].clone();
				cupOfDice[k] = cupOfDice[k+1].clone();
				cupOfDice[k+1] = tempDice.clone();
			}
		}
			
		if (numOfUnsorted == 1)
		{
			return true;
		} 
		
		return sortTheRoll(numOfUnsorted - 1);

	}
	
	/*
	 * resets the number of rolls to zero
	 * @param none
	 * @returns nothing
	 * @throws nothing
	 */
	public void resetRoll()
	{
		numOfRolls = 0;
	}
}
