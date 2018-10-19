package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;

/**
 * Created by Grant on 2017/07/28.
 */
public class Question6 implements Question {

    double sumOfSquare = 0;
    double squareOfSum = 0;

    long answer = 0;

    @Override
    public void doWork() {
        for (double i = 0; i <= 100; i++) {
            this.sumOfSquare = sumOfSquare + Math.pow(i, 2);
            this.squareOfSum += i;
        }

        this.squareOfSum = Math.pow(this.squareOfSum, 2);

        double lower = Math.min(this.squareOfSum, this.sumOfSquare);
        double upper = Math.max(this.squareOfSum, this.sumOfSquare);

        this.answer = (new Double(upper - lower)).longValue();
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 6");
        System.out.println("The difference is: " + this.answer);
        System.out.println("=================================");
    }
}
