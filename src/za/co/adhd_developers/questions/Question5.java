package za.co.adhd_developers.questions;

import za.co.adhd_developers.tools.Question;

import java.util.Date;

/**
 * Created by Grant on 2017/07/28.
 */
public class Question5 implements Question
{
    int answer = 0;

    @Override
    public void doWork()
    {
        int number = 380;
        int count = 1;
        while (answer == 0)
        {
            boolean fits = true;

            for (int i = 20; i > 0; i--)
            {
                if (number % i != 0)
                {
                    fits = false;
                }

                if (!fits)
                {
                    i = 0;
                }
            }

            if (fits)
            {
                this.answer = number;
            }
            else
            {
                number+=380;
                count++;
            }
        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 5");
//        System.out.println("=================================");
//        System.out.println(this.d1);
//        System.out.println(this.d2);
        System.out.println("Smallest Multiple 1 - 20: " + this.answer);
        System.out.println("=================================");
    }
}
