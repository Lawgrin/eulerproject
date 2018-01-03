package za.co.adhd_developers.tools;

public class PolygonalUtil
{
    //Triangle
    public static double getTriangleNumber(double number)
    {
        return 0.5 * number * (number + 1);
    }

    public static double getTriangleOrigin(double number)
    {
        double a = 0.5*10;
        double b = 0.5*10;
        double c = 0-(number*10);

        double underSqrt = Math.pow(b, 2) + (-4 * (a * c));

        double underDivide = 2*a;

        double sqrt = Math.sqrt(underSqrt);

        double aboveDivide = (b*-1) + sqrt;

        return aboveDivide / underDivide;
    }

    public static boolean isTriangle(double number)
    {
        double ans = getTriangleOrigin(number);

        if (Double.isNaN(ans))
        {
            return false;
        }

        if (ans != Math.floor(ans))
        {
            return false;
        }

        return true;
    }

    //Square
    public static double getSquareNumber(double number)
    {
        return 0.5 * number * (number + 1);
    }

    public static double getSquareOrigin(double number)
    {
        double root = Math.sqrt(number);
//        double a = 1;
//        double b = 0;
//        double c = 0;
//
//        double underSqrt = Math.pow(b, 2) + (-4 * (a * c));
//
//        double underDivide = 2*a;
//
//        double sqrt = Math.sqrt(underSqrt);
//
//        double aboveDivide = (b*-1) + sqrt;

        return root;
    }

    public static boolean isSquare(double number)
    {
        double ans = getSquareOrigin(number);

        if (Double.isNaN(ans))
        {
            return false;
        }

        if (ans != Math.floor(ans))
        {
            return false;
        }

        return true;
    }

    //Pentagonal
    public static double getPentagonal(double number)
    {
        return getPentagonal(0, number);
    }

    public static double getPentagonal(double beginPoint, double jumps)
    {
        double endPoint = beginPoint+jumps;

        double begin = ((3*(beginPoint*beginPoint)) - beginPoint) / 2;

        double sec1 = (4 * (endPoint - beginPoint));

        double sec2 = (getTriangleNumber(endPoint-2) - getTriangleNumber(beginPoint-2)) * 3;

        return begin + sec1 + sec2;
    }

    public static double getPentagonalOrigin(double number)
    {
        double a = 1.5*10;
        double b = -0.5*10;
        double c = 0-(number*10);

        double underSqrt = Math.pow(b, 2) + (-4 * (a * c));

        double underDivide = 2*a;

        double sqrt = Math.sqrt(underSqrt);

        double aboveDivide = (b*-1) + sqrt;

        return aboveDivide / underDivide;
    }

    public static boolean isPentagonal(double number)
    {
        double ans = getPentagonalOrigin(number);

        if (Double.isNaN(ans))
        {
            return false;
        }

        if (ans != Math.floor(ans))
        {
            return false;
        }

        return true;
    }

    //Hexagonal
    public static double getHexagonalNumber(double number)
    {
        return number * ((2 * number) - 1);
    }

    public static double getHexagonalOrigin(int number)
    {
        double a = 2;
        double b = -1;
        double c = 0-(number);

        double underSqrt = Math.pow(b, 2) + (-4 * (a * c));

        double underDivide = 2*a;

        double sqrt = Math.sqrt(underSqrt);

        double aboveDivide = (b*-1) + sqrt;

        return aboveDivide / underDivide;
    }

    public static boolean isHexagonal(int number)
    {
        double ans = getHexagonalOrigin(number);

        if (Double.isNaN(ans))
        {
            return false;
        }

        if (ans != Math.floor(ans))
        {
            return false;
        }

        return true;
    }

    //Heptagonal
    public static double getHeptagonalNumber(double number)
    {
        return (number * ((5 * number) - 3)) / 2;
    }

    public static double getHeptagonalOrigin(int number)
    {
        double a = 2.5*10;
        double b = -1.5*10;
        double c = 0-(number*10);

        double underSqrt = Math.pow(b, 2) + (-4 * (a * c));

        double underDivide = 2*a;

        double sqrt = Math.sqrt(underSqrt);

        double aboveDivide = (b*-1) + sqrt;

        return aboveDivide / underDivide;
    }

    public static boolean isHeptagonal(int number)
    {
        double ans = getHeptagonalOrigin(number);

        if (Double.isNaN(ans))
        {
            return false;
        }

        if (ans != Math.floor(ans))
        {
            return false;
        }

        return true;
    }

    //Octagonal
    public static double getOctagonalNumber(double number)
    {
        return number * ((3 * number) - 2);
    }

    public static double getOctagonalOrigin(int number)
    {
        double a = 3;
        double b = -2;
        double c = 0-(number);

        double underSqrt = Math.pow(b, 2) + (-4 * (a * c));

        double underDivide = 2*a;

        double sqrt = Math.sqrt(underSqrt);

        double aboveDivide = (b*-1) + sqrt;

        return aboveDivide / underDivide;
    }

    public static boolean isOctagonal(int number)
    {
        double ans = getOctagonalOrigin(number);

        if (Double.isNaN(ans))
        {
            return false;
        }

        if (ans != Math.floor(ans))
        {
            return false;
        }

        return true;
    }
}
