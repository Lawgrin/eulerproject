package za.co.adhd_developers.questions;

import za.co.adhd_developers.tools.Question;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Hashtable;

public class Question32 implements Question
{
    int limit = 9;

    private Hashtable<String, String> finalResults = new Hashtable<>();

    private ArrayList<String> uniqueProds = new ArrayList<>();

    private BigInteger answer = null;

    @Override
    public void doWork()
    {
        for (int mp = 1; mp < 100000; mp++)
        {
            String str_mp = String.valueOf(mp);

            int upperLimit = (this.limit+1)-str_mp.length();

            upperLimit = -Math.floorDiv(-upperLimit, 2);

            upperLimit = (int) Math.pow(10,upperLimit);

            String test = String.valueOf(mp)+String.valueOf(upperLimit)+String.valueOf(mp * upperLimit);
            while (test.length() > 9)
            {
                upperLimit--;
                test = String.valueOf(mp)+String.valueOf(upperLimit)+String.valueOf(mp * upperLimit);
            }
            if (test.length() < 9)
            {
                continue;
            }
            if (upperLimit == 0)
            {
                continue;
            }

            for (int ml = 1; ml <= upperLimit; ml++)
            {
                if (ml != mp && containsOneOfEach(ml) && containsOneOfEach(mp))
                {
                    int prod = mp * ml;

                    String prod_str = String.valueOf(prod);
                    String numberUsed = String.valueOf(mp)+String.valueOf(ml)+prod_str;
                    String inversedNumberUsed = String.valueOf(ml)+String.valueOf(mp)+prod_str;

                    if (finalResults.containsKey(numberUsed) || finalResults.containsKey(inversedNumberUsed))
                    {
                        continue;
                    }

                    if (numberUsed.length() == this.limit)
                    {
                        if (containsOneOfEach(numberUsed))
                        {
                            finalResults.put(numberUsed, prod_str);
                            finalResults.put(inversedNumberUsed, prod_str);

                            if (!uniqueProds.contains(prod_str))
                            {
                                uniqueProds.add(prod_str);
                            }
                        }
                    }
                }
            }
        }

        this.answer = new BigInteger("0");
        for (String item : this.uniqueProds)
        {
            BigInteger tmpBigInt = new BigInteger(item);
            this.answer = this.answer.add(tmpBigInt);
        }
    }

    private boolean containsOneOfEach(int number)
    {
        return containsOneOfEach(String.valueOf(number));
    }


    private boolean containsOneOfEach(String number)
    {
        String[] digits = number.split("|");

        Hashtable<String, Boolean> data = new Hashtable<>();

        for (int i = 1; i <= this.limit; i++)
        {
            data.put(String.valueOf(i), false);
        }

        for (String digit : digits)
        {
            if ("".equals(digit))
            {
                continue;
            }

            if (data.get(digit) == null)
            {
                return false;
            }

            Boolean isUsed = data.get(digit);

            if (isUsed)
            {
                return false;
            }
            else
            {
                data.put(digit, true);
            }
        }

        return true;
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 32");
        System.out.println("The sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital, is: " + this.answer.toString());
        System.out.println("=================================");
    }
}
