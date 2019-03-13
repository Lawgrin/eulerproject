package za.co.adhd_developers.java.tools;

import java.util.Date;

public class TimeCheck {

    Date mStartTime = new Date();

    public void start() {
        mStartTime = new Date();
    }

    public void mark() {
        mark("");
    }

    public void mark(String data) {
        Date endTime = new Date();

        long timeTaken = (endTime.getTime() - mStartTime.getTime());
        System.out.println("Mark: " + timeTaken + "ms - " + data);
        mStartTime = new Date();
    }
}
