package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.resources.ContinuedFractionAlgorithmParameters;
import za.co.adhd_developers.java.tools.Question;

import java.util.ArrayList;

public class Question64 implements Question
{
    private int answer = 0;

    @Override
    public void doWork()
    {
        for (int S = 1; S < 10000; S++)
        {
            int m0 = 0;
            int d0 = 1;
            int a0 = (int) Math.floor(Math.sqrt(S));

            if (Math.sqrt(S) == a0)
            {
                continue;
            }

            ArrayList<ContinuedFractionAlgorithmParameters> fractionAlgorithmParameters = new ArrayList<>();

            ContinuedFractionAlgorithmParameters tmp = new ContinuedFractionAlgorithmParameters(S, m0, d0, a0);
            fractionAlgorithmParameters.add(tmp);

            while (a0*2 != tmp.getA())
            {
                tmp = new ContinuedFractionAlgorithmParameters(S, tmp.getM(), tmp.getD(), tmp.getA());
                fractionAlgorithmParameters.add(tmp);
            }

            int count = 0;
//            String result = S + " = [" + a0 + ";(";
            for (ContinuedFractionAlgorithmParameters algorithmParameters : fractionAlgorithmParameters)
            {
                count++;
//                result = result + algorithmParameters.getA() + ",";
            }

//            result = result.substring(0,result.length()-1) + ")]";

            if (count % 2 != 0)
            {
                answer++;
            }
        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 64");
        System.out.println("The amount of continued fractions for N ≤ 10000 that have an odd period?, is: "+this.answer);
        System.out.println("=================================");
    }
}
