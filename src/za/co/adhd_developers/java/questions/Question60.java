package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.PermUtil;
import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

import java.util.*;

public class Question60 implements Question
{
    private Long answer = 0L;

    @Override
    public void doWork()
    {
        long limit = 10000;

        ArrayList<Long> isPrime = new ArrayList<>();

        long prime = 3;

        while (prime < limit)
        {
            isPrime.add(prime);

            prime = Utils.getNextPrime(prime);
        }

//        System.out.println(isPrime.size());
//        System.out.println(isPrime.get(isPrime.size()-1));

        Set<Long> newIsPrime = new HashSet<>();
        for (int i = 0; i < isPrime.size(); i++)
        {
            for (int j = 0; j < isPrime.size(); j++)
            {
                String newPrimeNum = String.valueOf(isPrime.get(i))+String.valueOf(isPrime.get(j));
                long newPrime = Long.valueOf(newPrimeNum);

                if (Utils.newIsPrime(newPrime))
                {
                    newIsPrime.add(newPrime);
                }
            }
        }

//        System.out.println("===================");
//        System.out.println(newIsPrime.size());

        long fPrime1 = 3;
        long fPrime2 = 3;
        long fPrime3 = 3;
        long fPrime4 = 3;
        long fPrime5 = 3;

        boolean isDone = false;
        boolean isAllPrime = true;
        for (Long prime1 : isPrime)
        {
            fPrime1 = prime1;

            ArrayList<String> option = new ArrayList<>();
            while (option.size() >= 1)
            {
                option.remove(option.size()-1);
            }
            option.add(String.valueOf(prime1));

            for (Long prime2 : isPrime)
            {
                fPrime2 = prime2;

                if (prime1.equals(prime2))
                {
                    continue;
                }
                while (option.size() >= 2)
                {
                    option.remove(option.size()-1);
                }
                option.add(String.valueOf(prime2));

                isAllPrime = true;
                for (int i = 0; i < option.size(); i++)
                {
                    for (int j = 0; j < option.size(); j++)
                    {
                        if (i == j)
                        {
                            continue;
                        }
                        Long possiblePrime = Long.valueOf(option.get(i)+option.get(j));
                        if (!newIsPrime.contains(possiblePrime))
                        {
                            isAllPrime = false;
                        }

                        if (!isAllPrime)
                        {
                            break;
                        }
                    }

                    if (!isAllPrime)
                    {
                        break;
                    }
                }

                if (!isAllPrime)
                {
                    option.remove(String.valueOf(prime2));
                    continue;
                }


                for (Long prime3 : isPrime)
                {
                    fPrime3 = prime3;

                    if (prime1.equals(prime3) || prime2.equals(prime3))
                    {
                        continue;
                    }
                    while (option.size() >= 3)
                    {
                        option.remove(option.size()-1);
                    }
                    option.add(String.valueOf(prime3));

                    isAllPrime = true;
                    for (int i = 0; i < option.size(); i++)
                    {
                        for (int j = 0; j < option.size(); j++)
                        {
                            if (i == j)
                            {
                                continue;
                            }
                            Long possiblePrime = Long.valueOf(option.get(i)+option.get(j));
                            if (!newIsPrime.contains(possiblePrime))
                            {
                                isAllPrime = false;
                            }

                            if (!isAllPrime)
                            {
                                break;
                            }
                        }

                        if (!isAllPrime)
                        {
                            break;
                        }
                    }

                    if (!isAllPrime)
                    {
                        option.remove(String.valueOf(prime3));
                        continue;
                    }

                    for (Long prime4 : isPrime)
                    {
                        fPrime4 = prime4;

                        if (prime1.equals(prime4) || prime2.equals(prime4) || prime3.equals(prime4))
                        {
                            continue;
                        }
                        while (option.size() >= 4)
                        {
                            option.remove(option.size()-1);
                        }
                        option.add(String.valueOf(prime4));

                        isAllPrime = true;
                        for (int i = 0; i < option.size(); i++)
                        {
                            for (int j = 0; j < option.size(); j++)
                            {
                                if (i == j)
                                {
                                    continue;
                                }
                                Long possiblePrime = Long.valueOf(option.get(i)+option.get(j));
                                if (!newIsPrime.contains(possiblePrime))
                                {
                                    isAllPrime = false;
                                }

                                if (!isAllPrime)
                                {
                                    break;
                                }
                            }

                            if (!isAllPrime)
                            {
                                break;
                            }
                        }

                        if (!isAllPrime)
                        {
                            option.remove(String.valueOf(prime4));
                            continue;
                        }

                        for (Long prime5 : isPrime)
                        {
                            fPrime5 = prime5;

                            if (prime1.equals(prime5) || prime2.equals(prime5) || prime3.equals(prime5) || prime4.equals(prime5))
                            {
                                continue;
                            }
                            while (option.size() >= 5)
                            {
                                option.remove(option.size()-1);
                            }
                            option.add(String.valueOf(prime5));

                            isAllPrime = true;
                            for (int i = 0; i < option.size(); i++)
                            {
                                for (int j = 0; j < option.size(); j++)
                                {
                                    if (i == j)
                                    {
                                        continue;
                                    }
                                    Long possiblePrime = Long.valueOf(option.get(i)+option.get(j));
                                    if (!newIsPrime.contains(possiblePrime))
                                    {
                                        isAllPrime = false;
                                    }

                                    if (!isAllPrime)
                                    {
                                        break;
                                    }
                                }

                                if (!isAllPrime)
                                {
                                    break;
                                }
                            }

                            if (!isAllPrime)
                            {
                                option.remove(String.valueOf(prime5));
                            }
                            else
                            {
                                isDone = true;
                                break;
                            }
                        }

                        if (isDone)
                        {
                            break;
                        }
                    }

                    if (isDone)
                    {
                        break;
                    }
                }

                if (isDone)
                {
                    break;
                }
            }

            if (isDone)
            {
                break;
            }
        }

//        System.out.println("===================");

        answer += fPrime1;
        answer += fPrime2;
        answer += fPrime3;
        answer += fPrime4;
        answer += fPrime5;
//        System.out.println(fPrime1);
//        System.out.println(fPrime2);
//        System.out.println(fPrime3);
//        System.out.println(fPrime4);
//        System.out.println(fPrime5);
    }

