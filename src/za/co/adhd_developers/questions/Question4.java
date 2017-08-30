package za.co.adhd_developers.questions;

import za.co.adhd_developers.tools.Question;
import za.co.adhd_developers.tools.Utils;

/**
 * Created by Grant on 2017/07/28.
 */
public class Question4 implements Question
{
    int limit = 999;
    int answer = 0;

    int d1 = 0;
    int d2 = 0;

    @Override
    public void doWork()
    {
        for (int i = this.limit; i >= 100; i--)
        {
            for (int j = this.limit; j >= 100; j--)
            {
                int possible = i * j;

                if (Utils.isPalindromic(String.valueOf(possible)))
                {
                    if (possible > this.answer)
                    {
                        this.answer = possible;
                        this.d1 = i;
                        this.d2 = j;
                    }
                }
            }
        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 4");
//        System.out.println("=================================");
//        System.out.println(this.d1);
//        System.out.println(this.d2);
        System.out.println("This is a palindrome: " + this.answer);
        System.out.println("=================================");
    }
}
