package za.co.adhd_developers.questions;

import com.sun.org.apache.xpath.internal.operations.Bool;
import za.co.adhd_developers.tools.Question;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Grant on 2017/08/01.
 */
public class Question26 implements Question
{
    int answer = 0;
    String cycle = "";
    int limit = 1000;

    Hashtable<Integer, String> possibles = new Hashtable<>();

    @Override
    public void doWork()
    {
        BigDecimal result;
        BigDecimal one = new BigDecimal("1");
        for (int i = 2; i < limit; i++)
        {
            try
            {
                one.setScale(2*i+1);
                result = one.divide(new BigDecimal(String.valueOf(i)));

//                String number = result.toString();
//                this.possibles.put(i,number);
            }
            catch (ArithmeticException e)
            {
                result = one.divide(new BigDecimal(String.valueOf(i)), (i-1)*2, RoundingMode.DOWN);

                String number = result.toString();
                String recurring = number.substring(number.indexOf(".")+1);

                int HALF_LENGTH = i-1;
                int start_location = 0;
                int copy_length = 1;

                while (start_location <= HALF_LENGTH || copy_length <= HALF_LENGTH)
                {
                    start_location = 0;
                    while (start_location <= HALF_LENGTH)
                    {
                        String prefix = "";
                        if (start_location > 0)
                        {
                            prefix = recurring.substring(0,start_location);
                        }
                        String str_check = recurring.substring(start_location, start_location+copy_length);


                        String check = prefix;

                        while (check.length() < recurring.length())
                        {
                            check = check+str_check;
                        }

                        if (check.length() > recurring.length())
                        {
                            check = check.substring(0,recurring.length());
                        }

                        if (check.equals(recurring))
                        {
//                            this.possibles.put(i, str_check);
                            if (str_check.length() > this.cycle.length())
                            {
                                this.cycle = str_check;
                                this.answer = i;
                            }
                            start_location = HALF_LENGTH;
                            copy_length = HALF_LENGTH;
                        }

                        start_location++;
                    }
                    copy_length++;
                }

//                String firstItem = recurring.substring(0,1);
//                Pattern firstPat = Pattern.compile(firstItem);
//
//                Matcher firstMatch = firstPat.matcher(recurring);
//                int found = 0;
//                while (firstMatch.find())
//                {
//                    found++;
//                }
//                if (found == recurring.length())
//                {
//                    continue;
//                }
//                int half_length = (int) Math.ceil(((double)recurring.length() / 2.0));
//
//                int start_location = 0;
////                int copy_length = (int) Math.ceil(((double)recurring.length() / 3.0));
//                int copy_length = 1;
//
//                while (start_location <= half_length || copy_length <= half_length)
//                {
//                    copy_length = 1;
//                    while (copy_length <= half_length)
//                    {
//                        String str_check = recurring.substring(start_location, start_location+copy_length);
//
//                        int iterations = recurring.length() / str_check.length();
//
//                        String finalStr = "";
//                        for (int j = 0; j < iterations; j++)
//                        {
//                            finalStr = finalStr+str_check;
//                        }
//
//                        if (recurring.equalsIgnoreCase(finalStr))
////                        if (recurring.matches("(" + str_check + "){" + iterations + "}"))
//                        {
////                            this.possibles.put(i, str_check);
//                            if (str_check.length() > this.cycle.length())
//                            {
//                                this.cycle = str_check;
//                                this.answer = i;
//                            }
//                            copy_length = half_length;
//                            start_location = half_length;
//                        }
//
//                        copy_length++;
//                    }
//                    start_location++;
//                }

//                while (start_location <= half_length || copy_length <= half_length)
//                {
//                    start_location = 0;
//                    while (start_location <= half_length)
//                    {
//                        String str_check = recurring.substring(start_location, start_location+copy_length);
//
//                        String rebuilt = "";
//                        for (int j = 0; j < (recurring.length() / str_check.length()); j++)
//                        {
//                            rebuilt = rebuilt + str_check;
//                        }
//
//                        if (recurring.equals(rebuilt) && str_check.length() < this.cycle.length())
//                        {
//
//                        }
//
//                        if (str_check.length() > this.cycle.length() && recurring.endsWith(str_check))
//                        {
//
//
//                            if (recurring.equals(rebuilt))
//                            {
//                                this.possibles.put(i, str_check);
//                                if (this.cycle.length() < str_check.length())
//                                {
//                                    this.cycle = str_check;
//                                    this.answer = i;
//                                }
//                                start_location = half_length;
//                                copy_length = half_length;
//                            }
//                        }
//
//                        start_location++;
//                    }
//                    copy_length++;
//                }
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
        System.out.println("The cycle for 1/"+this.answer+", is "+this.cycle);
        System.out.println("=================================");

//        for (int i = 0; i < this.limit; i++)
//        {
//            if (this.possibles.get(i) != null)
//            {
//                System.out.println(i + "\t" + this.possibles.get(i));
//            }
//        }
//        for (int key : this.possibles.keySet())
//        {
//            BigDecimal result;
//            BigDecimal one = new BigDecimal("1");
//
//            result = one.divide(new BigDecimal(String.valueOf(key)),(key-1)*2, RoundingMode.DOWN);
//            String number = result.toString();
//
//            System.out.println(key + "\t" + this.possibles.get(key));
//            System.out.println(number);
//            System.out.println("----------------------------");
//        }
    }
}
