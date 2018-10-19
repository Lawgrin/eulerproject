package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;

public class Question31 implements Question {

    private int answer = 0;

    @Override
    public void doWork() {
        for (int p200 = 0; p200 < 2; p200++) {
            for (int p100 = 0; p100 < 3; p100++) {
                for (int p50 = 0; p50 < 5; p50++) {
                    for (int p20 = 0; p20 < 11; p20++) {
                        for (int p10 = 0; p10 < 21; p10++) {
                            for (int p5 = 0; p5 < 41; p5++) {
                                for (int p2 = 0; p2 < 101; p2++) {
                                    for (int p1 = 0; p1 < 201; p1++) {
                                        int total = 0;

                                        total += p200 * 200;
                                        total += p100 * 100;
                                        total += p50 * 50;
                                        total += p20 * 20;
                                        total += p10 * 10;
                                        total += p5 * 5;
                                        total += p2 * 2;
                                        total += p1 * 1;

                                        if (total == 200) {
                                            answer++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 31");
        System.out.println("The total number of coin combinations, is: " + this.answer);
        System.out.println("=================================");
    }
}
