package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;

import java.util.Calendar;

/**
 * Created by Grant on 2017/07/31.
 */
public class Question19 implements Question {

    int answer = 0;

    @Override
    public void doWork() {
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(2000, 12, 31);

        Calendar calendar = Calendar.getInstance();
        calendar.set(1901, 01, 01);

        int count = 0;

        while (calendar.compareTo(endCalendar) <= 0) {
            while (calendar.get(Calendar.DAY_OF_WEEK) != 1) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }

            if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
                if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                    count++;
                }
            }

            calendar.add(Calendar.DAY_OF_MONTH, 7);
        }

        this.answer = count;
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 19");
        System.out.println("The number of Sundays that fall on the first of the month, is: " + this.answer);
        System.out.println("=================================");
    }
}
