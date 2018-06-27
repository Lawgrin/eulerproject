package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

import java.math.BigInteger;

public class Question53 implements Question
{
    private int answer = 0;
    @Override
    public void doWork()
    {
        for (int n = 1; n < 101; n++)
        {
            for (int r = 1; r <= n; r++)
            {
                BigInteger nFac = Utils.factorial(new BigInteger(String.valueOf(n)));
                BigInteger rFac = Utils.factorial(new BigInteger(String.valueOf(r)));

                BigInteger n_rFac = Utils.factorial(new BigInteger(String.valueOf(n-r)));

                BigInteger aboveLine = nFac;

                BigInteger belowLine = rFac.multiply(n_rFac);

                BigInteger nCr = aboveLine.divide(belowLine);

                if (nCr.compareTo(new BigInteger(String.valueOf(1000000))) == 1)
                {
                    this.answer++;
                }
            }
        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 51");
        System.out.println("The amount of time values of nCr are greater then 1 000 000, is: " + this.answer);
        System.out.println("=================================");
    }
}
