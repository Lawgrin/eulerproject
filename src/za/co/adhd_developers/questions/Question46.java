package za.co.adhd_developers.questions;

import za.co.adhd_developers.tools.Question;
import za.co.adhd_developers.tools.Utils;

public class Question46 implements Question
{
    private long answer = 0;

    @Override
    public void doWork()
    {
        long oddNumber = 1;
        long otherNumber = 1;
        long currPrime = 3;

        long possible = 0;

        while (this.answer == 0)
        {
            if (Utils.isPrime(oddNumber))
            {
                oddNumber += 2;
                continue;
            }
            long tmp = oddNumber - currPrime;

            if (tmp < 1)
            {
                if (possible == 0)
                {
                    oddNumber+=2;
                }
                else
                {
                    this.answer = possible;
                }
                continue;
            }
            tmp = tmp;

            long sqrAns = 2*(otherNumber*otherNumber);

            if (tmp == sqrAns)
            {
                oddNumber+=2;
                currPrime = 3;
                otherNumber = 1;
                possible = 0;
                continue;
            }

            if (tmp > sqrAns)
            {
                possible = oddNumber;
                otherNumber++;
                continue;
            }

            currPrime = Utils.getNextPrime(currPrime);
            otherNumber = 1;

        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 45");
        System.out.println("The smallest odd composite that cannot be written as the sum of a prime and twice a square, is: "+this.answer);
        System.out.println("=================================");
    }
}
