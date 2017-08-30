package za.co.adhd_developers.questions;

import za.co.adhd_developers.tools.Question;
import za.co.adhd_developers.tools.Utils;

import java.math.BigInteger;

/**
 * Created by Grant on 2017/08/01.
 */
public class Question20 implements Question
{
    long answer = 0;

    @Override
    public void doWork()
    {
        BigInteger factoralResult = Utils.factorial(new BigInteger("100"));

        this.answer = Utils.sumTheNumber(factoralResult.toString());
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 20");
//        System.out.println("=================================");
//        System.out.println(this.correctPaths);
        System.out.println("The sum of the digits in the number 100!, is: "+this.answer);
        System.out.println("=================================");
    }
}
