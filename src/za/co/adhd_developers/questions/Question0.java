package za.co.adhd_developers.questions;

import za.co.adhd_developers.tools.Question;
import za.co.adhd_developers.tools.Utils;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Question0 implements Question
{
    @Override
    public void doWork()
    {
        int pow = 353928;

        int length = String.valueOf(pow).length();

        if (length % 2 == 0)
        {
            length--;
        }

        long start = System.nanoTime();
        String args = "%-"+length/2+"d";
        System.out.println("args: "+args);
        BigInteger counter = new BigInteger(String.format(args,1).replace(" ", "0"));

        long afterCounter = System.nanoTime();
        counter = counter.subtract(BigInteger.ONE);

        long afterSubtract = System.nanoTime();
        BigInteger ans = counter.pow(pow);

        long afterPow = System.nanoTime();
        byte[] output = ans.toByteArray();

        long afterToByteArray = System.nanoTime();
        int finalLength = output.length;

        long afterLength = System.nanoTime();
        String outputString = ans.toString();

        long afterStringMake = System.nanoTime();
        int count = 0;

        double possible = Utils.logBigInteger(ans);
        possible = Math.floor(possible)+1;

        long afterDivideIdea = System.nanoTime();
        System.out.println(finalLength);
        System.out.println(outputString.length());
        System.out.println(possible);

        System.out.printf("after Counter: %.3f%n", (afterCounter - start) / 1e6);
        System.out.printf("after Subtract: %.3f%n", (afterSubtract - afterCounter) / 1e6);
        System.out.printf("after Pow: %.3f%n", (afterPow - afterSubtract) / 1e6);
        System.out.printf("after To Byte Array: %.3f%n", (afterToByteArray - afterPow) / 1e6);
        System.out.printf("after Length: %.3f%n", (afterLength - afterToByteArray) / 1e6);
        System.out.printf("after String Make: %.3f%n", (afterStringMake - afterLength) / 1e6);
        System.out.printf("after Divide Idea: %.3f%n", (afterDivideIdea - afterStringMake) / 1e6);

//        Utils.logBigInteger()

//        double logLarge = Utils.logBigInteger(BigInteger.valueOf(8).pow(1000));
//
//        System.out.println(Math.floor(logLarge));
//        System.out.println(Math.floor(logLarge)+1);
//
//
//        //134217728=8^9
//
//        int pow = 9;
//
//        //10^(pow-1) = b^pow
//        //10^(9-1) = b^9
//        //8 = 9*log10(b)
//        //8/9 = log10(b)
//        //10^(8/9) = b
//
//
//        System.out.println(Math.pow(10,(8d/9d)));
    }

    @Override
    public void printAnswer()
    {
    }
}
