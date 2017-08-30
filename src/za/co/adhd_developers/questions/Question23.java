package za.co.adhd_developers.questions;

import za.co.adhd_developers.tools.Question;
import za.co.adhd_developers.tools.Utils;

import java.math.BigInteger;
import java.util.ArrayList;

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
            ArrayList<Long> tmp_factors = Utils.getFactors((long)i);

            ArrayList<Long> factors = new ArrayList<>();

            for (long tmp : tmp_factors)
            {
                if (tmp != i)
                {
                    if (!factors.contains(tmp))
                    {
                        factors.add(tmp);
                    }
                }
            }

            long sum = 0;

            for (long fact : factors)
            {
                sum += fact;
            }

            if (sum > i)
            {
                this.abundantNumbers.ensureCapacity(this.abundantNumbers.size()+1);
                this.abundantNumbers.add(i);
            }
        }

        for (int i = 1; i <= 28123; i++)
        {
            if (i < (this.abundantNumbers.get(0)*2))
            {
                this.cantSum.add(i);
                continue;
            }
            for (Integer abundant : this.abundantNumbers)
            {
                if (abundant >= i)
                {
                    this.cantSum.add(i);
                    break;
                }

                if (i == (abundant*2))
                {
                    break;
                }

                Integer check = i - abundant;

                if (check < this.abundantNumbers.get(0))
                {
                    this.cantSum.add(i);
                    break;
                }

                if (this.abundantNumbers.contains(check))
                {
                    break;
                }
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
