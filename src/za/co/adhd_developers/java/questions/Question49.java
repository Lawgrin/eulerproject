package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.PermUtil;
import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

import java.util.*;

public class Question49 implements Question
{
    private String answer = "";
    @Override
    public void doWork()
    {
        wayFive();
    }

    private void wayFive()
    {
        for (int currNumber = 1000; currNumber < 10000; currNumber++)
        {
            if (!Utils.isPrime(currNumber))
            {
                continue;
            }

            for (int diff = 0; diff < 10000; diff++)
            {
                int secondNumber = currNumber + diff;

                if (secondNumber > 9999)
                {
                    continue;
                }
                if (!Utils.containsSameCharsInDiffOrder(String.valueOf(currNumber), String.valueOf(secondNumber)))
                {
                    continue;
                }
                if (!Utils.isPrime(secondNumber))
                {
                    continue;
                }

                int lastNumber = secondNumber + diff;

                if (lastNumber > 9999)
                {
                    continue;
                }
                if (!Utils.containsSameCharsInDiffOrder(String.valueOf(currNumber), String.valueOf(lastNumber)))
                {
                    continue;
                }
                if (!Utils.containsSameCharsInDiffOrder(String.valueOf(secondNumber), String.valueOf(lastNumber)))
                {
                    continue;
                }
                if (!Utils.isPrime(lastNumber))
                {
                    continue;
                }

                this.answer = String.valueOf(currNumber) + String.valueOf(secondNumber) + String.valueOf(lastNumber);
            }
        }
    }

    private void wayFour()
    {
        for (int firstNumber = 1000; firstNumber < 10000; firstNumber++)
        {
            if (Utils.containsDuplicateChars(String.valueOf(firstNumber)))
            {
                continue;
            }

            if (!Utils.isPrime(firstNumber))
            {
                continue;
            }

            for (int secondNumber = firstNumber+1; secondNumber < 10000; secondNumber++)
            {
                if (Utils.containsDuplicateChars(String.valueOf(secondNumber)))
                {
                    continue;
                }
                if (!Utils.containsSameCharsInDiffOrder(String.valueOf(firstNumber), String.valueOf(secondNumber)))
                {
                    continue;
                }
                if (!Utils.isPrime(secondNumber))
                {
                    continue;
                }

                for (int lastNumber = secondNumber+1; lastNumber < 10000; lastNumber++)
                {
                    if (Utils.containsDuplicateChars(String.valueOf(lastNumber)))
                    {
                        continue;
                    }
                    if (!Utils.containsSameCharsInDiffOrder(String.valueOf(firstNumber), String.valueOf(lastNumber)))
                    {
                        continue;
                    }
                    if (!Utils.containsSameCharsInDiffOrder(String.valueOf(secondNumber), String.valueOf(lastNumber)))
                    {
                        continue;
                    }
                    if (!Utils.isPrime(lastNumber))
                    {
                        continue;
                    }

                    int fsDiff = secondNumber - firstNumber;
                    int slDiff = lastNumber - secondNumber;
                    if (fsDiff == slDiff)
                    {
                        System.out.println(firstNumber + "|" + secondNumber + "|" + lastNumber + " - " + fsDiff + "|" + slDiff);
                    }
                }
            }
        }
    }

    private void wayThree()
    {
        for (int currNum = 1000; currNum < 10000; currNum++)
        {
            if (Utils.containsDuplicateChars(String.valueOf(currNum)))
            {
                continue;
            }
            if (!Utils.isPrime(currNum))
            {
                continue;
            }

            for (int compareTo = currNum+1; compareTo < 10000; compareTo++)
            {
                if (Utils.containsDuplicateChars(String.valueOf(compareTo)))
                {
                    continue;
                }
                if (!Utils.containsSameCharsInDiffOrder(String.valueOf(currNum), String.valueOf(compareTo)))
                {
                    continue;
                }
                if (!Utils.isPrime(compareTo))
                {
                    continue;
                }
                int diff = compareTo - currNum;

                int nextNumber = compareTo + diff;

                if (nextNumber > 9999)
                {
                    continue;
                }

                if (Utils.containsDuplicateChars(String.valueOf(nextNumber)))
                {
                    continue;
                }
                if (!Utils.containsSameCharsInDiffOrder(String.valueOf(currNum), String.valueOf(nextNumber)))
                {
                    continue;
                }
                if (!Utils.containsSameCharsInDiffOrder(String.valueOf(compareTo), String.valueOf(nextNumber)))
                {
                    continue;
                }
                if (!Utils.isPrime(nextNumber))
                {
                    continue;
                }

                System.out.println(currNum + "|" + compareTo + "|" + nextNumber + " - " + diff);
            }
        }
    }

