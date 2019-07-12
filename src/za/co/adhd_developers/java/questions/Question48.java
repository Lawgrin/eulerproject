package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;

import java.math.BigInteger;

public class Question48 implements Question {

    private BigInteger answer = BigInteger.ZERO;

    @Override
    public void doWork() {
        BigInteger runningTotal = BigInteger.ZERO;
        for (int n = 1; n <= 1000; n++) {
            runningTotal = runningTotal.add(BigInteger.valueOf(n).pow(n));
        }

        this.answer = runningTotal.mod(BigInteger.valueOf(10000000000L));
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 48");
        System.out.println("The last 10 digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000, is: " + this.answer.toString());
        System.out.println("=================================");
    }
}
