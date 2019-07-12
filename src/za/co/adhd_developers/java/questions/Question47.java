package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Question47 implements Question {

    private Long answer = 0L;

    @Override
    public void doWork() {

        Hashtable<Long, List<Long>> possibles = new Hashtable<>();
        int target = 4;

        long currNumber = 1;

        while (possibles.size() != target) {
            ArrayList<Long> factors = Utils.getFactors(currNumber);
            ArrayList<Long> primes = new ArrayList<>();
            for (Long number : factors) {
                if (Utils.isPrime(number)) {
                    if (!primes.contains(number)) {
                        primes.add(number);
                    }
                }
            }

            if (primes.size() == target) {
                if (possibles.size() > 0) {
                    if (possibles.containsKey(currNumber - 1)) {
                        possibles.put(currNumber, primes);
                    } else {
                        possibles.clear();
                        possibles.put(currNumber, primes);
                    }
                } else {
                    possibles.put(currNumber, primes);
                }
            }
            currNumber++;
        }

        for (Long key : possibles.keySet()) {
            if (this.answer == 0) {
                this.answer = key;
            } else {
                if (key < this.answer) {
                    this.answer = key;
                }
            }
        }
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 47");
        System.out.println("The first four consecutive integers to have four distinct prime factors each, starts with: " + this.answer);
        System.out.println("=================================");
    }
}
