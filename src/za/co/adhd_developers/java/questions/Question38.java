package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

public class Question38 implements Question
{
    private int answer = 0;
    @Override
    public void doWork()
    {
        for (int base = 0; base < 1000000; base++)
        {
            String hold = "";
            hold += String.valueOf(base*1);
            hold += String.valueOf(base*2);
            int n = 2;

            while (hold.length() < 9)
            {
                hold += String.valueOf(base * n);
                n++;
            }

            if (Utils.isPandigital(hold, 9))
            {
                int newPossible = Integer.valueOf(hold);
                if (newPossible > this.answer)
                {
                    this.answer = newPossible;
                }
            }
        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 38");
        System.out.println("The largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer with (1,2, ... , n) where n > 1, is: " + this.answer);
        System.out.println("=================================");
    }
}
