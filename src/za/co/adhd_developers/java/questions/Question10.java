package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

import java.util.Date;

/**
 * Created by Grant on 2017/07/28.
 */
public class Question10 implements Question {

    long limit = 2000000;
    long answer = 0;

    @Override
    public void doWork() {
        for (long i = this.limit; i > 0; i--) {
            if (Utils.newIsPrime(i)) {
                this.answer += i;
            }
        }
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 10");
        System.out.println("The sum of all primes below 2 000 000 is: " + this.answer);
        System.out.println("=================================");
    }
}
