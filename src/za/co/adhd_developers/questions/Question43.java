package za.co.adhd_developers.questions;

import za.co.adhd_developers.tools.Question;
import za.co.adhd_developers.tools.Utils;

import java.util.ArrayList;
import java.util.List;

public class Question43 implements Question
{
    private long answer = 0L;
    @Override
    public void doWork()
    {
        List<String> permutations = new ArrayList<>();

        Utils.permutation("0123456789", permutations);

        for (String permutation : permutations)
        {
            if (permutation.startsWith("0"))
            {
                continue;
            }
            if (!Utils.isPandigital(permutation, 0, 9))
            {
                continue;
            }
            if (!Utils.hasSubStringDivisibility(permutation))
            {
                continue;
            }
            this.answer += Long.valueOf(permutation);
        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 43");
        System.out.println("The sum of all 0 to 9 pandigital numbers with the sub-string divisibility property, is: " + this.answer);
        System.out.println("=================================");
    }
}
