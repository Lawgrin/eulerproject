package za.co.adhd_developers.questions;

import za.co.adhd_developers.resources.ContinuedFractionAlgorithmParameters;
import za.co.adhd_developers.tools.Question;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class Question65 implements Question
{
    @Override
    public void doWork()
    {
        int S = 8;

        int m0 = 0;
        int d0 = 1;
        int a0 = (int) Math.floor(Math.sqrt(S));

        ArrayList<ContinuedFractionAlgorithmParameters> fractionAlgorithmParameters = new ArrayList<>();

        ContinuedFractionAlgorithmParameters tmp = new ContinuedFractionAlgorithmParameters(S, m0, d0, a0);
        fractionAlgorithmParameters.add(tmp);

        for (int i = 1; i < 10; i++)
        {
            tmp = new ContinuedFractionAlgorithmParameters(S, tmp.getM(), tmp.getD(), tmp.getA());
            fractionAlgorithmParameters.add(tmp);
        }

        String result = S + " = [" + a0 + ";(";
        for (ContinuedFractionAlgorithmParameters algorithmParameters : fractionAlgorithmParameters)
        {
            result = result + algorithmParameters.getA() + ",";
        }

        result = result.substring(0,result.length()-1) + ")]";

        System.out.println(result);

//        BigInteger two = new BigInteger(String.valueOf(2));
//
//        for (int run = 0; run < 10; run++)
//        {
//            BigInteger numerator = new BigInteger(String.valueOf(1));
//            BigInteger denominator = new BigInteger(String.valueOf(2));
//            for (int i = 0; i < run; i++)
//            {
//                numerator = numerator.add(denominator.multiply(two));
//
//                BigInteger tmp = denominator;
//
//                denominator = numerator;
//                numerator = tmp;
//            }
//
//            numerator = numerator.add(denominator);
//
//            System.out.println(numerator + " / " + denominator);
//
//        }
    }

    @Override
    public void printAnswer() {

    }
}
