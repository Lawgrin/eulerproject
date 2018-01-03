package za.co.adhd_developers.questions;

import za.co.adhd_developers.tools.PolygonalUtil;
import za.co.adhd_developers.tools.Question;
import za.co.adhd_developers.tools.Utils;

import java.util.ArrayList;

public class Question44 implements Question
{
    private int answer = 0;

    @Override
    public void doWork()
    {
        Boolean keepGoing = true;
        int currNumber = 2;

        while (keepGoing)
        {
            int currPentagonal = (int) PolygonalUtil.getPentagonal(currNumber);

            for (int j = 1; j < currNumber; j++)
            {
                int otherPentagonal = (int) PolygonalUtil.getPentagonal(j);

                if (PolygonalUtil.isPentagonal(currPentagonal - otherPentagonal))
                {
                    if (PolygonalUtil.isPentagonal(currPentagonal + otherPentagonal))
                    {
                        this.answer = currPentagonal-otherPentagonal;

                        keepGoing = false;
                        break;
                    }
                }
            }

            currNumber++;
        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 44");
        System.out.println("The difference of th pentagonal pair that matches the criteria, is: "+this.answer);
        System.out.println("=================================");
    }
}
