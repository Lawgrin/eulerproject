package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;

import java.util.ArrayList;
import java.util.Hashtable;

public class Question34 implements Question {

    private Hashtable<String, Integer> factorals = new Hashtable<>();

    private ArrayList<Integer> allSums = new ArrayList<>();

    private int answer = 0;

    @Override
    public void doWork() {
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                factorals.put("0", 1);
            } else {
                int runningTotal = 1;
                for (int j = 1; j <= i; j++) {
                    runningTotal = runningTotal * j;
                }

                factorals.put(String.valueOf(i), runningTotal);
            }
        }

        for (int target = 3; target < 1000000; target++) {
            int runningTotal = 0;

            String[] digits = String.valueOf(target).split("|");

            boolean isEqual = true;

            for (String digit : digits) {
                int toAdd = factorals.get(digit);

                if (runningTotal + toAdd > target) {
                    isEqual = false;
                    break;
                } else {
                    runningTotal = runningTotal + toAdd;
                }
            }

            if (runningTotal != target) {
                isEqual = false;
            }

            if (isEqual) {
                this.allSums.add(runningTotal);
            }
        }

        for (int i = 0; i < this.allSums.size(); i++) {
            this.answer += this.allSums.get(i);
        }
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 34");
        System.out.println("The sum of all numbers which are equal to the sum of the factorial of their digits, is: " + this.answer);
        System.out.println("=================================");
    }
}
