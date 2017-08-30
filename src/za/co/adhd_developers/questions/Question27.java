package za.co.adhd_developers.questions;

import za.co.adhd_developers.tools.Question;
import za.co.adhd_developers.tools.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.Hashtable;

/**
 * Created by Grant on 2017/08/30.
 */
public class Question27 implements Question
{
    private Hashtable<Long, Boolean> isPrime = new Hashtable<>();
    private Hashtable<String, Integer> primeCount = new Hashtable<>();

    private Path outPath = new File("C:\\Users\\Grant\\Desktop\\question27_out.txt").toPath();

    @Override
    public void doWork()
    {
        for (int i = 0; i < 80 ; i++)
        {
            double finalResult = Math.pow(i,2) + (-79*i) + 1601;

            if (Utils.isPrime((long)finalResult))
            {
                System.out.println("i: "+i+" | "+finalResult);
            }
        }
    }

    private void writeLine(String line)
    {
//        System.out.println(line);
        try
        {
            Files.write(outPath, line.getBytes(), StandardOpenOption.APPEND);
            Files.write(outPath, ("\n").getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private boolean checkPrime(long number)
    {
        if (!this.isPrime.containsKey(number))
        {
            this.isPrime.put(number, Utils.isPrime(number));
        }
        return this.isPrime.get(number);
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 27");
//        System.out.println("=================================");
//        System.out.println(this.correctPaths);
        System.out.println("The 1/d with the longest recurring cycle, is: ");
        System.out.println("=================================");
    }
}
