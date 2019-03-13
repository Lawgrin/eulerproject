package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

/**
 * Created by Grant on 2017/07/28.
 */
public class Question7 implements Question {

    int limit = 10001;
    int answer = 0;

    @Override
    public void doWork() {
        int count = 0;
        int currNumber = 2;

        while (count != limit) {
            if (Utils.newIsPrime(currNumber)) {
                count++;
            }

            if (count != limit) {
                currNumber++;
            }
        }

        this.answer = currNumber;
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 7");
        System.out.println("The 10 001st prime number: " + this.answer);
        System.out.println("=================================");
    }
}
