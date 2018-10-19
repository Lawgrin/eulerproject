package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;

import java.math.BigInteger;

/**
 * Created by Grant on 2017/08/01.
 */
public class Question25 implements Question
{
    BigInteger current = new BigInteger("1");
    BigInteger previous = new BigInteger("1");

    int answer = 2;

    @Override
    public void doWork()
    {
        while (this.current.toString().length() < 1000)
        {
            BigInteger tmp = this.current;

            this.answer++;
            this.current = this.current.add(this.previous);

            this.previous = tmp;
        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 25");
        System.out.println("The index of the first term with 1000 digits, is: "+this.answer);
        System.out.println("=================================");
    }
}
