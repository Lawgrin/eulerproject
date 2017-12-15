package za.co.adhd_developers;

import za.co.adhd_developers.tools.Question;

import java.util.Date;

/**
 * Created by Grant on 2017/07/28.
 */
public class Root
{
    static int[] questions = {58};

    public static void main(String[] args)
    {
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
                    ((Question) question).printAnswer();
                    System.out.println("Time taken: " + (end.getTime() - start.getTime()) + "(ms)");
                    System.out.println("\n\n");
                }
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
    }
}
