package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

public class Question52 implements Question
{
    private String answer = "";
    @Override
    public void doWork()
    {
        for (int i = 1; i < 1000000; i++)
        {
            int times2 = i*2;
            if (!Utils.containsSameCharsInDiffOrder(String.valueOf(i), String.valueOf(times2)))
            {
                continue;
            }

            int times3 = i*3;
            if (!Utils.containsSameCharsInDiffOrder(String.valueOf(i), String.valueOf(times3)))
            {
                continue;
            }
            if (!Utils.containsSameCharsInDiffOrder(String.valueOf(times2), String.valueOf(times3)))
            {
                continue;
            }

            int times4 = i*4;
            if (!Utils.containsSameCharsInDiffOrder(String.valueOf(i), String.valueOf(times4)))
            {
                continue;
            }
            if (!Utils.containsSameCharsInDiffOrder(String.valueOf(times2), String.valueOf(times4)))
            {
                continue;
            }
            if (!Utils.containsSameCharsInDiffOrder(String.valueOf(times3), String.valueOf(times4)))
            {
                continue;
            }

            int times5 = i*5;
            if (!Utils.containsSameCharsInDiffOrder(String.valueOf(i), String.valueOf(times5)))
            {
                continue;
            }
            if (!Utils.containsSameCharsInDiffOrder(String.valueOf(times2), String.valueOf(times5)))
            {
                continue;
            }
            if (!Utils.containsSameCharsInDiffOrder(String.valueOf(times3), String.valueOf(times5)))
            {
                continue;
            }
            if (!Utils.containsSameCharsInDiffOrder(String.valueOf(times4), String.valueOf(times5)))
            {
                continue;
            }

            int times6 = i*6;
            if (!Utils.containsSameCharsInDiffOrder(String.valueOf(i), String.valueOf(times6)))
            {
                continue;
            }
            if (!Utils.containsSameCharsInDiffOrder(String.valueOf(times2), String.valueOf(times6)))
            {
                continue;
            }
            if (!Utils.containsSameCharsInDiffOrder(String.valueOf(times3), String.valueOf(times6)))
            {
                continue;
            }
            if (!Utils.containsSameCharsInDiffOrder(String.valueOf(times4), String.valueOf(times6)))
            {
                continue;
            }
            if (!Utils.containsSameCharsInDiffOrder(String.valueOf(times5), String.valueOf(times6)))
            {
                continue;
            }

            this.answer = String.valueOf(i);
            break;
        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 51");
        System.out.println("The smallest posotive integer that 2x, 3x, 4x, 5x, and 6x, contain the same digits, is: " + this.answer);
        System.out.println("=================================");
    }
}
