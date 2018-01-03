package za.co.adhd_developers.questions;

import za.co.adhd_developers.tools.Question;
import za.co.adhd_developers.tools.Utils;

import java.math.BigInteger;

public class Question63 implements Question
{
    private int answer = 0;

    @Override
    public void doWork()
    {
        long start = System.nanoTime();
        for (int pow = 1; pow < Integer.MAX_VALUE ; pow++)
        {
//            if (pow % 1000 == 0)
//            {
//                long end = System.nanoTime();
//                System.out.printf("pow: %d | %.3f ms%n", pow, (end - start) / 1e6);
//
//                start = System.nanoTime();
//            }

            BigInteger counter = BigInteger.ZERO;

            int stringLeng = String.valueOf(pow).length();

            if (stringLeng % 2 != 0)
            {
                stringLeng = stringLeng-1;
            }

            if (stringLeng != 0)
            {
                String args = "%-"+stringLeng/2+"d";
                counter = new BigInteger(String.format(args,1).replace(" ", "0"));

                counter = counter.subtract(BigInteger.ONE);
            }

            BigInteger ans = BigInteger.ZERO;

            stringLeng = Utils.toString(ans).length();

            while (ans.compareTo(BigInteger.ZERO) == 0 || stringLeng < pow)
            {
                counter = counter.add(BigInteger.ONE);
                ans = counter.pow(pow);

                stringLeng = (int)Math.floor(Utils.logBigInteger(ans))+1;
            }

            if (ans.compareTo(BigInteger.ZERO) == 0)
            {
                continue;
            }
            if (stringLeng == pow)
            {
                System.out.println(counter.toString()+"^"+pow + ": " + Utils.toString(ans));
                this.answer++;
            }
        }

        System.out.println(this.answer);
    }

    @Override
    public void printAnswer()
    {

    }
}
