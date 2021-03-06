package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

import java.util.Hashtable;

/**
 * Created by Grant on 2017/08/30.
 */
public class Question27 implements Question {

    private String answer = "";
    private int maxCount = 0;
    private Hashtable<Long, Boolean> isPrime = new Hashtable<>();
    private Hashtable<String, Integer> primeCount = new Hashtable<>();

    @Override
    public void doWork() {
        int a = -999;
        int b = -1000;

        while (a < 1000 || b <= 1000) {
            a = -999;
            while (a < 1000) {
                for (int i = 0; i <= Math.abs(a); i++) {
                    String key = String.valueOf(a) + "|" + String.valueOf(b);

                    long finalResult = (long) (Math.pow(i, 2) + (a * i) + b);

                    Boolean finalResultIsPrime = false;

                    if (this.isPrime.containsKey(finalResult)) {
                        finalResultIsPrime = this.isPrime.get(finalResult);
                    } else if (Utils.isPrime(finalResult)) {
                        finalResultIsPrime = true;
                        this.isPrime.put(finalResult, true);
                    }

                    if (finalResultIsPrime) {
                        if (this.primeCount.containsKey(key)) {
                            Integer currCount = this.primeCount.get(key);
                            currCount++;
                            this.primeCount.put(key, currCount);
                        } else {
                            this.primeCount.put(key, 1);
                        }
                    } else {
                        i = Math.abs(a) + 1;
                    }
                }
                a++;
            }
            b++;
        }

        for (String key : this.primeCount.keySet()) {
            Integer count = this.primeCount.get(key);

            if (count > this.maxCount) {
                this.answer = key;
                this.maxCount = count;
            }
        }
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 27");
        String[] coeff = this.answer.split("\\|");

        int i_1 = Integer.valueOf(coeff[0]);
        int i_2 = Integer.valueOf(coeff[1]);

        System.out.println("The product of the coefficients, a and b, is: " + (i_1 * i_2));
        System.out.println("=================================");
    }
}
