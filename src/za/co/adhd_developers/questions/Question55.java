package za.co.adhd_developers.questions;

import za.co.adhd_developers.tools.Question;
import za.co.adhd_developers.tools.Utils;

import java.math.BigInteger;

public class Question55 implements Question
{
    private int limit = 10000;
    private int answer = 0;

    @Override
    public void doWork()
    {
        for (int i = 0; i < this.limit; i++)
        {
            BigInteger currNumber = new BigInteger(String.valueOf(i));
            int count = 1;
            boolean hasPalindromic = false;
            while (count < 51)
            {
                BigInteger other = new BigInteger(Utils.reverse(currNumber.toString()));

                currNumber = currNumber.add(other);

                if (Utils.isPalindromic(currNumber.toString()))
                {
                    hasPalindromic = true;
                    break;
                }
                count++;
            }

            if (!hasPalindromic)
            {
                this.answer++;
            }
        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 55");
        System.out.println("The number of Lychrel numbers that are below ten-thousand, is: " + this.answer);
        System.out.println("=================================");
    }
}
