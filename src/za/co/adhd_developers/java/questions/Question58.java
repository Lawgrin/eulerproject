package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

public class Question58 implements Question
{
    private int answer = 0;

    @Override
    public void doWork()
    {
        int start = 1;

        int interation = 1;

        int jump = 2;

        int currNumber = start;

        double isPrime = 0;
        double total = 1;

        double percent = 100.0;

        while (percent > 10)
        {
            for (int corner = 0; corner < 4; corner++)
            {
                total++;
                currNumber += jump;
//                System.out.println(interation+"|"+(corner+1)+"|"+currNumber);
                if (Utils.newIsPrime(currNumber))
                {
                    isPrime++;
                }
            }
            jump += 2;
            interation++;

            percent = (isPrime / total)*100;
        }

        this.answer = jump-1;
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 58");
        System.out.println("The length of the side where it falls below 10%, is: "+this.answer);
        System.out.println("=================================");
    }
}
