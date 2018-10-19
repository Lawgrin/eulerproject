package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Hashtable;

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

        int cycleLength = this.cycle.length();

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
                int recurring_length = recurring.length();

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

                        int str_check_Length = str_check.length();
                        if (cycleLength > str_check_Length) {
                            continue;
                        }

                        StringBuilder check = new StringBuilder(prefix);
                        int check_Length = check.length();

                        int total = Math.floorDiv((recurring_length - check_Length), str_check_Length);

                        while (total > 0)
                        {
                            check.append(str_check);
                            total--;
                        }
                        check_Length = check.length();

                        while (check_Length < recurring_length)
                        {
                            check.append(str_check);
                            check_Length = check.length();
                        }

                        if (check_Length > recurring_length)
                        {
                            check = new StringBuilder(check.substring(0, recurring_length));
                        }

                        if (check.toString().compareTo(recurring) == 0)
                        {
//                            this.possibles.put(i, str_check);
                            this.cycle = str_check;
                            this.answer = i;
                            cycleLength = this.cycle.length();

                            start_location = HALF_LENGTH;
                            copy_length = HALF_LENGTH;
                        }

                        start_location++;
                    }
                    copy_length++;
                }
            }
        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 26");
        System.out.println("The 1/d with the longest recurring cycle, is: "+this.answer);
        System.out.println("The cycle for 1/"+this.answer+", is "+this.cycle);
        System.out.println("=================================");
    }
}