    private void try1()
    {
        long limit = 1000;
        long prime = 0;

        ArrayList<String> primeOptions = new ArrayList<>();
        while (prime < limit)
        {
            prime = Utils.getNextPrime(prime);
            primeOptions.add(String.valueOf(prime));
        }

        PermUtil permUtil = new PermUtil(Utils.castArrayToList(primeOptions),4);

        String[] perm = (String[]) permUtil.next();

        Hashtable<String,ArrayList<String>> uniqueList = new Hashtable<>();

        int count = 1;
        while (perm != null)
        {
            if (!Utils.newIsPrime(Long.valueOf(perm[0]+perm[1])))
            {
                perm = (String[]) permUtil.next();
                continue;
            }
            if (!Utils.newIsPrime(Long.valueOf(perm[0]+perm[2])))
            {
                perm = (String[]) permUtil.next();
                continue;
            }
            if (!Utils.newIsPrime(Long.valueOf(perm[0]+perm[3])))
            {
                perm = (String[]) permUtil.next();
                continue;
            }
            if (!Utils.newIsPrime(Long.valueOf(perm[1]+perm[0])))
            {
                perm = (String[]) permUtil.next();
                continue;
            }
            if (!Utils.newIsPrime(Long.valueOf(perm[1]+perm[2])))
            {
                perm = (String[]) permUtil.next();
                continue;
            }
            if (!Utils.newIsPrime(Long.valueOf(perm[1]+perm[3])))
            {
                perm = (String[]) permUtil.next();
                continue;
            }
            if (!Utils.newIsPrime(Long.valueOf(perm[2]+perm[0])))
            {
                perm = (String[]) permUtil.next();
                continue;
            }
            if (!Utils.newIsPrime(Long.valueOf(perm[2]+perm[1])))
            {
                perm = (String[]) permUtil.next();
                continue;
            }
            if (!Utils.newIsPrime(Long.valueOf(perm[2]+perm[3])))
            {
                perm = (String[]) permUtil.next();
                continue;
            }
            if (!Utils.newIsPrime(Long.valueOf(perm[3]+perm[0])))
            {
                perm = (String[]) permUtil.next();
                continue;
            }
            if (!Utils.newIsPrime(Long.valueOf(perm[3]+perm[1])))
            {
                perm = (String[]) permUtil.next();
                continue;
            }
            if (!Utils.newIsPrime(Long.valueOf(perm[3]+perm[2])))
            {
                perm = (String[]) permUtil.next();
                continue;
            }


            ArrayList<String> tmp = new ArrayList<>();

            for (String item : perm)
            {
                tmp.add(item);
            }

            Collections.sort(tmp);

            String key = String.join("|",tmp);

            if (!uniqueList.containsKey(key))
            {
                uniqueList.put(key, tmp);
            }

            perm = (String[]) permUtil.next();
//            System.out.println("count: "+count);
            count++;
        }

//        System.out.println(uniqueList.size());
    }

