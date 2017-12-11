package za.co.adhd_developers.questions;

import za.co.adhd_developers.tools.Question;
import za.co.adhd_developers.tools.Utils;

import java.util.ArrayList;

public class Question0 implements Question
{
    private ArrayList<Long> primesBelowTarget = new ArrayList<>();

    @Override
    public void doWork()
    {
        long startingPrime = 2;
        long endAt = Utils.getNextPrime(1000000);
        while (startingPrime <= endAt)
        {
            this.primesBelowTarget.add(startingPrime);
            startingPrime = Utils.getNextPrime(startingPrime);
        }
    }

    @Override
    public void printAnswer() {

    }
}
