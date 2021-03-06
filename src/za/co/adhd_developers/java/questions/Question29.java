package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Question29 implements Question {

    private int lowerLimit = 2;
    private int upperlimit = 100;

    private int answer = 0;

    private Set<Double> terms = new HashSet<>();

    @Override
    public void doWork() {
        int a = this.lowerLimit;

        while (a <= this.upperlimit) {
            int b = this.lowerLimit;
            while (b <= this.upperlimit) {
                this.terms.add(Math.pow(a, b));
                b++;
            }
            a++;
        }

        this.answer = this.terms.size();
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 29");
        System.out.println("Amount of distinct terms in the sequence, is: " + this.answer);
        System.out.println("=================================");
    }
}
