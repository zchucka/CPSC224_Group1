import java.util.*;

public class dice implements Cloneable {
	/*
	 * creates an object called dice that contains a roll number that is an integer
	 * and has a function that reassigns the dice a random value from one to six 
	 * and a function that returns the value.
	 * dice is also cloneable
	 */
	int rollNumber = -1;
	int numOfSides;
	Boolean continueRolling = true;
	
	/*
	 * creates a deep copy of the dice
	 * @param none
	 * @returns a deep copy of the dice
	 * @throw CloneNotSupportedException if the fields of dice are not cloneable
	 */
	public dice clone() 
	{
		try {
			return (dice) super.clone();
		} catch (CloneNotSupportedException e)
		{
			return null;
		}
		
	}
	
	/*
	 * creates an object called dice that contains a specified number of sides, a boolean if you should keep rolling, and a roll value
	 * @param number of sides you want the dice to have
	 * @returns an object of dice with the specified sides
	 * @throw nothing
	 */
	public dice(int sides)
	{
		numOfSides = sides;
	}
	
	/*
	 * reassigns the roll number variable to a random variable between 1 and numberOfSides
	 * @param none
	 * @returns nothing
	 * @throw no exceptions thrown
	 */
	public void roll() 
	{
		Random rand = new Random();
		
		rollNumber = rand.nextInt(numOfSides) + 1;
	}
	
	/*
	 * returns the value of the dice
	 * @param none
	 * @returns the integer value contained in the dice
	 * @throw no exceptions thrown
	 */
	public int diceValue() 
	{
		return rollNumber;
	}
	
	/*
	 * sets the boolean variable to the specified input
	 * @param a boolean used to set the boolean member of dice
	 * @returns nothing
	 * @throw no exceptions thrown
	 */
	public void setRolling()
	{
		continueRolling = !continueRolling;
	}
	
	/*
	 * returns the value of continueRolling
	 * @param none
	 * @returns the value of the boolean member continueRolling
	 * @throws nothing
	 */
	public Boolean moreRolling()
	{
		return continueRolling;
	}
}
