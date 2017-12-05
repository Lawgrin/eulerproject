package za.co.adhd_developers.questions;

import za.co.adhd_developers.tools.Question;
import za.co.adhd_developers.tools.Utils;

import java.util.Hashtable;

public class Question37 implements Question
{
    private Hashtable<String, Boolean> isPrime = new Hashtable<>();

    private int truncatablePrimeCount = 0;

    private long answer = 0;
    
    @Override
    public void doWork()
    {
        int currNumber = 0;
        while (this.truncatablePrimeCount < 11)
        {
            currNumber++;
            String currNumberStr = String.valueOf(currNumber);

            if (!isLocalPrime(currNumberStr))
            {
                continue;
            }

            Boolean leftToRight = true;
            for (int i = 0; i < currNumberStr.length(); i++)
            {
                String tmp = currNumberStr.substring(0,currNumberStr.length()-i);

                leftToRight = isLocalPrime(tmp);

                if (!leftToRight)
                {
                    break;
                }
            }

            if (!leftToRight)
            {
                continue;
            }

            Boolean rightToLeft = true;
            for (int i = 0; i < currNumberStr.length(); i++)
            {
                String tmp = currNumberStr.substring(i);

                rightToLeft = isLocalPrime(tmp);

                if (!rightToLeft)
                {
                    break;
                }
            }

            if (!rightToLeft)
            {
                continue;
            }

            if (currNumber == 2 || currNumber == 3 || currNumber == 5 || currNumber == 7)
            {
                continue;
            }

            this.truncatablePrimeCount++;
            this.answer += Long.valueOf(currNumber);
        }
//        for (String item : this.truncatablePrime)
//        {
//            System.out.println(item);
//        }


    }

    private boolean isLocalPrime(String number)
    {
        if (this.isPrime.containsKey(number))
        {
            return this.isPrime.get(number);
        }

        if (Utils.isPrime(Long.valueOf(number)))
        {
            this.isPrime.put(number, true);
            return true;
        }

        this.isPrime.put(number, false);
        return false;
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 37");
        System.out.println("The sum of prime numbers that are truncatable from left to right and right to left, is: " + this.answer);
        System.out.println("=================================");
    }
}
