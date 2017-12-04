package za.co.adhd_developers.questions;

import za.co.adhd_developers.tools.Question;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Question33 implements Question
{
    private int finalNume = 0;
    private int finalDenom = 0;
    private int answer = 0;
    @Override
    public void doWork()
    {
        for (int nume = 10; nume < 100; nume++)
        {
            for (int denom = nume+1; denom < 100; denom++)
            {
                String numeStr = String.valueOf(nume);
                String denomStr = String.valueOf(denom);

                String[] numeStrs = numeStr.split("|");
                String[] denomStrs = denomStr.split("|");

                String commonNumStr = "";
                int commonNum = 0;
                int newNume = nume;
                int newDenom = denom;

                if (numeStrs[0].equalsIgnoreCase(denomStrs[1]))
                {
                    commonNumStr = numeStrs[0];
                    commonNum = Integer.valueOf(commonNumStr);
                    newNume = nume - (commonNum*10);
                    newDenom = (denom - commonNum) /10;
                }
                else if (numeStrs[1].equalsIgnoreCase(denomStrs[0]))
                {
                    commonNumStr = numeStrs[1];
                    commonNum = Integer.valueOf(commonNumStr);
                    newNume = (nume - commonNum) /10;
                    newDenom = denom - (commonNum*10);
                }

                if (newNume > 10 || newDenom > 10 || newNume == 0 || newDenom==0)
                {
                    continue;
                }

                BigDecimal larger = new BigDecimal(nume);
                larger = larger.divide(new BigDecimal(denom), (denom-1)*2, RoundingMode.DOWN);


                BigDecimal smaller = new BigDecimal(newNume);
                smaller = smaller.divide(new BigDecimal(newDenom), (denom-1)*2, RoundingMode.DOWN);

                if (larger.equals(smaller))
                {
                    if (this.finalDenom == 0)
                    {
                        finalDenom = newDenom;
                    }
                    else
                    {
                        finalDenom = finalDenom * newDenom;
                    }

                    if (this.finalNume == 0)
                    {
                        this.finalNume = newNume;
                    }
                    else
                    {
                        this.finalNume = this.finalNume * newNume;
                    }
                }
            }
        }

        this.answer = this.finalDenom / this.finalNume;
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 33");
        System.out.println("The product of all denominators, is: " + this.answer);
        System.out.println("=================================");
    }
}
