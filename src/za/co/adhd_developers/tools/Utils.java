package za.co.adhd_developers.tools;

import java.lang.reflect.Array;
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

    //https://codereview.stackexchange.com/questions/24704/efficiently-determining-if-a-number-is-prime
    public static boolean newIsPrime(long number)
    {
        if (number <= 1)
        {
            return false;
        }
        if (number > 2 &&  number % 2 == 0)
        {
            return false;
        }

        int top = (int)Math.sqrt(number)+1;
        for (int i = 3; i < top; i+=2)
        {
            if (number % i == 0)
            {
                return false;
            }
        }

        return true;
    }

    public static boolean isPrime(long number)
    {
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

    public static long getNextPrime(long currPrime)
    {
        long newPrime = 0;

        while (newPrime == 0)
        {
            currPrime++;

            if (newIsPrime(currPrime))
            {
                newPrime = currPrime;
            }
        }

        return newPrime;
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
    public static Long getSmallFactorial(Long number)
    {
        if (number.compareTo(1L) <= 0)
        {
            return 1L;
        }
        else
        {
            Long tmp = getSmallFactorial(number-1);
            return number * tmp;
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

    public static Boolean isTriangleWord(String word)
    {
        Hashtable<String, Integer> alphaToNumeric = new Hashtable<>();
        alphaToNumeric.put("a",1);
        alphaToNumeric.put("b",2);
        alphaToNumeric.put("c",3);
        alphaToNumeric.put("d",4);
        alphaToNumeric.put("e",5);
        alphaToNumeric.put("f",6);
        alphaToNumeric.put("g",7);
        alphaToNumeric.put("h",8);
        alphaToNumeric.put("i",9);
        alphaToNumeric.put("j",10);
        alphaToNumeric.put("k",11);
        alphaToNumeric.put("l",12);
        alphaToNumeric.put("m",13);
        alphaToNumeric.put("n",14);
        alphaToNumeric.put("o",15);
        alphaToNumeric.put("p",16);
        alphaToNumeric.put("q",17);
        alphaToNumeric.put("r",18);
        alphaToNumeric.put("s",19);
        alphaToNumeric.put("t",20);
        alphaToNumeric.put("u",21);
        alphaToNumeric.put("v",22);
        alphaToNumeric.put("w",23);
        alphaToNumeric.put("x",24);
        alphaToNumeric.put("y",25);
        alphaToNumeric.put("z",26);

        int runninTotal = 0;

        for (int i = 0; i < word.length(); i++)
        {
            String letter = String.valueOf(word.charAt(i)).toLowerCase();
            if (alphaToNumeric.containsKey(letter))
            {
                runninTotal += alphaToNumeric.get(letter);
            }
            else
            {
                System.out.println("isTriangleWord - letter missing from list:\""+letter+"\"");
            }
        }

        double wordTotal = (double)runninTotal;
        double triangleNumber = 0;
        int index = 1;

        while (triangleNumber < wordTotal)
        {
            triangleNumber = 0.5 * index * (index + 1);
            index++;
        }

        if (triangleNumber != wordTotal)
        {
            return false;
        }

        return true;

    }

//    public static double getTriangleNumber(double number)
//    {
//        return 0.5 * number * (number + 1);
//    }
//
//    public static double getTriangleOrigin(double number)
//    {
//        double a = 0.5*10;
//        double b = 0.5*10;
//        double c = 0-(number*10);
//
//        double underSqrt = Math.pow(b, 2) + (-4 * (a * c));
//
//        double underDivide = 2*a;
//
//        double sqrt = Math.sqrt(underSqrt);
//
//        double aboveDivide = (b*-1) + sqrt;
//
//        return aboveDivide / underDivide;
//    }
//
//    public static boolean isTriangle(double number)
//    {
//        double ans = getTriangleOrigin(number);
//
//        if (Double.isNaN(ans))
//        {
//            return false;
//        }
//
//        if (ans != Math.floor(ans))
//        {
//            return false;
//        }
//
//        return true;
//    }
//
//    public static double getPentagonal(double number)
//    {
//        return getPentagonal(0, number);
//    }
//
//    public static double getPentagonal(double beginPoint, double jumps)
//    {
//        double endPoint = beginPoint+jumps;
//
//        double begin = ((3*(beginPoint*beginPoint)) - beginPoint) / 2;
//
//        double sec1 = (4 * (endPoint - beginPoint));
//
//        double sec2 = (Utils.getTriangleNumber(endPoint-2) - Utils.getTriangleNumber(beginPoint-2)) * 3;
//
//        return begin + sec1 + sec2;
//    }
//
//    public static double getPentagonalOrigin(double number)
//    {
//        double a = 1.5*10;
//        double b = -0.5*10;
//        double c = 0-(number*10);
//
//        double underSqrt = Math.pow(b, 2) + (-4 * (a * c));
//
//        double underDivide = 2*a;
//
//        double sqrt = Math.sqrt(underSqrt);
//
//        double aboveDivide = (b*-1) + sqrt;
//
//        return aboveDivide / underDivide;
//    }
//
//    public static boolean isPentagonal(double number)
//    {
//        double ans = getPentagonalOrigin(number);
//
//        if (Double.isNaN(ans))
//        {
//            return false;
//        }
//
//        if (ans != Math.floor(ans))
//        {
//            return false;
//        }
//
//        return true;
//    }
//
//    public static double getHexagonalNumber(double number)
//    {
//        return number * ((2 * number) - 1);
//    }
//
//    public static double getHexagonalOrigin(int number)
//    {
//        double a = 2;
//        double b = -1;
//        double c = 0-(number);
//
//        double underSqrt = Math.pow(b, 2) + (-4 * (a * c));
//
//        double underDivide = 2*a;
//
//        double sqrt = Math.sqrt(underSqrt);
//
//        double aboveDivide = (b*-1) + sqrt;
//
//        return aboveDivide / underDivide;
//    }
//
//    public static boolean isHexagonal(int number)
//    {
//        double ans = getHexagonalOrigin(number);
//
//        if (Double.isNaN(ans))
//        {
//            return false;
//        }
//
//        if (ans != Math.floor(ans))
//        {
//            return false;
//        }
//
//        return true;
//    }

    public static boolean isPandigital(String number, int endPoint)
    {
        return isPandigital(number, 1, endPoint);
    }

    public static boolean isPandigital(String number, int startPoint, int endPoint)
    {
        int reqLength = ((endPoint + 1) - startPoint);
        if (number.length() != reqLength)
        {
            return false;
        }

        Hashtable<String, Boolean> checkList = new Hashtable<>();

        for (int i = startPoint; i <= endPoint; i++)
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

        int checkCount = 0;
        for (String key : checkList.keySet())
        {
            if (checkList.get(key))
            {
                checkCount++;
            }
        }

        return checkCount == number.length();
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

    public static <T> List<List<T>> newPermutaion(List<T> libArray, List<T> currArray, int length)
    {
        if (currArray.size() == length)
        {
            List<List<T>> retArray = new ArrayList<>();
            retArray.add(currArray);
            return retArray;
        }
        else
        {

        }
        return new ArrayList<>();
    }

    public static boolean hasSubStringDivisibility(String number)
    {
        if (!number.matches("\\d{10}"))
        {
            return false;
        }

        Hashtable<Integer, Integer> checks = new Hashtable<>();
        checks.put(1,2);
        checks.put(2,3);
        checks.put(3,5);
        checks.put(4,7);
        checks.put(5,11);
        checks.put(6,13);
        checks.put(7,17);

        boolean works = true;
        for (int i = 1; i <= number.length()-3; i++)
        {
            int newNumb = Integer.valueOf(number.substring(i,i+3));

            int comp = checks.get(i);
            if (newNumb % comp != 0)
            {
                works = false;
                break;
            }
        }

        return works;
    }

    public static <T> T[] castArrayToList(ArrayList<T> list)
    {
        if (list.size() <= 0)
        {
            return null;
        }

        T[] newList = (T[]) Array.newInstance(list.get(0).getClass(), list.size());

        newList = list.toArray(newList);

        return newList;
    }

    public static boolean containsDuplicateChars(String data)
    {
        for (int i = 0; i < data.length(); i++)
        {
            for (int j = 0; j < data.length(); j++)
            {
                if (i==j)
                {
                    continue;
                }

                if (data.charAt(i) == data.charAt(j))
                {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean containsSameCharsInDiffOrder(String data1, String data2)
    {
        if (data1.length() != data2.length())
        {
            return false;
        }
        if (data1.equalsIgnoreCase(data2))
        {
            return false;
        }

        for (int i = 0; i < data1.length(); i++)
        {
            String charr = String.valueOf(data1.charAt(i));
            if (!data2.contains(charr))
            {
                return false;
            }
        }

        for (int i = 0; i < data2.length(); i++)
        {
            String charr = String.valueOf(data2.charAt(i));
            if (!data1.contains(charr))
            {
                return false;
            }
        }

        return true;
    }

    public static String reverse(String phrase)
    {
        String tmpPhrase = "";

        for (int i = 0; i < phrase.length(); i++)
        {
            tmpPhrase = phrase.substring(i,i+1) + tmpPhrase;
        }

        return tmpPhrase;
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


    public static String toString(BigInteger bi) {
        StringBuilder sb = new StringBuilder();
        int i = 16;
        while (bi.compareTo(powerOfTen(i)) >= 0)
            i *= 2;


        toString(bi, sb, i);

        int start = 0;
        while (sb.charAt(start) == '0') {
            start++;
            if (start >= sb.length()) {
                start = sb.length()-1;
                break;
            }
        }
        return sb.substring(start);
    }

    private static void toString(BigInteger bi, StringBuilder sb, int digits) {
        if (digits < 18) {
            int start = sb.length();
            for (int i = 0; i < digits; i++)
                sb.append('0');
            long l = bi.longValue();
            for (int i = digits - 1; i >= 0; i--, l /= 10)
                sb.setCharAt(start + i, (char) ('0' + l % 10));
        } else {
            int digits2 = digits / 2;
            BigInteger[] parts = bi.divideAndRemainder(powerOfTen(digits2));
            toString(parts[0], sb, digits - digits2);
            toString(parts[1], sb, digits2);
        }
    }

    private static final Map<Integer, BigInteger> powersOfTen = new HashMap<Integer, BigInteger>();

    private static BigInteger powerOfTen(int digits2) {
        BigInteger tens = powersOfTen.get(digits2);
        if (tens == null)
            powersOfTen.put(digits2, tens = BigInteger.TEN.pow(digits2));
        return tens;
    }


    private static final double LOG2 = Math.log10(2.0);

    /**
     * Computes the natural logarithm of a BigInteger. Works for really big
     * integers (practically unlimited)
     *
     * @param val Argument, positive integer
     * @return Natural logarithm, as in <tt>Math.log()</tt>
     */
    public static double logBigInteger(BigInteger val) {
        int blex = val.bitLength() - 1022; // any value in 60..1023 is ok
        if (blex > 0)
            val = val.shiftRight(blex);
        double res = Math.log10(val.doubleValue());
        return blex > 0 ? res + blex * LOG2 : res;
    }
}
