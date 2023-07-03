package za.co.adhd_developers.java;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.kotlin.tools.MyToolbox;

import java.util.Hashtable;

/**
 * Created by Grant on 2017/07/28.
 */
public class Root {
    static int[] questions = {};
    static int start = 1;
    static int end = 64;

    public static void main(String[] args) {
        Hashtable<String, Long> results = new Hashtable<>();
        if (questions.length == 0) {
            questions = new int[(end - start) + 1];
            int index = 0;
            for (int i = start; i <= end; i++) {
                questions[index] = i;
                index++;
            }
        }
        for (int questNum : questions) {
//            if (questNum == 26) {
//                continue;
//            }
            try {
                Class<?> cls = Class.forName("za.co.adhd_developers.java.questions.Question" + String.valueOf(questNum));

                Object question = cls.newInstance();
                if (question instanceof Question) {
                    long start = System.nanoTime();
                    ((Question) question).doWork();
                    long end = System.nanoTime();
                    long timeTaken = (end - start);
                    ((Question) question).printAnswer();
                    String timeTakenStr = MyToolbox.DateTimeToolbox.Tools.getHumanReadableDuration(timeTaken, MyToolbox.DateTimeToolbox.TimeUnit.Nanosecond, MyToolbox.DateTimeToolbox.TimeUnit.Nanosecond);
                    System.out.println("Time taken: " + timeTakenStr);
                    results.put("Question " + String.valueOf(questNum), timeTaken);
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n\n");
        for (int i = 1; i <= end; i++) {
            if (results.containsKey("Question " + i)) {
                System.out.println(i + "\t" + results.get("Question " + i));
            }
        }
    }
}
