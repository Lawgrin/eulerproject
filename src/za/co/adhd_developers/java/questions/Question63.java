package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

import java.math.BigInteger;

public class Question63 implements Question {
    private int answer = 0;

    @Override
    public void doWork() {
        for (int pow = 1; pow < 1000; pow++) {

            BigInteger counter = BigInteger.ZERO;

            int stringLeng = String.valueOf(pow).length();

            BigInteger ans = BigInteger.ZERO;

            stringLeng = Utils.log10(ans) + 1;

            while (ans.compareTo(BigInteger.ZERO) == 0 || stringLeng <= pow) {
                counter = counter.add(BigInteger.ONE);
                ans = counter.pow(pow);

                stringLeng = Utils.log10(ans) + 1;

                if (stringLeng == pow) {
                    this.answer++;
                }
            }
        }
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 63");
        System.out.println("The amount of n-digit positive integers that exist which are also an nth power, is: " + this.answer);
        System.out.println("=================================");
    }
}
