package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.TimeCheck;
import za.co.adhd_developers.java.tools.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Created by Grant on 2017/08/01.
 */
public class Question26 implements Question {
    int answer = 0;
    String cycle = "";
    int limit = 1000;
    String prefix = "";

    @Override
    public void doWork() {
        BigDecimal result;
        BigDecimal one = new BigDecimal("1");
        for (int i = 999; i > 1; i--) {

            int cycleLength = this.cycle.length();
            result = one.divide(new BigDecimal(String.valueOf(i)), (i - 1) * 2, RoundingMode.DOWN);
            result = result.stripTrailingZeros();

            String number = result.toString();
            String recurring = number.substring(number.indexOf(".") + 1);
            int recurring_length = recurring.length();

            int HALF_LENGTH = Math.floorDiv(recurring_length, 2);
            if (HALF_LENGTH == 0) {
                continue;
            }

            if (HALF_LENGTH < cycleLength) {
                continue;
            }

            for (int prefixLength = 0; prefixLength <= 5; prefixLength++) {
                String prefix = "";

                String workingSet = recurring;
                if (prefixLength > 0) {
                    workingSet = recurring.substring(prefixLength);
                    prefix = recurring.substring(0,prefixLength);
                }

                int workingHALF_LENGTH = Math.floorDiv(workingSet.length(),2);

                if (workingHALF_LENGTH == 0) {
                    break;
                }

                if (workingHALF_LENGTH < cycleLength) {
                    break;
                }

                for (int copyLength = 1; copyLength <= workingHALF_LENGTH; copyLength++) {
                    String[] items = workingSet.split("(?<=\\G.{" + copyLength + "})");
                    if (items.length == 1) {
                        continue;
                    }

                    int count = 0;
                    for (String item : items) {
                        if (item.equals(items[0])) {
                            count++;
                        } else {
                            break;
                        }
                    }

                    if (count == items.length) {
                        if (cycleLength < items[0].length()) {
                            this.cycle = items[0];
                            this.answer = i;
                            copyLength = workingHALF_LENGTH + 1;
                            cycleLength = this.cycle.length();
                            this.prefix = prefix;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 26");
        System.out.println("The 1/d with the longest recurring cycle, is: " + this.answer);
        System.out.println("The cycle for 1/" + this.answer + ", is 0." + this.prefix + "(" + this.cycle + ")");
        System.out.println("=================================");
    }
}
