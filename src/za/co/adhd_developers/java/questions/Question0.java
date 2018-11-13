package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Question0 implements Question {

    private String SERVER_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    @Override
    public void doWork() {
        String dateStr = "2018-10-26T03:38:59";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SERVER_DATE_PATTERN);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("gmt"));

        try {
            Date date = simpleDateFormat.parse(dateStr);

            System.out.println(date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printAnswer() {
    }
}
