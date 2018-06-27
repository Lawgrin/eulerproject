package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.PermUtil;
import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

import java.util.ArrayList;

public class Question51 implements Question
{
    private String answer = "";
    private int length = 6;

    @Override
    public void doWork()
    {
        ArrayList<String> templats = new ArrayList<>();

        ArrayList<String> libArray = new ArrayList<>();

        for (int i = 0; i < length-1; i++)
        {
            libArray.add("d");
        }

        for (int i = 0; i < length-1; i++)
        {
            libArray.add("?");
        }

        PermUtil permUtil = new PermUtil(Utils.castArrayToList(libArray), length);

        String[] perm = (String[]) permUtil.next();

        while (perm != null)
        {
            String outPut = String.join("",perm);
            perm = (String[]) permUtil.next();

            if (!templats.contains(outPut))
            {
                templats.add(outPut);
            }
        }

        for (int i = 100000; i < 1000000; i++)
        {
            for (String item : templats)
            {
                String iStr = String.valueOf(i);
                String frame = "";

                for (int d = 0; d < item.length(); d++)
                {
                    if (item.charAt(d) == "d".charAt(0))
                    {
                        frame += String.valueOf(iStr.charAt(d));
                    }
                    else
                    {
                        frame += "?";
                    }
                }

                int count = 0;
                int failCount = 0;
                for (int r = 0; r < 10; r++)
                {
                    String numberStr = frame.replaceAll("\\?", String.valueOf(r));

                    if (numberStr.startsWith("0"))
                    {
                        failCount++;
                        continue;
                    }

                    Long number = Long.valueOf(numberStr);

                    if (Utils.newIsPrime(number))
                    {
                        count++;
                    }
                    else
                    {
                        failCount++;
                    }

                    if (10 - failCount < 8)
                    {
                        break;
                    }
                }

                if (count > 7)
                {
                    this.answer = frame;
                }
            }

            if (this.answer.length() > 0)
            {
                break;
            }
        }

        for (int i = 0; i < 10; i++)
        {
            String numberStr = this.answer.replaceAll("\\?", String.valueOf(i));

            if (numberStr.startsWith("0"))
            {
                continue;
            }

            Long number = Long.valueOf(numberStr);

            if (Utils.newIsPrime(number))
            {
                this.answer = numberStr;
            }
        }


//        ArrayList<String> templats = new ArrayList<>();
//
//        ArrayList<String> libArray = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++)
//        {
//            libArray.add(String.valueOf(i));
//        }
//
//        for (int i = 0; i < length-1; i++)
//        {
//            libArray.add("?");
//        }
//
//        PermUtil permUtil = new PermUtil(Utils.castArrayToList(libArray), length);
//
//        String[] perm = (String[]) permUtil.next();
//
//        while (perm != null)
//        {
//            String outPut = String.join("",perm);
//            perm = (String[]) permUtil.next();
//
//            if (!outPut.contains("?"))
//            {
//                continue;
//            }
//
//            if (!outPut.matches("^(\\?|[1-9])[0123456789?]*(\\?|[1379])$"))
//            {
//                continue;
//            }
//            if (outPut.startsWith("0"))
//            {
//                continue;
//            }
//            if (outPut.endsWith("0"))
//            {
//                continue;
//            }
//            if (outPut.endsWith("2"))
//            {
//                continue;
//            }
//            if (outPut.endsWith("4"))
//            {
//                continue;
//            }
//            if (outPut.endsWith("6"))
//            {
//                continue;
//            }
//            if (outPut.endsWith("8"))
//            {
//                continue;
//            }
//            if (outPut.endsWith("5"))
//            {
//                continue;
//            }
//            if (!templats.contains(outPut))
//            {
//                templats.add(outPut);
//            }
//            else
//            {
//                System.out.println(outPut);
//            }
//        }
//
//        for (String template : templats)
//        {
//            int count = 0;
//            int failCount = 0;
//            for (int i = 0; i < 10; i++)
//            {
//                String numberStr = template.replaceAll("\\?", String.valueOf(i));
//
//                if (numberStr.startsWith("0"))
//                {
//                    failCount++;
//                    continue;
//                }
//
//                Long number = Long.valueOf(numberStr);
//
//                if (Utils.newIsPrime(number))
//                {
//                    count++;
//                }
//                else
//                {
//                    failCount++;
//                }
//
//                if (10 - failCount < 8)
//                {
//                    break;
//                }
//            }
//
//            if (count > 7)
//            {
//                this.counts.put(template, count);
//            }
//        }
//
//        for (String key : this.counts.keySet())
//        {
//            System.out.println(key + " - " + this.counts.get(key));
//        }


//        int units = 0;
//        int tens = 0;
//        int hundreds = 0;
//        int thousends = 0;
//
//        while (thousends < 10 && hundreds < 10 && units < 10)
//        {
//            int count = 0;
//            for (int i = 0; i < 10; i++)
//            {
//                String numberStr = String.valueOf(thousends) + String.valueOf(hundreds) + String.valueOf(i) + String.valueOf(units);
//                if (numberStr.startsWith("0"))
//                {
//                    break;
//                }
//                Long number = Long.valueOf(numberStr);
//                if (Utils.newIsPrime(number))
//                {
//                    count++;
//                }
//            }
//
//            String key = String.valueOf(thousends) + String.valueOf(hundreds) + "?" + String.valueOf(units);
//
//            if (count > 0)
//            {
//                this.counts.put(key, count);
//            }
//
//            if (units == 9)
//            {
//                units = 0;
//                hundreds++;
//            }
//            else
//            {
//                units++;
//            }
//        }
//
//        for (String key : this.counts.keySet())
//        {
//            System.out.println(key + " - " + this.counts.get(key));
//        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 51");
        System.out.println("The smallest prime which us part of an eight prime value family, is: " + this.answer);
        System.out.println("=================================");
    }
}
