package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;

import java.util.ArrayList;

/**
 * Created by Grant on 2017/07/28.
 */
public class Question2 implements Question
{
    private int limit = 4000000;
    private ArrayList<Integer> fibTerms = new ArrayList<>();
    private ArrayList<Integer> evenFibTerms = new ArrayList<>();

    public Question2()
    {
        this.fibTerms.add(1);
        this.fibTerms.add(2);
    }

    private void getAllFibs()
    {
        int newFib = 0;

        while (newFib < this.limit)
        {
            int fib1 = this.fibTerms.get(this.fibTerms.size()-2);
            int fib2 = this.fibTerms.get(this.fibTerms.size()-1);

            newFib = fib1 + fib2;

            if (newFib < this.limit)
            {
                this.fibTerms.ensureCapacity(this.fibTerms.size()+1);
                this.fibTerms.add(newFib);
            }
        }
    }

    private void getAllEvenFibs()
    {
        for (int fib : this.fibTerms)
        {
            if (fib % 2 == 0)
            {
                this.evenFibTerms.add(fib);
            }
        }
    }

    private int getSum()
    {
        int total = 0;

        for (int fib : this.evenFibTerms)
        {
            total = total + fib;
        }

        return total;
    }

    @Override
    public void doWork()
    {
        this.getAllFibs();
        this.getAllEvenFibs();
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 2");
//        System.out.println("=================================");
//        System.out.println(q2.fibTerms);
//        System.out.println(q2.evenFibTerms);
        System.out.println("Sum of even fib terms: " + this.getSum());
        System.out.println("=================================");
    }
}
