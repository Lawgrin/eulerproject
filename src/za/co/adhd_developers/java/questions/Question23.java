package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Grant on 2017/08/01.
 */
public class Question23 implements Question
{
    long answer = 0;

    ArrayList<Integer> abundantNumbers = new ArrayList<>();

    ArrayList<Integer> cantSum = new ArrayList<>();

    @Override
    public void doWork()
    {
        for (int i = 1; i <= 28123 ; i++)
        {
            ArrayList<Long> factors = Utils.getFactors((long)i);

            long sum = 0;

            for (long fact : factors)
            {
                if (fact != i)
                {
                    sum += fact;
                }
            }

            if (sum > i)
            {
                this.abundantNumbers.ensureCapacity(this.abundantNumbers.size()+1);
                this.abundantNumbers.add(i);
            }
        }

        Set<Integer> sums = new HashSet<>();

        for (Integer abundant1 : this.abundantNumbers)
        {
            for (Integer abundant2 : this.abundantNumbers)
            {
                Integer sum = abundant1 + abundant2;
                if (!sums.contains(sum))
                {
                    sums.add(sum);
                }
            }
        }

        for (int i = 1; i <= 28123; i++)
        {
            if (!sums.contains(i))
            {
                this.cantSum.add(i);
            }
        }

        for (Integer cant : this.cantSum)
        {
            this.answer += Long.valueOf(cant);
        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 23");
//        System.out.println("=================================");
//        System.out.println(this.correctPaths);
        System.out.println("The sum of all the positive integers, is: "+this.answer);
        System.out.println("=================================");
    }
}
