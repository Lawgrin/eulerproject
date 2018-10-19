package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;

public class Question62 implements Question {

    private BigInteger answer = BigInteger.ZERO;

    @Override
    public void doWork() {
        BigInteger ans = BigInteger.valueOf(0);

        BigInteger baseNumber = BigInteger.valueOf(1);

        Hashtable<String, BigInteger> allCbdNumbers = new Hashtable<>();

        BigInteger endpoint = BigInteger.valueOf(Integer.MAX_VALUE);
        endpoint = endpoint.multiply(BigInteger.valueOf(300));

        while (ans.compareTo(endpoint) < 0) {
            ans = baseNumber.pow(3);

            baseNumber = baseNumber.add(BigInteger.valueOf(1));

            allCbdNumbers.put(baseNumber.toString(), ans);
        }

        Hashtable<String, ArrayList<String>> groupedOptions = new Hashtable<>();

        allCbdNumbers.forEach((s, bigInteger) -> {
            String numberStr = bigInteger.toString();

            ArrayList<String> tmp = new ArrayList<>();
            tmp.addAll(Arrays.asList(numberStr.split("|")));
            Collections.sort(tmp);

            String key = String.join("", tmp);

            ArrayList<String> items = groupedOptions.get(key);

            if (items == null) {
                items = new ArrayList<>();
            }

            items.add(numberStr);

            groupedOptions.put(key, items);
        });

        for (String key : groupedOptions.keySet()) {
            if (groupedOptions.get(key).size() > 4) {

                boolean b = true;
                for (int i = 0; i < groupedOptions.get(key).size(); i++) {
                    for (int j = 0; j < groupedOptions.get(key).size(); j++) {
                        if (i == j) {
                            continue;
                        }

                        b = Utils.containsSameCharsInDiffOrder(groupedOptions.get(key).get(i), groupedOptions.get(key).get(j));

                        if (!b) {
                            System.out.println(groupedOptions.get(key).get(i) + "\n" + groupedOptions.get(key).get(j));
                            break;
                        }
                    }
                    if (!b) {
                        break;
                    }
                }

                if (!b) {
                    System.out.println("WTF");
                }
                allCbdNumbers.forEach((s, bigInteger) -> {

                    if (groupedOptions.get(key).contains(bigInteger.toString())) {

                        if (this.answer.compareTo(BigInteger.ZERO) == 0) {
                            this.answer = bigInteger;
                        } else if (this.answer.compareTo(bigInteger) > 0) {
                            this.answer = bigInteger;
                        }
                    }
                });
            }
        }
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 62");
        System.out.println("The smallest cube for which exactly five permutations of its digits are cube, is: " + this.answer.toString());
        System.out.println("=================================");
    }
}
