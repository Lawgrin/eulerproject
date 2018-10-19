package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

/**
 * Created by Grant on 2017/07/29.
 */
public class Question12 implements Question {

    int limit = 500;
    long answer = 0;

    @Override
    public void doWork() {
        long triangleNumber = 0;
        int count = 1;
        while (this.answer <= limit) {
            triangleNumber = triangleNumber + count;

            if (Utils.getFactors(triangleNumber).size() > limit) {
                this.answer = triangleNumber;
            }
            count++;
        }
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 12");
        System.out.println("The first triangle number to have over " + this.limit + " divisors, is: " + this.answer);
        System.out.println("=================================");
    }
}
