package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class Question50 implements Question {

    private Hashtable<Long, String> results = new Hashtable<>();
    private int target = 1000000;

    private long answer = 0L;
    private int answerCount = 0;

    @Override
    public void doWork() {
        long currPrime = 2;
        long runningTotal = 0;
        long startingPrime = 2;

        long highestPrime = 0;

        int count = 1;
        int highestCount = 0;

        while (startingPrime < this.target) {
            runningTotal += currPrime;

            if (Utils.newIsPrime(runningTotal)) {
                if (runningTotal > highestPrime) {
                    highestPrime = runningTotal;
                    highestCount = count;
                }
            }

            if (runningTotal <= this.target) {
                currPrime = Utils.getNextPrime(currPrime);
                count++;
            }

            if (runningTotal + currPrime > this.target) {
                this.results.put(startingPrime, String.valueOf(highestPrime) + "|" + String.valueOf(highestCount));
                startingPrime = Utils.getNextPrime(startingPrime);
                currPrime = startingPrime;
                count = 1;
                runningTotal = 0;
            }
        }

        ArrayList<Long> keys = new ArrayList<>();
        keys.addAll(this.results.keySet());

        Collections.sort(keys);

        for (Long key : keys) {
            String tmp = this.results.get(key);

            String[] data = tmp.split("\\|");
            Long prime = Long.valueOf(data[0]);
            int primeCount = Integer.valueOf(data[1]);

            if (primeCount > this.answerCount) {
                this.answerCount = primeCount;
                this.answer = prime;
            } else if (primeCount == this.answerCount) {
                if (prime > this.answer) {
                    this.answerCount = primeCount;
                    this.answer = prime;
                }
            }
        }
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 50");
        System.out.println("The highest prime with the most consecutive primes, is: " + this.answer);
        System.out.println("=================================");
    }
}
