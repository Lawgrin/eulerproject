package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;

import java.util.Hashtable;

/**
 * Created by Grant on 2017/07/31.
 */
public class Question17 implements Question {

    Hashtable<Integer, String> staticStrings = new Hashtable<>();

    StringBuffer stringBuffer = new StringBuffer();

    public Question17() {
        this.staticStrings.put(1, "one");
        this.staticStrings.put(2, "two");
        this.staticStrings.put(3, "three");
        this.staticStrings.put(4, "four");
        this.staticStrings.put(5, "five");
        this.staticStrings.put(6, "six");
        this.staticStrings.put(7, "seven");
        this.staticStrings.put(8, "eight");
        this.staticStrings.put(9, "nine");
        this.staticStrings.put(10, "ten");
        this.staticStrings.put(11, "eleven");
        this.staticStrings.put(12, "twelve");
        this.staticStrings.put(13, "thirteen");
        this.staticStrings.put(14, "fourteen");
        this.staticStrings.put(15, "fifteen");
        this.staticStrings.put(16, "sixteen");
        this.staticStrings.put(17, "seventeen");
        this.staticStrings.put(18, "eighteen");
        this.staticStrings.put(19, "nineteen");
        this.staticStrings.put(20, "twenty");
        this.staticStrings.put(30, "thirty");
        this.staticStrings.put(40, "forty");
        this.staticStrings.put(50, "fifty");
        this.staticStrings.put(60, "sixty");
        this.staticStrings.put(70, "seventy");
        this.staticStrings.put(80, "eighty");
        this.staticStrings.put(90, "ninety");
        this.staticStrings.put(100, "one hundred");
        this.staticStrings.put(200, "two hundred");
        this.staticStrings.put(300, "three hundred");
        this.staticStrings.put(400, "four hundred");
        this.staticStrings.put(500, "five hundred");
        this.staticStrings.put(600, "six hundred");
        this.staticStrings.put(700, "seven hundred");
        this.staticStrings.put(800, "eight hundred");
        this.staticStrings.put(900, "nine hundred");
        this.staticStrings.put(1000, "one thousand");
    }

    @Override
    public void doWork() {
        for (int i = 1; i <= 1000; i++) {
            String number = String.valueOf(i);

            String finalString = "";

            if (this.staticStrings.containsKey(i)) {
                finalString = this.staticStrings.get(i);
            } else {
                int value = i;
                String strHundred = "";
                String strTens = "";
                String strValue = "";

                int hundred = value - (value % 100);
                value = value - hundred;

                if (this.staticStrings.containsKey(hundred)) {
                    strHundred = this.staticStrings.get(hundred);
                }

                if (this.staticStrings.containsKey(value)) {
                    strTens = this.staticStrings.get(value);
                } else {
                    int tens = value - (value % 10);
                    value = value - tens;

                    if (this.staticStrings.containsKey(tens)) {
                        strTens = this.staticStrings.get(tens);
                    }

                    if (this.staticStrings.containsKey(value)) {
                        strValue = this.staticStrings.get(value);
                    }
                }

                finalString = strHundred + (strHundred.length() > 0 ? " and" : "") + " " + strTens + " " + strValue;
            }

            finalString = finalString.trim().replace(" ", "");

            this.stringBuffer.append(finalString);
        }
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 17");
        System.out.println("Number of letters of the numbers 1 - 1000 written out, is: " + this.stringBuffer.toString().length());
        System.out.println("=================================");
    }
}
