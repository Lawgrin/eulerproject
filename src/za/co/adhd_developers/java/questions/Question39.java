package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;

import java.util.HashSet;
import java.util.Hashtable;

public class Question39 implements Question {

    private int endPoint = 1000;

    private Hashtable<Integer, HashSet<String>> allSolutions = new Hashtable<>();

    private int answer = 0;
    private int answerCount = 0;

    @Override
    public void doWork() {
        for (int target = 1; target <= this.endPoint; target++) {
            for (int a = 1; a < target; a++) {
                for (int b = 1; b < target; b++) {
                    if (a + b >= target) {
                        break;
                    }

                    double hypot = Math.sqrt(Math.pow(a,2.0)+Math.pow(b,2.0));

                    if (Double.isNaN(hypot)) {
                        continue;
                    }

                    double roundedHypot = Math.floor(hypot);

                    if (hypot > roundedHypot) {
                        continue;
                    }

                    int h = (int) roundedHypot;

                    if (a + b + h != target) {
                        continue;
                    } else {
                        String item = "{" + a + "," + b + "," + h + "}";

                        if (a < b) {
                            item = "{" + b + "," + a + "," + h + "}";
                        }

                        HashSet<String> tmp = new HashSet<>();

                        if (this.allSolutions.containsKey(target)) {
                            tmp = this.allSolutions.get(target);
                        }

                        tmp.add(item);

                        this.allSolutions.put(target, tmp);
                    }
                }
            }
        }

        for (Integer key : this.allSolutions.keySet()) {
            int newCount = this.allSolutions.get(key).size();

            if (newCount > this.answerCount) {
                this.answerCount = newCount;
                this.answer = key;
            }
        }
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 39");
        System.out.println("The number with the most solutions <= 1000, is: " + this.answer);
        System.out.println("=================================");
    }
}
