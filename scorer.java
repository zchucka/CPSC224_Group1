public class scorer {
	private int roundScore = 0;
	hand playerHand;
	
	public scorer(hand player)
	{
		playerHand.numOfDice = player.numOfDice;
		
		for(int k = 0; k < playerHand.numOfDice; k++)
		{
			playerHand.cupOfDice[k] = player.cupOfDice[k];
		}
	}
	
	// returns the score of hand
	public int Score()
	{
		calculateScore();
		return roundScore;
	}
	
	// calculates the score from hand
	public void calculateScore()
	{
		int[] frequencyArray = new int[playerHand.numOfDice];
		
		for (int i = 0; i < 6; i++)
		{
			int value = playerHand.displayTheDiceValue(i);
			frequencyArray[value - 1]++;
		}
		
		if(true)
		{
			if (playerHand.numOfDice >= 6)
			{
				//check for straight, six of a kind, three pairs, two sets of three of a kind,four of a kind and a pair
			}
			if (playerHand.numOfDice >= 5)
			{
				// check for five of a kind
			}
			if (playerHand.numOfDice >= 4)
			{
				// check for four of a kind
			}
			if (playerHand.numOfDice >= 3)
			{
				// check for three of a kind
			}
			
			// check for 1's and 5's
			if (frequencyArray[0] > 0)
			{
				
			}
		}
	}
	
	/*
	private class checkIfValid
	{
		boolean isValid;
		
	    

	    private boolean checkForFivesOrOnes(int[] frequencyArray)
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

	    private boolean checkForThreeOfAKind(int[] frequencyArray)
	    {
	        for (int i = 0; i < 6; i++)
	        {
	            if (frequencyArray[i] >= 3)
	            {
	                return true;
	            }
	        }

	        return false;
	    }

	    private boolean checkForThreePairs(int[] frequencyArray)
	    {
	        int pairs = 0;

	        for (int i = 0; i < 6; i++)
	        {
	            if (frequencyArray[i] == 2)
	            {
	                pairs++;
	            }
	        }

	        return (pairs == 3);
	    }

	    public boolean checkValidity(hand myHand)
	    {
	        int frequencyArray[] = new int[6];
	        
	        setFrequency(myHand, frequencyArray);

	        if(checkForFivesOrOnes(frequencyArray))
	        {
	            return true;
	        }
	        else if(checkForThreeOfAKind(frequencyArray))
	        {
	            return true;
	        }
	        else if(checkForThreePairs(frequencyArray))
	        {
	            return true;
	        }
	        else 
	        {
	            return false;
	        }
	    }
	}
	
	*/
	
}
