package za.co.adhd_developers.questions;

import za.co.adhd_developers.tools.Question;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Grant on 2017/08/01.
 */
public class Question26 implements Question
{
    int answer = 0;
    String cycle = "";

    @Override
    public void doWork()
    {
        BigDecimal result;
        BigDecimal one = new BigDecimal("1");
        for (int i = 2; i < 11; i++)
        {
            try
            {
                one.setScale(2*i+1);
                result = one.divide(new BigDecimal(String.valueOf(i)));

                String number = result.toString();
            }
            catch (ArithmeticException e)
            {
                result = one.divide(new BigDecimal(String.valueOf(i)),(i-1)*2, RoundingMode.HALF_EVEN);

                String number = result.toString();
                String recurring = number.substring(number.indexOf(".")+1);
                System.out.println(i + " | " + number);

                for (int j = 1; j < i-1; j++)
                {
                    ArrayList<String> items = new ArrayList<>();
                    int index = 0;

                    while (index < recurring.length())
                    {
                        items.add(recurring.substring(index, Math.min(index+j,recurring.length())));
                        index += j;
                    }

                    String first = items.get(0);

                    if (items.size() > 1)
                    {
                        if (items.get(1).equalsIgnoreCase(first))
                        {
                            j = i-1;

                            System.out.println(i + " | " + number + " | " + first);
                        }
                    }
                }
            }

        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 26");
//        System.out.println("=================================");
//        System.out.println(this.correctPaths);
        System.out.println("The 1/d with the longest recurring cycle, is: "+this.answer);
        System.out.println("=================================");
    }
}
