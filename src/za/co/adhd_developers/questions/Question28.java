package za.co.adhd_developers.questions;

import za.co.adhd_developers.tools.Question;

public class Question28 implements Question
{
    int limit = 1001;
    int answer = 0;

    @Override
    public void doWork()
    {
        int currentTotal = 3;

        int currentNumber = 3;
        int nextJump = 10;
        int endPoint = (limit*limit)-((limit-1)*3);

        while (currentNumber<endPoint)
        {
            currentNumber += nextJump;
            nextJump += 8;
            currentTotal +=currentNumber;
        }

        currentTotal += 5;
        currentNumber = 5;
        nextJump = 12;
        endPoint = (limit*limit)-((limit-1)*2);

        while (currentNumber<endPoint)
        {
            currentNumber += nextJump;
            nextJump += 8;
            currentTotal +=currentNumber;
        }

        currentTotal += 7;
        currentNumber = 7;
        nextJump = 14;
        endPoint = (limit*limit)-(limit-1);

        while (currentNumber<endPoint)
        {
            currentNumber += nextJump;
            nextJump += 8;
            currentTotal +=currentNumber;
        }

        currentTotal += 9;
        currentNumber = 9;
        nextJump = 16;
        endPoint = (limit*limit);

        while (currentNumber<endPoint)
        {
            currentNumber += nextJump;
            nextJump += 8;
            currentTotal +=currentNumber;
        }

        this.answer = currentTotal+1;
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 28");
        System.out.println("The sum of the numbers on the diagonals in a 1001 by 1001 spiral, is: "+this.answer);
        System.out.println("=================================");

    }
}
