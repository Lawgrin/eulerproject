package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;

import java.math.BigInteger;

/**
 * Created by Grant on 2017/07/31.
 */
public class Question16 implements Question {

    long answer = 0;

    @Override
    public void doWork() {
        BigInteger digitSum = new BigInteger("2");
        digitSum = digitSum.pow(1000);

        String largeNumber = digitSum.toString();

        for (int i = 0; i < largeNumber.length(); i++) {
            char[] num = new char[1];
            num[0] = largeNumber.charAt(i);

            String strNum = new String(num);

            if (strNum.length() == 1) {
                this.answer = this.answer + Long.parseLong(strNum);
            }
        }
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 16");
        System.out.println("The sum of the numbers from 2^1000 is: " + this.answer);
        System.out.println("=================================");
    }
}
