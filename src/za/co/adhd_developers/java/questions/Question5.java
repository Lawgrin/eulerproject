package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

import java.math.BigInteger;

/**
 * Created by Grant on 2017/07/28.
 */
public class Question5 implements Question {

    int answer = 0;

    @Override
    public void doWork() {
        int number = 2;
        while (answer == 0) {
            boolean fits = true;
            for (int i = 20; i > 0; i--) {
                if (number % i != 0) {
                    fits = false;
                    break;
                }
            }

            if (fits) {
                this.answer = number;
            } else {
                number += 2;
            }
        }
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 5");
        System.out.println("Smallest Multiple 1 - 20: " + this.answer);
        System.out.println("=================================");
    }
}
