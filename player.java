
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

public class player implements Cloneable
{
    private int points = 0;
    private int playerName;


    /**
     * constructor for object player
     */
    public player() {
        points = 0;
    }

    /**
     * Adds points to the player's score
     *
     * @parameter the points to be added
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
     * @parameter the name name of the player
     *
     * @returns
     */
    public void setName(int name)
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
    public int getName()
    {
        return playerName;
    }

    /*
     * returns the players score
     */
    public int getScore()
    {
        return points;
    }

    /*
     * creates a deep copy of the player
     * @param none
     * @returns a deep copy of the player
     * @throw CloneNotSupportedException if the fields of dice are not cloneable
     */
    public player clone()
    {
        try {
            return (player) super.clone();
        } catch (CloneNotSupportedException e)
        {
            return null;
        }

    }
}