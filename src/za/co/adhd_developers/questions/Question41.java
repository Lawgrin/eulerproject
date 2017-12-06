package za.co.adhd_developers.questions;

import za.co.adhd_developers.tools.Question;
import za.co.adhd_developers.tools.Utils;

import java.util.ArrayList;
import java.util.List;

public class Question41 implements Question
{
    private int answer = 0;

    @Override
    public void doWork()
    {
        String starting = "123456789";
        while (this.answer == 0)
        {
            List<String> permutations = new ArrayList<>();
            Utils.permutation(starting, permutations);

            for (int i = 0; i < permutations.size(); i++)
            {
                if (Utils.isPrime(Long.valueOf(permutations.get(i))))
                {
                    int newNumber = Integer.valueOf(permutations.get(i));

                    if (newNumber > this.answer)
                    {
                        this.answer = newNumber;
                    }
                }
            }

            starting = starting.substring(0, starting.length()-1);
        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 41");
        System.out.println("The largest n-digit pandigital prime that exists, is: " + this.answer);
        System.out.println("=================================");
    }
}
