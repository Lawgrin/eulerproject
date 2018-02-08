package za.co.adhd_developers;

import za.co.adhd_developers.tools.Question;

import java.util.Date;

/**
 * Created by Grant on 2017/07/28.
 */
public class Root
{
    static int[] questions = {};
    static int start = 1;
    static int end = 64;

    public static void main(String[] args)
    {
        if (questions.length <= 0)
        {
            questions = new int[end];
            int index = 0;
            for (int i = start; i <= end; i++)
            {
//                if (i == 26 || i == 39)
//                {
//                    continue;
//                }
                questions[index] = i;
                index++;
            }
        }
        for (int questNum : questions)
        {
            try
            {
                Class<?> cls = Class.forName("za.co.adhd_developers.questions.Question"+String.valueOf(questNum));

                Object question = cls.newInstance();
                if (question instanceof Question)
                {
                    Date start = new Date();
                    ((Question) question).doWork();
                    Date end = new Date();
                    long timeTaken = (end.getTime() - start.getTime());
//                    ((Question) question).printAnswer();
//                    System.out.println("Time taken: " + timeTaken +"(ms)");
                    System.out.println(cls.getName().replace("za.co.adhd_developers.questions.", "") + "\t" + timeTaken);
                }
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
    }
}
