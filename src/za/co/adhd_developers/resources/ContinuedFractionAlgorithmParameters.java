package za.co.adhd_developers.resources;

public class ContinuedFractionAlgorithmParameters
{
    int m = 0;
    int d = 0;
    int a = 0;

    public ContinuedFractionAlgorithmParameters(int S, int pM, int pD, int pA)
    {
        int a0 = (int) Math.floor(Math.sqrt(S));

        m = pD * pA - pM;
        d = (int) Math.floor((S - (int) Math.pow(m, 2)) / pD);
        a = Math.floorDiv(a0 + m, d);
    }

    public int getM() {
        return m;
    }

    public int getD() {
        return d;
    }

    public int getA() {
        return a;
    }
}
