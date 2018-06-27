package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

import java.util.ArrayList;
import java.util.Hashtable;

public class Question35 implements Question
{
    private int answer = 0;
    private int limit = 1000000;

    private Hashtable<String, Boolean> isAllPrime = new Hashtable<>();

    @Override
    public void doWork()
    {
        for (int i = 0; i < limit; i++)
        {
            if (this.isAllPrime.containsKey(String.valueOf(i)))
            {
                continue;
            }

            ArrayList<String> allRotations = Utils.getAllCyclicallyRotations(String.valueOf(i));

            boolean allPrime = true;

            for (String rotation : allRotations)
            {
                if (!Utils.newIsPrime(Long.valueOf(rotation)))
                {
                    allPrime = false;
                    break;
                }
            }

//            String output = "";
            for (String rotation : allRotations)
            {
//                output += rotation+",";
                this.isAllPrime.put(rotation, allPrime);
            }
//            output += "|"+allPrime;

//            System.out.println(output);

//            if (i % (this.limit / (this.limit >= 100 ? 100 : this.limit)) == 0)
//            {
//                System.out.println(i+"/"+this.limit);
//            }
        }

        for (String key : this.isAllPrime.keySet())
        {
            if (this.isAllPrime.get(key))
            {
                this.answer++;
            }
        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 35");
        System.out.println("The total number of circular primes below one million, is: " + this.answer);
        System.out.println("=================================");
    }
}
