package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.PolygonalUtil;
import za.co.adhd_developers.java.tools.Question;

public class Question45 implements Question
{
    private double answer = 0;
    private double above = 40755;

    @Override
    public void doWork()
    {
        int currIndex = 1;
        while (this.answer == 0)
        {
            double currNumber = 0;
            try
            {
                currNumber = PolygonalUtil.getHexagonalNumber(currIndex);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                this.answer = -1;
                break;
            }

            if (currNumber > this.above)
            {
                if (PolygonalUtil.isPentagonal(currNumber))
                {
                    if (PolygonalUtil.isTriangle(currNumber))
                    {
                        this.answer = currNumber;
                    }
                }
            }
            currIndex++;
        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 45");
        System.out.println("The next triangle number that is also pentagonal and hexagonal, is: "+(long)this.answer);
        System.out.println("=================================");
    }
}