    private void wayTwo()
    {
        ArrayList<String> libArray = new ArrayList<>();

        for (int i = 1000; i < 10000; i++)
        {
            if (!Utils.isPrime(i))
            {
                continue;
            }

            String number = String.valueOf(i);

            if (!Utils.containsDuplicateChars(number))
            {
                libArray.add(number);
            }
        }

        Hashtable<String, ArrayList<String>> groups = new Hashtable<>();

        for (int p = 0; p < libArray.size(); p++)
        {
            String baseLine = libArray.get(p);

            List<String> chars = Arrays.asList(baseLine.split("|"));
            Collections.sort(chars);

            String newKey = String.join("",chars);

            if (groups.containsKey(newKey))
            {
                continue;
            }

            ArrayList<String> group = new ArrayList<>();
            group.add(baseLine);

            for (String item : libArray)
            {
                if (item.equalsIgnoreCase(baseLine))
                {
                    continue;
                }

                boolean hasAll = true;
                for (int i = 0; i < chars.size(); i++)
                {
                    if (!item.contains(chars.get(i)))
                    {
                        hasAll = false;
                        break;
                    }
                }

                if (hasAll)
                {
                    group.add(item);
                }
            }

            Collections.sort(group);
            groups.put(newKey, group);
        }


        ArrayList<String> keys = new ArrayList<>();

        keys.addAll(groups.keySet());
        Collections.sort(keys);

        for (String key : keys)
        {
            if (groups.get(key).size() < 3)
            {
                groups.remove(key);
            }
        }

        keys.clear();
        keys.addAll(groups.keySet());
        Collections.sort(keys);

        for (String key : keys)
        {
            ArrayList<String> items = new ArrayList<>(groups.get(key));

            if (items.size() < 3)
            {
                continue;
            }

            for (int i = 0; i < items.size(); i++)
            {
                int currNum = Integer.valueOf(items.get(i));

                for (int j = i+1; j < items.size(); j++)
                {
                    int checkAgainst = Integer.valueOf(items.get(j));
                    int diff = checkAgainst - currNum;

                    int checkFor = checkAgainst+diff;

                    if (items.contains(String.valueOf(checkFor)))
                    {
                        if (Utils.isPrime(currNum) && Utils.isPrime(checkAgainst) && Utils.isPrime(checkFor))
                        {
                            System.out.println(currNum + "|" + checkAgainst + "|" + checkFor + " - " + diff);
                        }
                    }
                }
            }
        }
    }

    private void wayOne()
    {
        Hashtable<String, ArrayList<String>> groups = new Hashtable<>();
        ArrayList<String> primes = new ArrayList<>();
        ArrayList<String> libArray = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            libArray.add(String.valueOf(i));
        }

        PermUtil permUtil = new PermUtil(Utils.castArrayToList(libArray), 4);

        String[] perm = (String[]) permUtil.next();

        while (perm != null)
        {
            String outPut = String.join("",perm);

            if (!outPut.startsWith("0"))
            {
                if (!Utils.containsDuplicateChars(outPut))
                {
                    if(Utils.isPrime(Long.valueOf(outPut)))
                    {
                        primes.add(outPut);
                    }
                }
            }

            perm = (String[]) permUtil.next();
        }

        for (int p = 0; p < primes.size(); p++)
        {
            String baseLine = primes.get(p);

            List<String> chars = Arrays.asList(baseLine.split("|"));
            Collections.sort(chars);

            String newKey = String.join("",chars);

            if (groups.containsKey(newKey))
            {
                continue;
            }

            ArrayList<String> group = new ArrayList<>();
            group.add(baseLine);

            for (String item : primes)
            {
                if (item.equalsIgnoreCase(baseLine))
                {
                    continue;
                }

                if (Utils.containsSameCharsInDiffOrder(baseLine, item))
                {
                    group.add(item);
                }
            }

            Collections.sort(group);
            groups.put(newKey, group);
        }

        ArrayList<String> keys = new ArrayList<>();

        keys.addAll(groups.keySet());

        Collections.sort(keys);
        for (String key : keys)
        {
            ArrayList<String> items = new ArrayList<>(groups.get(key));

            if (items.size() < 3)
            {
                continue;
            }

            for (int i = 0; i < items.size(); i++)
            {
                int currNum = Integer.valueOf(items.get(i));

                for (int j = i+1; j < items.size(); j++)
                {
                    int checkAgainst = Integer.valueOf(items.get(j));
                    int diff = checkAgainst - currNum;

                    int checkFor = checkAgainst+diff;

                    if (items.contains(String.valueOf(checkFor)))
                    {
                        System.out.println(currNum + "|" + checkAgainst + "|" + checkFor + " - " + diff);
                    }
                }
            }
        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 49");
        System.out.println("The 12-digit number do you form by concatenating the three terms in this sequence, is: " + this.answer);
        System.out.println("=================================");
    }
}
