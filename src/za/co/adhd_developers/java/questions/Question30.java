package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;

public class Question30 implements Question {

    private int start = 2;
    private int end = 10000000;
    private int pow = 5;

    private int answer = 0;

    @Override
    public void doWork() {
        for (int i = this.start; i < this.end; i++) {
            String number = String.valueOf(i);

            String digits[] = number.split("|");

            int sum = 0;

            for (int j = 0; j < digits.length; j++) {
                sum += Math.pow(Integer.valueOf(digits[j]), this.pow);
            }

            if (sum == i) {
                this.answer += sum;
            }
        }
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 30");
        System.out.println("The sum of all numbers that can be written as the sum of fifth powers of their digits, is: " + this.answer);
        System.out.println("=================================");
    }
}
