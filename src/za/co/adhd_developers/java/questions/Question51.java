package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.PermUtil;
import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

import java.util.ArrayList;

public class Question51 implements Question {

    private String answer = "";
    private int length = 6;

    @Override
    public void doWork() {
        ArrayList<String> templats = new ArrayList<>();

        ArrayList<String> libArray = new ArrayList<>();

        for (int i = 0; i < length - 1; i++) {
            libArray.add("d");
        }

        for (int i = 0; i < length - 1; i++) {
            libArray.add("?");
        }

        PermUtil permUtil = new PermUtil(Utils.castArrayToList(libArray), length);

        String[] perm = (String[]) permUtil.next();

        while (perm != null) {
            String outPut = String.join("", perm);
            perm = (String[]) permUtil.next();

            if (!templats.contains(outPut)) {
                templats.add(outPut);
            }
        }

        for (int i = 100000; i < 1000000; i++) {
            for (String item : templats) {
                String iStr = String.valueOf(i);
                String frame = "";

                for (int d = 0; d < item.length(); d++) {
                    if (item.charAt(d) == "d".charAt(0)) {
                        frame += String.valueOf(iStr.charAt(d));
                    } else {
                        frame += "?";
                    }
                }

                int count = 0;
                int failCount = 0;
                for (int r = 0; r < 10; r++) {
                    String numberStr = frame.replaceAll("\\?", String.valueOf(r));

                    if (numberStr.startsWith("0")) {
                        failCount++;
                        continue;
                    }

                    Long number = Long.valueOf(numberStr);

                    if (Utils.newIsPrime(number)) {
                        count++;
                    } else {
                        failCount++;
                    }

                    if (10 - failCount < 8) {
                        break;
                    }
                }

                if (count > 7) {
                    this.answer = frame;
                }
            }

            if (this.answer.length() > 0) {
                break;
            }
        }

        for (int i = 0; i < 10; i++) {
            String numberStr = this.answer.replaceAll("\\?", String.valueOf(i));

            if (numberStr.startsWith("0")) {
                continue;
            }

            Long number = Long.valueOf(numberStr);

            if (Utils.newIsPrime(number)) {
                this.answer = numberStr;
            }
        }
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 51");
        System.out.println("The smallest prime which us part of an eight prime value family, is: " + this.answer);
        System.out.println("=================================");
    }
}
