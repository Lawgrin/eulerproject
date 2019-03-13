package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.TimeCheck;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Pattern;

public class Question0 implements Question {

    int answer = 0;
    String cycle = "";
    int limit = 1000;
    String prefix = "";

    @Override
    public void doWork() {

        TimeCheck timeCheck = new TimeCheck();
        BigDecimal result;
        BigDecimal one = new BigDecimal("1");

        int cycleLength = this.cycle.length();
        int i = 997;
        System.out.println("i: " + i);
        result = one.divide(new BigDecimal(String.valueOf(i)), (i - 1) * 2, RoundingMode.DOWN);
        result = result.stripTrailingZeros();

        String number = result.toString();
        String recurring = number.substring(number.indexOf(".") + 1);
        int recurring_length = recurring.length();

        int HALF_LENGTH = Math.floorDiv(recurring_length, 2);
//        if (HALF_LENGTH == 0) {
//            continue;
//        }

        timeCheck.start();
        for (int copyLength = 1; copyLength <= HALF_LENGTH; copyLength++) {
            String[] items = recurring.split("(?<=\\G.{" + copyLength + "})");
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
                    copyLength = HALF_LENGTH + 1;
                    cycleLength = this.cycle.length();
                }
            }
        }

//        while (start_location <= HALF_LENGTH || copy_length <= HALF_LENGTH) {
//            start_location = 0;
//            timeCheck.mark(String.valueOf(copy_length));
//            while (start_location <= HALF_LENGTH) {
//                String prefix = "";
//                if (start_location > 0) {
//                    prefix = recurring.substring(0, start_location);
//                }
//                String str_check = recurring.substring(start_location, start_location + copy_length);
//
//                int str_check_Length = str_check.length();
//                if (cycleLength > str_check_Length) {
//                    continue;
//                }
//
//                StringBuilder check = new StringBuilder(prefix);
//                int check_Length = check.length();
//
//                int total = Math.floorDiv((recurring_length - check_Length), str_check_Length);
//
//                while (total > 0) {
//                    check.append(str_check);
//                    total--;
//                }
//                check_Length = check.length();
//
//                while (check_Length < recurring_length) {
//                    check.append(str_check);
//                    check_Length = check.length();
//                }
//
//                if (check_Length > recurring_length) {
//                    check = new StringBuilder(check.substring(0, recurring_length));
//                }
//
//                if (check.toString().compareTo(recurring) == 0) {
////                            this.possibles.put(i, str_check);
//                    this.cycle = str_check;
//                    this.answer = i;
//                    cycleLength = this.cycle.length();
//                    this.prefix = prefix;
//
//                    start_location = HALF_LENGTH;
//                    copy_length = HALF_LENGTH;
//                }
//
//                start_location++;
//            }
//            copy_length++;
//        }
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 26");
        System.out.println("The 1/d with the longest recurring cycle, is: " + this.answer);
        System.out.println("The cycle for 1/" + this.answer + ", is 0." + this.prefix + "(" + this.cycle + ")");
        System.out.println("=================================");
    }

    public static boolean passwordValid(String password) {

        if (!Pattern.compile("[A-z0-9]{7,}").matcher(password).matches()) {
            return false;
        }
        if (!Pattern.compile("\\w*[A-Z]+\\w*").matcher(password).matches()) {
            return false;
        }
        if (!Pattern.compile("\\w*[a-z]+\\w*").matcher(password).matches()) {
            return false;
        }
        if (!Pattern.compile("\\w*[0-9]+\\w*").matcher(password).matches()) {
            return false;
        }
        if (!Pattern.compile("[^\\W]+").matcher(password).matches()) {
            return false;
        }

        return true;
    }
}