    private void try2()
    {
        Hashtable<String, Boolean> isPrime = new Hashtable<>();
        long limit = 1000;

        ArrayList<String> options = new ArrayList<>();

        for (int i = 0; i < limit; i++)
        {
            if (Utils.newIsPrime((long)i))
            {
                options.add(String.valueOf(i));
            }
        }

        PermUtil permUtil = new PermUtil(Utils.castArrayToList(options), 2);

        String[] perm = (String[]) permUtil.next();

        while (perm != null)
        {
            String newNumberStr = String.join("", perm);

            if (!isPrime.keySet().contains(newNumberStr))
            {
                boolean numIsPrime = Utils.newIsPrime(Long.valueOf(newNumberStr));
                isPrime.put(newNumberStr, numIsPrime);
            }

            perm = (String[]) permUtil.next();
        }

        System.out.println("====");

        long prime1 = 3;
        long prime2 = Utils.getNextPrime(prime1);
        long prime3 = Utils.getNextPrime(prime2);
        long prime4 = Utils.getNextPrime(prime3);
        long prime5 = Utils.getNextPrime(prime4);

        while (prime1 < limit)
        {
            while (prime2 < limit)
            {
                while (prime3 < limit)
                {
                    while (prime4 < limit)
                    {
                        while (prime5 < limit)
                        {
                            ArrayList<String> option = new ArrayList<>();

                            option.add(String.valueOf(prime1));
                            option.add(String.valueOf(prime2));
                            option.add(String.valueOf(prime3));
                            option.add(String.valueOf(prime4));
                            option.add(String.valueOf(prime5));

                            permUtil = new PermUtil(Utils.castArrayToList(option), 2);

                            perm = (String[]) permUtil.next();

                            boolean isAllPrime = true;
                            while (perm != null)
                            {
                                String newNumberStr = String.join("", perm);

                                isAllPrime = isPrime.get(newNumberStr);

                                if (!isAllPrime)
                                {
                                    break;
                                }

                                perm = (String[]) permUtil.next();
                            }

                            if (!isAllPrime)
                            {
                                prime5 = Utils.getNextPrime(prime5);
                            }
                            else
                            {
                                break;
                            }
                        }

                        if (prime5 < limit)
                        {
                            break;
                        }

                        prime4 = Utils.getNextPrime(prime4);
                        prime5 = Utils.getNextPrime(prime4);
                    }

                    if (prime4 < limit)
                    {
                        break;
                    }

                    prime3 = Utils.getNextPrime(prime3);
                    prime4 = Utils.getNextPrime(prime3);
                    prime5 = Utils.getNextPrime(prime4);
                }

                if (prime3 < limit)
                {
                    break;
                }

                prime2 = Utils.getNextPrime(prime2);
                prime3 = Utils.getNextPrime(prime2);
                prime4 = Utils.getNextPrime(prime3);
                prime5 = Utils.getNextPrime(prime4);

                System.out.println(">>"+prime2);
            }

            if (prime2 < limit)
            {
                break;
            }

            prime1 = Utils.getNextPrime(prime1);
            prime2 = Utils.getNextPrime(prime1);
            prime3 = Utils.getNextPrime(prime2);
            prime4 = Utils.getNextPrime(prime3);
            prime5 = Utils.getNextPrime(prime4);

            System.out.println(">"+prime1);
        }

        System.out.println(prime1);
        System.out.println(prime2);
        System.out.println(prime3);
        System.out.println(prime4);
        System.out.println(prime5);


    }

    private void try3()
    {
        Hashtable<String, Boolean> isAllPrime = new Hashtable<>();

        Hashtable<String, Boolean> numIsPrime = new Hashtable<>();

        ArrayList<Long> primeNumber = new ArrayList<>();

        long limit = 1000;

        long prime = 0;
        while (prime < limit)
        {
            prime = Utils.getNextPrime(prime);
            if (prime < limit)
            {
                primeNumber.add(prime);
                numIsPrime.put(String.valueOf(prime), true);
            }
        }

        System.out.println("==== Start ====");
        PermUtil permUtil = new PermUtil(Utils.castArrayToList(primeNumber), 4);

        Long[] perm = (Long[]) permUtil.next();

        while (perm != null)
        {
            ArrayList<String> keyParts = new ArrayList<>();

            for (Long item : perm)
            {
                keyParts.add(String.valueOf(item));
            }

            Collections.sort(keyParts);

            String key = String.join("|", keyParts);

            if (!isAllPrime.keySet().contains(key))
            {
                boolean allPrime = true;
                for (int i = 0; i < perm.length; i++)
                {
                    for (int j = 0; j < perm.length; j++)
                    {
                        if (i == j)
                        {
                            continue;
                        }

                        String newNumberStr = String.valueOf(perm[i])+String.valueOf(perm[j]);
                        if (numIsPrime.containsKey(newNumberStr))
                        {
                            allPrime = numIsPrime.get(newNumberStr);
                        }
                        else if (!Utils.newIsPrime(Long.valueOf(newNumberStr)))
                        {
                            allPrime = false;
                            numIsPrime.put(newNumberStr, false);
                        }
                        else
                        {
                            numIsPrime.put(newNumberStr, true);
                        }

                        if (!allPrime)
                        {
                            break;
                        }
                    }

                    if (!allPrime)
                    {
                        break;
                    }
                }

                if (allPrime)
                {
                    isAllPrime.put(key, allPrime);
                }
            }
            perm = (Long[]) permUtil.next();
        }

        ArrayList<String> keys = new ArrayList<>();

        keys.addAll(isAllPrime.keySet());

        Collections.sort(keys);

        for (String key : keys)
        {
            System.out.println(key+": "+isAllPrime.get(key));
        }
        System.out.println(isAllPrime.size());
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 60");
        System.out.println("The lowest sum for a set of five primes for which any two primes concatenate to produce another prime, is: "+this.answer);
        System.out.println("=================================");
    }
}
