package za.co.adhd_developers.questions;

import za.co.adhd_developers.tools.Question;

import java.util.ArrayList;

public class Question29 implements Question
{
    private int lowerLimit = 2;
    private int upperlimit = 100;

    private int answer = 0;

    private ArrayList<Double> terms = new ArrayList<>();

    @Override
    public void doWork()
    {
        int a = this.lowerLimit;

        while (a <= this.upperlimit)
        {
            int b = this.lowerLimit;
            while (b <= this.upperlimit)
            {
                double result = Math.pow(a,b);

                if (!this.terms.contains(result))
                {
                    this.terms.add(result);
                }
                b++;
            }
            a++;
        }

        this.answer = this.terms.size();
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 29");
        System.out.println("Amount of distinct terms in the sequence, is: "+this.answer);
        System.out.println("=================================");
    }
}
