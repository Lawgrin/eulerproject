package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Grant on 2017/08/01.
 */
public class Question21 implements Question
{
    long answer = 0;

    Hashtable <Integer, Integer> sumOfDivisors = new Hashtable<>();

    ArrayList<Integer> done = new ArrayList<>();

    @Override
    public void doWork()
    {
        for (int i = 1; i <= 10000; i++)
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

            if (sum != i && sum <= 10000)
            {
                this.sumOfDivisors.put(i, (int) sum);
            }
        }

        for (Integer key : this.sumOfDivisors.keySet())
        {
            int other = this.sumOfDivisors.get(key);

            if (!this.done.contains(other))
            {
                if (this.sumOfDivisors.containsKey(other))
                {
                    int otherValue = this.sumOfDivisors.get(other);

                    if (key == otherValue)
                    {
                        this.answer += (key + other);
                        this.done.add(key);
                        this.done.add(other);
                    }
                }
            }
        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 21");
//        System.out.println("=================================");
//        System.out.println(this.correctPaths);
        System.out.println("The sum of all the amicable numbers under 10000, is: "+this.answer);
        System.out.println("=================================");
    }
}
