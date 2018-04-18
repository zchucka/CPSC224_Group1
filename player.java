/**
 * Class player for Farkle.
 * This class handles tracking score
 * 
 *      Methods: add points
 * 
 * CPSC224-01 Spring 2018
 * 
 * @Authors Bo Volwiler
 *          Zach Chucka
 *          Olivia Hassebrock
 * @Version V1 3/7/18
 * 
 */

public class player
{
    private int points = 0;
    private String playerName;

    /**
     * Adds points to the player's score
     * 
     * @param the points to be added
     * 
     * @returns
     * 
     */
    public void addPoints(int toAdd)
    {
        points = points + toAdd;
    }

    /**
     * Sets the player's name
     * 
     * @param the name name of the player
     * 
     * @returns
     */
    public void setName(String name)
    {
        playerName = name;
    }

    /**
     * Return's the player's name
     * 
     * @param
     * 
     * @returns the player's name
     */
    public String getName()
    {
        return playerName;
    }
}