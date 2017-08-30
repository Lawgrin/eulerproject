package za.co.adhd_developers.questions;

import za.co.adhd_developers.tools.Question;
import za.co.adhd_developers.tools.Utils;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Grant on 2017/07/28.
 */
public class Question3 implements Question
{
    private long source = new Long("600851475143");
//    private long source = new Long("13195");
    private ArrayList<Long> factors = new ArrayList<>();
    private ArrayList<Long> primeFactors = new ArrayList<>();

    private void calculateFactors()
    {
        this.factors.addAll(Utils.getFactors(source));

        factors.sort(new Comparator<Long>()
        {
            @Override
            public int compare(Long o1, Long o2)
            {
                return o1.compareTo(o2);
            }
        });
    }

    private void getPrimeFactors()
    {
        for (Long factor : factors)
        {
            if (Utils.isPrime(factor))
            {
                primeFactors.add(factor);
            }
        }
    }

    private Long getLargesPrime()
    {
        primeFactors.sort(new Comparator<Long>()
        {
            @Override
            public int compare(Long o1, Long o2)
            {
                return o1.compareTo(o2);
            }
        });

        return this.primeFactors.get(this.primeFactors.size()-1);
    }

    @Override
    public void doWork()
    {
        this.calculateFactors();
        this.getPrimeFactors();
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 3");
//        System.out.println("=================================");
//        System.out.println(this.factors);
//        System.out.println(this.primeFactors);
        System.out.println("Largest prime number: " + this.getLargesPrime());
        System.out.println("=================================");
    }
}
