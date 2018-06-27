package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

import java.math.BigInteger;

/**
 * Created by Grant on 2017/07/29.
 */
public class Question15 implements Question
{
    BigInteger gridSize = new BigInteger(String.valueOf("20"));
    BigInteger answer = new BigInteger("0");

    @Override
    public void doWork()
    {
        //See http://www.robertdickau.com/manhattan.html on how to work this out
        BigInteger top = Utils.factorial(this.gridSize.multiply(new BigInteger("2")));

        BigInteger bottom = Utils.factorial(this.gridSize);
        bottom = bottom.pow(2);

        this.answer = top.divide(bottom);
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 15");
//        System.out.println("=================================");
//        System.out.println(this.correctPaths);
        System.out.println("In a " + this.gridSize + "x" + this.gridSize + " grid there are : " + this.answer + " paths");
        System.out.println("=================================");
    }
}
