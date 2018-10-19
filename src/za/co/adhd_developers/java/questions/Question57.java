package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;

import java.math.BigInteger;

public class Question57 implements Question {

    private int limit = 1000;
    private int answer = 0;

    @Override
    public void doWork() {
        BigInteger two = new BigInteger(String.valueOf(2));

        for (int run = 0; run < this.limit; run++) {
            BigInteger numerator = new BigInteger(String.valueOf(1));
            BigInteger denominator = new BigInteger(String.valueOf(2));
            for (int i = 0; i < run; i++) {
                numerator = numerator.add(denominator.multiply(two));

                BigInteger tmp = denominator;

                denominator = numerator;
                numerator = tmp;
            }

            numerator = numerator.add(denominator);

            if (numerator.toString().length() > denominator.toString().length()) {
                answer++;
            }
        }
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 57");
        System.out.println("The amount of time the numerator has more digits then the denominator, is: " + this.answer);
        System.out.println("=================================");
    }
}
