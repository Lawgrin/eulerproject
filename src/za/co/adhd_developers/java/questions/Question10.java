package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

import java.util.Date;

/**
 * Created by Grant on 2017/07/28.
 */
public class Question10 implements Question
{
    long limit = 2000000;
    long answer = 0;

    @Override
    public void doWork()
    {
        Date start = new Date();
        int count = 0;
        for (long i = this.limit; i > 0; i--)
        {
            if (Utils.newIsPrime(i))
            {
                count++;
                this.answer += i;
            }

//            if (i % 10000 == 0)
//            {
//                float progress = (float) i / (float) this.limit;
//                float percent = 100f - (progress*100);
//                Date end = new Date();
////                System.out.println("(" + count + ") - " + percent + "%" + " - " + (end.getTime() - start.getTime()) + " - " + i);
//                start = end;
//            }
        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 10");
//        System.out.println("=================================");
//        System.out.println(this.d1);
//        System.out.println(this.d2);
        System.out.println("The sum of all primes below 2 000 000 is: " + this.answer);
        System.out.println("=================================");
    }
}
