package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;

import java.math.BigInteger;

public class Question48 implements Question
{
    private String answer = "";
    @Override
    public void doWork()
    {
        BigInteger runningTotal = new BigInteger("0");
        for (int n = 1; n <= 1000; n++)
        {
            BigInteger toAdd = new BigInteger(String.valueOf(n));
            toAdd = toAdd.pow(n);

            runningTotal = runningTotal.add(toAdd);
        }

        String total = runningTotal.toString();

        this.answer = total.substring(total.length()-10);
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 49");
        System.out.println("The last 10 digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000, is: " + this.answer);
        System.out.println("=================================");
    }
}
