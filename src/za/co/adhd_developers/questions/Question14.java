package za.co.adhd_developers.questions;

import java.util.Hashtable;

import za.co.adhd_developers.tools.Question;

/**
 * Created by Grant on 2017/07/29.
 */
public class Question14 implements Question
{
    int answer = 0;
    long chain = 0;
    Hashtable<Integer, Long> chains = new Hashtable<>();

    private long calculateEven(long term)
    {
        return term/2;
    }

    private long calculateOdd(long term)
    {
        return (term * 3) + 1;
    }

    @Override
    public void doWork()
    {
        for (int i = 1; i < 1000000; i++)
        {
            long num = (long) i;
            long count = 0;

            while (num != 0)
            {
                if (this.chains.keySet().contains(num))
                {
                    long otherCount = this.chains.get(num);

                    count = count + otherCount;

                    this.chains.put(i, count);
                    num = 0;
                }
                else if (num == 1)
                {
                    count++;
                    this.chains.put(i, count);
                    num = 0;
                }
                else
                {
                    count++;
                    if (num % 2 == 0)
                    {
                        num = calculateEven(num);
                    }
                    else
                    {
                        num = calculateOdd(num);
                    }
                }
            }
        }

        for (Integer key : this.chains.keySet())
        {
            long tmp_chain = this.chains.get(key);

            if (tmp_chain > this.chain)
            {
                this.chain = tmp_chain;
                this.answer = key;
            }
        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 14");
//        System.out.println("=================================");
//        System.out.println(this.chains.size());
        System.out.println("The starting number with the longest chain is: " + this.answer + " (" + this.chain + ")");
        System.out.println("=================================");
    }
}
