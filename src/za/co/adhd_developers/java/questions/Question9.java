package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;

/**
 * Created by Grant on 2017/07/28.
 */
public class Question9 implements Question
{
    int finalA = 0;
    int finalB = 0;
    int finalC = 0;

    long answer = 0;

    @Override
    public void doWork()
    {
        for (int a = 1; a <= 1000; a++)
        {
            for (int b = a+1; b <= 1000; b++)
            {
                for (int c = b+1; c <= 1000; c++)
                {
                    if (a+b+c == 1000)
                    {
                        double a2 = Math.pow(a, 2);
                        double b2 = Math.pow(b, 2);
                        double c2 = Math.pow(c, 2);

                        if ((a2+b2) == c2)
                        {
                            this.finalA = a;
                            this.finalB = b;
                            this.finalC = c;
                        }
                    }
                }
            }
        }

        this.answer = this.finalA * this.finalB * this.finalC;
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 9");
//        System.out.println("=================================");
//        System.out.println(this.d1);
//        System.out.println(this.d2);
        System.out.println("The product 'abc' is: " + this.answer);
        System.out.println("=================================");
    }
}
