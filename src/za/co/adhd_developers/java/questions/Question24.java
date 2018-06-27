package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;

import java.util.Arrays;

/**
 * Created by Grant on 2017/08/01.
 */
public class Question24 implements Question
{
    String answer = "";

    int limit = 1000000;


    //http://www.geeksforgeeks.org/lexicographic-permutations-of-string/
    private String generateNextPermutations(String previousPerm)
    {
        String[] hold = previousPerm.split("|");

        int movingIndex = -1;
        int movingToIndex = -1;

        for (int i = 0; i < hold.length-1; i++)
        {
            if(hold[i].compareTo(hold[i+1]) < 0)
            {
                movingIndex = i;
            }
        }

        if(movingIndex == -1)
        {
            return previousPerm;
        }

        for (int i = movingIndex+1; i < hold.length; i++)
        {
            if (hold[i].compareTo(hold[movingIndex]) > 0)
            {
                if (movingToIndex == -1)
                {
                    movingToIndex = i;
                }
                else if (hold[i].compareTo(hold[movingToIndex]) < 0)
                {
                    movingToIndex = i;
                }
            }
        }

        if(movingToIndex == -1)
        {
            return previousPerm;
        }

        String movingChar = hold[movingIndex];
        String movingToChar = hold[movingToIndex];

        hold[movingIndex] = movingToChar;
        hold[movingToIndex] = movingChar;

        String locked = String.join("", hold).substring(0,movingIndex+1);
        String[] sorting = String.join("", hold).substring(movingIndex+1).split("|");

        Arrays.sort(sorting);

        return locked+(String.join("", sorting));
    }


    @Override
    public void doWork()
    {
        String last = "0123456789";
        int count = 1;

        while (count != 1000000)
        {
            last = generateNextPermutations(last);
            count++;
        }

        this.answer = last;
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 24");
//        System.out.println("=================================");
//        System.out.println(this.correctPaths);
        System.out.println("The millionth lexicographic permutation, is: "+this.answer);
        System.out.println("=================================");
    }
}
