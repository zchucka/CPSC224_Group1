public class scoreCard {
	int[] upperCard = new int[10];
	int[] lowerCard = new int[7];
	int[] upperDescCard = new int[10];
	String[] lowerDescCard = new String[7];
	int numOfSides;
	
	/*
	 * creates a score card containing four arrays that represent the different
	 * catergories on a scorecard for yahtzee and an integer containing the number 
	 * of sides on the die
	 * @param number of sides the die has
	 * @returns an object containing the four arrays described above and the integer
	 * 			containing the number of sides
	 * @throw no exceptions thrown
	 */
	public scoreCard(int sides)
	{
		int counter = 0;
		numOfSides = sides;
		
		// enters the descriptions for each of the upperCard's values
		while(counter < sides)
		{
			upperDescCard[counter] = counter + 1;
			counter++;
		}

		lowerDescCard [0] = "3 of a Kind";
		lowerDescCard [1] = "4 of a Kind";
		lowerDescCard [2] = "Full House";
		lowerDescCard [3] = "Small Straight";
		lowerDescCard [4] = "Large Straight";
		lowerDescCard [5] = "Yahtzee";
		lowerDescCard [6] = "Chance";
	}
	
	/*
	 * adds a score to the either the upper or lower part of the card
	 * @param index where the score is to be added and the score
	 * @returns nothing
	 * @throw no exceptions thrown
	 */
	public void addScore(int index, int score)
	{
		// index is if the two array are together so we must check if it is bigger than the top array
		// if it is, we should subtract from index to find the correct index of the bottom array
		if (index >= numOfSides)
		{
			index = index - numOfSides;
			lowerCard[index] = score;
		} else {
			upperCard[index] = score;
		}
	}
	
	/*
	 * displays the score card using the score values and the description
	 * @param none
	 * @returns nothing
	 * @throw no exceptions thrown
	 */
	public void display()
	{
		// looping over and printing the top scoreCard
		for (int k = 0; k < numOfSides; k++)
		{
			System.out.println("Score " + upperCard[k] + " on the " + upperDescCard[k] + " line");
		}
		
		// looping over and pring the bottom scoreCard
		for (int k = 0; k < 7; k++)
		{
			System.out.println("Score " + lowerCard[k] + " on the " + lowerDescCard[k] + " line");
		}
	}
	
	/*
	 * returns the description contained at the index specified
	 * Note: i didn't use this function but I think it'll come in handy for the next assignment
	 * @param index where you want the description
	 * @returns nothing
	 * @throw no exceptions thrown
	 */
	public String giveDesc(int index)
	{
		// index is if the two array are together so we must check if it is bigger than the top array
		// if it is, we should subtract from index to find the correct index of the bottom array
		if (index >= numOfSides)
		{
			index = index - numOfSides;
			return lowerDescCard[index];
		} else {
			return String.valueOf(upperDescCard[index]); 
		}
	}
	
	/*
	 * returns the score of the specified index
	 * @param the index of the interested score
	 * @returns the score
	 * @throw no exceptions thrown
	 */
	public int getScore(int index)
	{
		if (index >= numOfSides)
		{
			index = index - numOfSides;
			return lowerCard[index];
		} else {
			return upperCard[index]; 
		}
	}
}
