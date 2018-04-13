public class checkIfValid
{
    public static void main(String args[])
    {
        hand myHand = new hand(6, 6, 3);
        myHand.roll("nnnnnn");
        myHand.displayRoll();

        if(checkValidity(myHand))
        {
            System.out.println("Valid Hand");
        }
        else
        {
            System.out.println("Not a valid hand");
        }
    }

    private static void setFrequency(hand myHand, int[] frequencyArray)
    {
        for (int i = 0; i < 6; i++)
        {
            int value = myHand.displayTheDiceValue(i);
            frequencyArray[value - 1]++;
        }
    }

    private static boolean checkForFivesOrOnes(int[] frequencyArray)
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

    private static boolean checkForThreeOfAKind(int[] frequencyArray)
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

    private static boolean checkForThreePairs(int[] frequencyArray)
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

    public static boolean checkValidity(hand myHand)
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
