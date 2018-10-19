package za.co.adhd_developers.java;

import za.co.adhd_developers.java.tools.Question;

import java.util.Date;

/**
 * Created by Grant on 2017/07/28.
 */
public class Root {
    static int[] questions = {};
    static int start = 1;
    static int end = 64;

    public static void main(String[] args) {
        if (questions.length <= 0) {
            questions = new int[(end - start) + 1];
            int index = 0;
            for (int i = start; i <= end; i++) {
                questions[index] = i;
                index++;
            }
        }
        for (int questNum : questions) {
            if (questNum == 26) {
                continue;
            }
            try {
                Class<?> cls = Class.forName("za.co.adhd_developers.java.questions.Question" + String.valueOf(questNum));

                Object question = cls.newInstance();
                if (question instanceof Question) {
                    Date start = new Date();
                    ((Question) question).doWork();
                    Date end = new Date();
                    long timeTaken = (end.getTime() - start.getTime());
                    ((Question) question).printAnswer();
                    System.out.println("Time taken: " + timeTaken + "(ms)");
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
