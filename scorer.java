public class scorer {
    private int roundScore = 0;
    hand playerHand;
    private int[] frequencyArray = new int[6];

    /*
     * creates an object that contains a hand object of dice that need to be score and a round score
     * this object is used in place of a scorecard to calculate the round scores
     * @param a hand object
     * @returns an object of class scorer
     * @throws nothing
     */
    public scorer(hand player)
    {
        playerHand = new hand(false,player);

        /*for(int k = 0; k < playerHand.numOfDice; k++)
        {
            playerHand.cupOfDice[k] = player.cupOfDice[k].clone();
        }*/

        setFrequencyArray();
    }

    /*
     * calculates and returns the score for the given hand
     * @param a hand object
     * @returns an object of class scorer
     * @throws nothing
     */
    public int Score()
    {
        calculateScore();
        System.out.println("Calculating score");
        return roundScore;
    }

    /*
	 * calculates the score of a given hand and inserts it into the data member round score
	 * @param none
	 * @returns nothing
	 * @throws nothing
	 */
    public void calculateScore()
    {
        if(checkValidity())
        {
            if (playerHand.numOfDice >= 6)
            {
                scoreSixDice();
            }

            if (playerHand.numOfDice >= 5)
            {
                scoreFiveDice();
            }

            if (playerHand.numOfDice >= 4)
            {
                scoreFourDice();
            }

            if (playerHand.numOfDice >= 3)
            {
                scoreThreeDice();
            }

            // check for 1's and 5's
            if (playerHand.numOfDice >= 0)
            {
                scoreOneDice();
            }

            System.out.println("your round score is: " + roundScore);
        }
    }

    /*
	 * a helper function for calculate score that scores the dice for any different type of six scoring dice
	 * @param an integer array that describes the frequency of the sides of the hand
	 * @returns nothing
	 * @throws nothing
	 */
    private void scoreSixDice()
    {
        Boolean isStraight = true;
        Boolean hasFourOfAKind = false;
        Boolean hasPair = false;

        int counter = 0;

        //check for straight
        for (int k = 0; k < 6; k++)
        {
            if(frequencyArray[k] == 0 || frequencyArray[k] > 1)
            {
                isStraight = false;
            }
        }
        if (isStraight == true)
        {
            roundScore += 1500;
            for (int k = 0; k < 6; k++)
            {
                frequencyArray[k] = 0;
            }
        }

        //six of a kind
        for (int k = 0; k < 6; k++)
        {
            if (frequencyArray[k] == 6)
            {
                roundScore += 3000;
                frequencyArray[k] = 0;
            }
        }

        // three pairs
        for (int k = 0; k < 6; k++)
        {
            if (frequencyArray[k] == 2)
            {
                counter += 1;
            }
        }
        if (counter == 3)
        {
            roundScore += 1500;
            counter = 0;
            for (int k = 0; k < 6; k++)
            {
                frequencyArray[k] = 0;
            }
        }

        //two sets of three of a kind
        for(int k = 0; k < 6; k++)
        {
            if (frequencyArray[k] == 3)
            {
                counter += 1;
            }
        }
        if (counter == 2)
        {
            roundScore += 2500;
        }

        //four of a kind and a pair
        for (int k = 0; k < 6; k++)
        {
            if (frequencyArray[k] == 4)
            {
                hasFourOfAKind = true;
            }
            if (frequencyArray[k] == 2)
            {
                hasPair = true;
            }
        }
        if (hasFourOfAKind && hasPair)
        {
            roundScore += 1500;
            for (int k = 0; k < 6; k++)
            {
                frequencyArray[k] = 0;
            }
        }
    }

    /*
	 * a helper function for calculate score that checks for a five of a kind and adds it to score
	 * @param an integer array that describes the frequency of the sides of the hand
	 * @returns nothing
	 * @throws nothing
	 */
    private void scoreFiveDice()
    {
        // check for five of a kind
        for (int k = 0; k < 6; k++)
        {
            if (frequencyArray[k] >= 5)
            {
                roundScore += 2000;
                frequencyArray[k] = frequencyArray[k] - 5;
            }
        }
    }

    /*
	 * a helper function for calculate score that checks for a four of a kind and adds it to score
	 * @param an integer array that describes the frequency of the sides of the hand
	 * @returns nothing
	 * @throws nothing
	 */
    private void scoreFourDice()
    {
        // check for four of a kind
        for (int k = 0; k < 6; k++)
        {
            if (frequencyArray[k] >= 4)
            {
                roundScore += 1000;
                frequencyArray[k] = frequencyArray[k] - 4;
            }
        }
    }

    /*
	 * a helper function for calculate score that checks for a three of a kind and adds it to score
	 * @param an integer array that describes the frequency of the sides of the hand
	 * @returns nothing
	 * @throws nothing
	 */
    private void scoreThreeDice()
    {
        // check for three of a kind
        for (int k = 0; k < 6; k++)
        {
            if (frequencyArray[k] >= 3)
            {
                if (k == 0)
                {
                    roundScore += frequencyArray[k] * 300;
                } else {
                    roundScore += frequencyArray[k] * k * 100;
                }
                frequencyArray[k] = frequencyArray[k] - 3;
            }
        }
    }

    /*
	 * a helper function for calculate score that checks for any remaining ones and fives and adds it to score
	 * @param an integer array that describes the frequency of the sides of the hand
	 * @returns nothing
	 * @throws nothing
	 */
    private void scoreOneDice()
    {
        if(frequencyArray[0] >= 1) // if there are ones
        {
            roundScore += frequencyArray[0] * 100;
            frequencyArray[0] = 0;
        }

        if (frequencyArray[4] >= 1) // if there are fives
        {
            roundScore += frequencyArray[4] * 50;
            frequencyArray[4] = 0;
        }
    }

    /*
	 * a function that checks if the hand given can be a valid score
	 * @param a hand that is being scored
	 * @returns a boolean variable based on if the hand can be scored in a valid manner
	 * @throws nothing
	 */
    public boolean checkValidity()
    {
        if(checkForFivesOrOnes())
        {
            return true;
        }
        else if(checkForThreeOfAKind())
        {
            return true;
        }
        else if(checkForThreePairs())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /*
	 * a helper function for check if valid that returns true if there are ones and fives in the hand
	 * @param an integer array that describes the frequency of the sides of the hand
	 * @returns true if there are ones or fives and false if there isnt
	 * @throws nothing
	 */
    private boolean checkForFivesOrOnes()
    {
        if(frequencyArray[0] > 0 || frequencyArray[4] > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /*
	 * a helper function for check if valid that returns true if there is a three of a kind in the hand
	 * @param an integer array that describes the frequency of the sides of the hand
	 * @returns true if there is a three of a kind and false if there isnt
	 * @throws nothing
	 */
    private  boolean checkForThreeOfAKind()
    {
        if (playerHand.numOfDice >= 3)
        {
            for (int i = 0; i < 6; i++)
            {
                if (frequencyArray[i] >= 3)
                {
                    return true;
                }
            }
        }

        return false;
    }

    /*
	 * a helper function for check if valid that returns true if there are three pairs in the hand
	 * @param an integer array that describes the frequency of the sides of the hand
	 * @returns true if there are three pairs and false if there isnt
	 * @throws nothing
	 */
    private  boolean checkForThreePairs()
    {
        int pairs = 0;

        if (playerHand.numOfDice == 6)
        {
            for (int i = 0; i < 6; i++)
            {
                if (frequencyArray[i] == 2)
                {
                    pairs++;
                }
            }
        }

        return (pairs == 3);
    }

    /*
	 * a function that creates a frequency array out of the data member hand
	 * @param noothing
	 * @returns an integer array that describes the frequency of the sides of the hand
	 * @throws nothing
	 */
    private  void setFrequencyArray()
    {
        for(int i = 0; i < 6; i++)
        {
            frequencyArray[i] = 0;
        }

        for (int i = 0; i < playerHand.numOfDice; i++)
        {
            int value = playerHand.displayTheDiceValue(i);
            frequencyArray[value - 1]++;
        }
    }

    public void printFreq()
    {
        for(int i = 0; i < 6; i++)
        {
            System.out.println((i + 1) + ": " + frequencyArray[i]);
        }
    }
}