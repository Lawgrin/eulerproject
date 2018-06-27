package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

public class Question36 implements Question
{
    private int limit = 1000000;

    private int answer = 0;

    @Override
    public void doWork()
    {
        for (int currNumb = 0; currNumb < this.limit; currNumb++)
        {
            String base10 = String.valueOf(currNumb);

            if (base10.startsWith("0") || base10.endsWith("0"))
            {
                continue;
            }

            if (!Utils.isPalindromic(base10))
            {
                continue;
            }

            String base2 = Long.toString(currNumb, 2);

            if (base2.startsWith("0") || base2.endsWith("0"))
            {
                continue;
            }

            if (!Utils.isPalindromic(base2))
            {
                continue;
            }

            this.answer += currNumb;
        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 36");
        System.out.println("The sum of all numbers that are palindromic in base 10 and base 2, is: " + this.answer);
        System.out.println("=================================");
    }
}
