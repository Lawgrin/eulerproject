package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;

import java.util.ArrayList;

/**
 * Created by Grant on 2017/07/28.
 */
public class Question1 implements Question
{
    private int limit = 1000;
    private ArrayList<Integer> multiples = new ArrayList<>();

    private void getAllMultiples()
    {
        for (int i = 0; i < this.limit; i++)
        {
            if (i % 3 == 0)
            {
                this.multiples.ensureCapacity(this.multiples.size()+1);
                this.multiples.add(i);
            }
            else if (i % 5 == 0)
            {
                this.multiples.ensureCapacity(this.multiples.size()+1);
                this.multiples.add(i);
            }
        }
    }

    private int getSum()
    {
        int total = 0;

        for (int mult : this.multiples)
        {
            total = total + mult;
        }

        return total;
    }

    @Override
    public void doWork()
    {
        this.multiples.clear();

        this.getAllMultiples();
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 1");
//        System.out.println("=================================");
//        System.out.println(q1.multiples);
        System.out.println("Sum of multiples: " + this.getSum());
        System.out.println("=================================");
    }
}
