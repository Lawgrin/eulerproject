package za.co.adhd_developers.questions;

import za.co.adhd_developers.tools.Question;

import java.math.BigInteger;

public class Question56 implements Question
{
    private int limit = 100;

    private int answer = 0;

    @Override
    public void doWork()
    {
        for (int a = 1; a < this.limit; a++)
        {
            for (int b = 1; b < this.limit; b++)
            {
                BigInteger bigInteger = new BigInteger(String.valueOf(a));
                bigInteger = bigInteger.pow(b);

                int runningTotal = 0;
                for (String digit : bigInteger.toString().split("|"))
                {
                    if (digit.matches("\\d"))
                    {
                        int d = Integer.valueOf(digit);

                        runningTotal += d;
                    }
                }

                if (this.answer < runningTotal)
                {
                    this.answer = runningTotal;
                }
            }
        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 55");
        System.out.println("The maximum digits sum of a^b where a,b < 100, is: " + this.answer);
        System.out.println("=================================");
    }
}
