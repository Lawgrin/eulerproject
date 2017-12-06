package za.co.adhd_developers.tools;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by Grant on 2017/07/28.
 */
public class Utils
{
    public static ArrayList<Long> getFactors(long number)
    {
        long factor = 1;
        long endPoint = number;
        ArrayList<Long> factors = new ArrayList<>();

        while (factor < endPoint)
        {
            if (number % factor == 0 && !factors.contains(factor))
            {
                factors.ensureCapacity(factors.size()+2);
                factors.add(factor);
                endPoint = Math.floorDiv(number, factor);
                factors.add(endPoint);
            }

            factor++;
        }

        return factors;
    }

    public static boolean isPrime(long number)
    {
        Date start = new Date();
        if (number < 2)
        {
            return false;
        }

        if (number != 2 && number % 2 == 0)
        {
            return false;
        }

        if (number != 5 && number % 5 == 0)
        {
            return false;
        }

        long factor = 1;

        while (factor < Math.floorDiv(number,2))
        {
            if (factor % 2 != 0)
            {
                if (factor != 1 && factor != number && number % factor == 0)
                {
                    return false;
                }
            }
            factor++;
        }
        return true;
    }

    public static boolean isPalindromic(String phrase)
    {
        if(phrase.isEmpty())
        {
            return false;
        }

        String tmpPhrase = "";
        for (int i = 0; i < phrase.length(); i++)
        {
            tmpPhrase = phrase.substring(i,i+1) + tmpPhrase;
        }

        return tmpPhrase.equalsIgnoreCase(phrase);
    }

    public static BigInteger factorial(BigInteger number)
    {
        if (number.compareTo(new BigInteger("1")) <= 0)
        {
            return new BigInteger("1");
        }
        else
        {
            BigInteger tmp = factorial((number.subtract(new BigInteger("1"))));
            return number.multiply(tmp);
        }
    }

    public static long sumTheNumber(String number)
    {
        long result = 0;
        String[] digits = number.split("|");

        for (String digit : digits)
        {
            long tmpDigit = Long.parseLong(digit);

            result += tmpDigit;
        }

        return result;
    }

    public static ArrayList<String> getAllCyclicallyRotations(String text)
    {
        String holdText = text;
        ArrayList<String> returningData = new ArrayList<>();

        for (int i = 0; i < text.length(); i++)
        {
            ArrayList<String> hold = new ArrayList<>();

            Collections.addAll(hold, holdText.split("|"));

            String tmpStr = hold.get(0);

            hold.remove(0);

            hold.add(tmpStr);

            holdText = String.join("",hold);
            returningData.add(holdText);
        }

        return returningData;
    }

    public static boolean isPandigital(String number, int endPoint)
    {
        if (number.length() != endPoint)
        {
            return false;
        }

        Hashtable<String, Boolean> checkList = new Hashtable<>();

        for (int i = 1; i <= endPoint; i++)
        {
            checkList.put(String.valueOf(i), false);
        }

        for (String digit : number.split("|"))
        {
            if (!checkList.containsKey(digit))
            {
                return false;
            }

            if (!checkList.get(digit))
            {
                checkList.put(digit, true);
            }
            else
            {
                return false;
            }
        }

        return true;
    }

    public static void permutation(String str, List<String> permutations)
    {
        permutation("", str, permutations);
    }

    //https://stackoverflow.com/questions/4240080/generating-all-permutations-of-a-given-string
    private static void permutation(String prefix, String str, List<String> permutations)
    {
        int n = str.length();
        if (n == 0)
        {
            permutations.add(prefix);
        }
        else
        {
            for (int i = 0; i < n; i++)
            {
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), permutations);
            }
        }
    }

//    public static Hashtable<String, Object> getOutData(Object obj) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException
//    {
//        Hashtable<String, Object> data = new Hashtable<>();
//
//        Class<?> _class = obj.getClass();
//        String packageName = _class.getPackage().getName();
//        while(packageName.equalsIgnoreCase("za.co.adhd_developers"))
//        {
//            _class = _class.getSuperclass();
//            packageName = _class.getPackage().getName();
//            for (Field field : _class.getDeclaredFields())
//            {
//                Object fieldData = field.get(obj);
//
//                if (fieldData instanceof TestClass)
//                {
//                    data.put(field.getName(), getOutData(fieldData));
//                }
//                else
//                {
//                    data.put(field.getName(), fieldData);
//                }
//            }
//        }
//
//        _class = obj.getClass();
//        for (Field field : _class.getDeclaredFields())
//        {
//            Object fieldData = field.get(obj);
//
//            if (fieldData.getClass().getPackage().getName().equalsIgnoreCase("za.co.adhd_developer"))
//            {
//                Method method = fieldData.getClass().getMethod("getOutData", null);
//                Hashtable<String, Object> outData = (Hashtable<String, Object>) method.invoke(fieldData);
//                data.put(field.getName(), outData);
//            }
//            else
//            {
//                data.put(field.getName(), fieldData);
//            }
//        }
//
//        return data;
//    }
}
