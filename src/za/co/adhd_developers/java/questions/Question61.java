package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.PolygonalUtil;
import za.co.adhd_developers.java.tools.Question;

import java.util.ArrayList;
import java.util.Hashtable;

public class Question61 implements Question
{
    private Integer answer = 0;

    @Override
    public void doWork()
    {
        Hashtable<Integer, ArrayList<NumberStatus>> groupedPrimes = new Hashtable<>();

        for (int key = 10; key < 100; key++)
        {
            int number = Integer.valueOf(String.valueOf(key)+"00");

            ArrayList<NumberStatus> primeGroup = new ArrayList<>();

            for (int i = number; i < number+100; i++)
            {
                if (String.valueOf(i).matches("\\d\\d0\\d"))
                {
                    continue;
                }

                NumberStatus numberStatus = new NumberStatus(i);

                if (numberStatus.hasValid())
                {
                    primeGroup.add(numberStatus);
                }
            }

            groupedPrimes.put(key, primeGroup);
        }

        ArrayList<CyclicalNumbers> allNumberStatus = new ArrayList<>();

        groupedPrimes.forEach((integer, numberStatuses) -> numberStatuses.forEach(numberStatus -> allNumberStatus.add(new CyclicalNumbers(numberStatus))));



        final int[] countNotDone = {0};

        allNumberStatus.forEach(cyclicalNumbers -> {
            if (!cyclicalNumbers.isDone())
            {
                countNotDone[0]++;
            }
        });

        ArrayList<CyclicalNumbers> tmp_allNumberStatus = new ArrayList<>();
        while (countNotDone[0] != 0)
        {
            for (CyclicalNumbers cyclicalNumbers : allNumberStatus)
            {
                ArrayList<CyclicalNumbers> tmp = cyclicalNumbers.getNextOptions(groupedPrimes);

                if (tmp == null)
                {
                    continue;
                }
                if (tmp.size() == 0)
                {
                    continue;
                }

                if (tmp.get(0) != cyclicalNumbers)
                {
                    tmp_allNumberStatus.addAll(tmp);
                }
            }

            allNumberStatus.clear();
            allNumberStatus.addAll(tmp_allNumberStatus);
            tmp_allNumberStatus.clear();

            countNotDone[0] = 0;

            allNumberStatus.forEach(cyclicalNumbers -> {
                if (!cyclicalNumbers.isDone())
                {
                    countNotDone[0]++;
                }
            });
        }

        tmp_allNumberStatus.clear();
        allNumberStatus.forEach(cyclicalNumbers -> {
            if (cyclicalNumbers.isDone() && cyclicalNumbers.isValid())
            {
                tmp_allNumberStatus.add(cyclicalNumbers);
            }
        });

        allNumberStatus.clear();
        allNumberStatus.addAll(tmp_allNumberStatus);
        tmp_allNumberStatus.clear();

        this.answer = allNumberStatus.get(0).getSum();
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 61");
        System.out.println("The sum of the only ordered set of six cyclic 4-digit numbers for which each polygonal type: " +
                "\ntriangle, square, pentagonal, hexagonal, heptagonal, and octagonal, is represented by a different number " +
                "\nin the set, is: "+this.answer);
        System.out.println("=================================");
    }

    class NumberStatus
    {
        private int number = 0;

        private boolean tri = false;
        private boolean squa = false;
        private boolean pent = false;
        private boolean hex = false;
        private boolean hep = false;
        private boolean oct = false;

        private double triOrigin = 0;
        private double squaOrigin = 0;
        private double pentOrigin = 0;
        private double hexOrigin = 0;
        private double hepOrigin = 0;
        private double octOrigin = 0;

        public NumberStatus(int number)
        {
            this.number = number;

            if (PolygonalUtil.isTriangle(this.number))
            {
                this.tri = true;
                this.triOrigin = PolygonalUtil.getTriangleOrigin(this.number);
            }
            if (PolygonalUtil.isSquare(this.number))
            {
                this.squa= true;
                this.squaOrigin = PolygonalUtil.getSquareOrigin(this.number);
            }
            if (PolygonalUtil.isPentagonal(this.number))
            {
                this.pent = true;
                this.pentOrigin = PolygonalUtil.getPentagonalOrigin(this.number);
            }
            if (PolygonalUtil.isHexagonal(this.number))
            {
                this.hex = true;
                this.hexOrigin = PolygonalUtil.getHexagonalOrigin(this.number);
            }
            if (PolygonalUtil.isHeptagonal(this.number))
            {
                this.hep = true;
                this.hepOrigin = PolygonalUtil.getHeptagonalOrigin(this.number);
            }
            if (PolygonalUtil.isOctagonal(this.number))
            {
                this.oct = true;
                this.octOrigin = PolygonalUtil.getOctagonalOrigin(this.number);
            }
        }

        public int getNumber()
        {
            return number;
        }

        public boolean isTri() {
            return tri;
        }

        public boolean isSqua() {
            return squa;
        }

        public boolean isPent() {
            return pent;
        }

        public boolean isHex() {
            return hex;
        }

        public boolean isHep() {
            return hep;
        }

        public boolean isOct() {
            return oct;
        }

        public double getTriOrigin() {
            return triOrigin;
        }

        public double getSquaOrigin() {
            return squaOrigin;
        }

        public double getPentOrigin() {
            return pentOrigin;
        }

        public double getHexOrigin() {
            return hexOrigin;
        }

        public double getHepOrigin() {
            return hepOrigin;
        }

        public double getOctOrigin() {
            return octOrigin;
        }

        public String getFirstTwo()
        {
            String numberStr = String.valueOf(this.number);

            return numberStr.substring(0,2);
        }

        public String getLastTwo()
        {
            String numberStr = String.valueOf(this.number);

            return numberStr.substring(2);
        }

        public boolean startwithLastof(NumberStatus numberStatus)
        {
            String numberStr = String.valueOf(this.number);

            if (numberStr.startsWith(numberStatus.getLastTwo()))
            {
                return true;
            }

            return false;
        }

        public boolean compareTypesAndOrigins(NumberStatus numberStatus)
        {
            boolean ret = false;

            if (this.isTri() != numberStatus.isTri())
            {
                ret = true;
            }
            else if (this.isSqua() != numberStatus.isSqua())
            {
                ret = true;
            }
            else if (this.isPent() != numberStatus.isPent())
            {
                ret = true;
            }
            else if (this.isHex() != numberStatus.isHex())
            {
                ret = true;
            }
            else if (this.isHep() != numberStatus.isHep())
            {
                ret = true;
            }
            else if (this.isOct() != numberStatus.isOct())
            {
                ret = true;
            }

            if (this.getTriOrigin() != 0 && numberStatus.getTriOrigin() != 0 &&
                    this.getTriOrigin() == numberStatus.getTriOrigin())
            {
                ret = false;
            }
            else if (this.getSquaOrigin() != 0 && numberStatus.getPentOrigin() != 0 &&
                    this.getSquaOrigin() == numberStatus.getSquaOrigin())
            {
                ret = false;
            }
            else if (this.getPentOrigin() != 0 && numberStatus.getPentOrigin() != 0 &&
                    this.getPentOrigin() == numberStatus.getPentOrigin())
            {
                ret = false;
            }
            else if (this.getHexOrigin() != 0 && numberStatus.getHexOrigin() != 0 &&
                    this.getHexOrigin() == numberStatus.getHexOrigin())
            {
                ret = false;
            }
            else if (this.getHepOrigin() != 0 && numberStatus.getHepOrigin() != 0 &&
                    this.getHepOrigin() == numberStatus.getHepOrigin())
            {
                ret = false;
            }
            else if (this.getOctOrigin() != 0 && numberStatus.getOctOrigin() != 0 &&
                    this.getOctOrigin() == numberStatus.getOctOrigin())
            {
                ret = false;
            }

            return ret;
        }

        private boolean hasValid()
        {
            if (this.tri || this.squa || this.pent || this.oct || this.hex || this.hep || this.oct)
            {
                return true;
            }

            return false;
        }

        @Override
        public String toString()
        {
            String str = "";

            str += String.valueOf(this.number)+": ";

            if (this.tri)
            {
                str += "\n\tis Tri: "+this.triOrigin;
            }
            if (this.squa)
            {
                str += "\n\tis Squa: "+this.squaOrigin;
            }
            if (this.pent)
            {
                str += "\n\tis Pent: "+this.pentOrigin;
            }
            if (this.hex)
            {
                str += "\n\tis Hex: "+this.hexOrigin;
            }
            if (this.hep)
            {
                str += "\n\tis Hep: "+this.hepOrigin;
            }
            if (this.oct)
            {
                str += "\n\tis Oct: "+this.octOrigin;
            }

            return str;
        }
    }

    class CyclicalNumbers
    {
        private NumberStatus number1 = null;
        private NumberStatus number2 = null;
        private NumberStatus number3 = null;
        private NumberStatus number4 = null;
        private NumberStatus number5 = null;
        private NumberStatus number6 = null;

        public CyclicalNumbers(CyclicalNumbers cyclicalNumbers)
        {
            this.number1 = cyclicalNumbers.getNumber1();
            this.number2 = cyclicalNumbers.getNumber2();
            this.number3 = cyclicalNumbers.getNumber3();
            this.number4 = cyclicalNumbers.getNumber4();
            this.number5 = cyclicalNumbers.getNumber5();
            this.number6 = cyclicalNumbers.getNumber6();
        }

        public CyclicalNumbers(NumberStatus startNumber)
        {
            this.number1 = startNumber;
        }

        public NumberStatus getNumber1() {
            return number1;
        }

        public NumberStatus getNumber2() {
            return number2;
        }

        public NumberStatus getNumber3() {
            return number3;
        }

        public NumberStatus getNumber4() {
            return number4;
        }

        public NumberStatus getNumber5() {
            return number5;
        }

        public NumberStatus getNumber6() {
            return number6;
        }

        public boolean isDone()
        {
            if (this.number1 != null && this.number2 != null &&
                    this.number3 != null && this.number4 != null &&
                    this.number5 != null && this.number6 != null)
            {
                return true;
            }

            return false;
        }

        public boolean isValid()
        {
            if (this.number1.startwithLastof(this.number6))
            {
                return true;
            }

            return false;
        }

        public Integer getSum()
        {
            return this.number1.getNumber() + this.number2.getNumber() + this.number3.getNumber() + this.number4.getNumber() + this.number5.getNumber() + this.number6.getNumber();
        }

        public boolean canAdd(NumberStatus numberStatus)
        {
            if (this.number1 != null)
            {
                if (!this.number1.compareTypesAndOrigins(numberStatus))
                {
                    return false;
                }
            }
            if (this.number2 != null)
            {
                if (!this.number2.compareTypesAndOrigins(numberStatus))
                {
                    return false;
                }
            }
            if (this.number3 != null)
            {
                if (!this.number3.compareTypesAndOrigins(numberStatus))
                {
                    return false;
                }
            }
            if (this.number4 != null)
            {
                if (!this.number4.compareTypesAndOrigins(numberStatus))
                {
                    return false;
                }
            }
            if (this.number5 != null)
            {
                if (!this.number5.compareTypesAndOrigins(numberStatus))
                {
                    return false;
                }
            }

            return true;
        }

        public void setNextNumber(NumberStatus nextNumber)
        {
            if (this.number2 == null)
            {
                this.number2 = nextNumber;
            }
            else if (this.number3 == null)
            {
                this.number3 = nextNumber;
            }
            else if (this.number4 == null)
            {
                this.number4 = nextNumber;
            }
            else if (this.number5 == null)
            {
                this.number5 = nextNumber;
            }
            else if (this.number6 == null)
            {
                this.number6 = nextNumber;
            }
        }

        public ArrayList<CyclicalNumbers> getNextOptions(Hashtable<Integer, ArrayList<NumberStatus>> numbers)
        {
            ArrayList<CyclicalNumbers> returningArray = new ArrayList<>();

            Integer key = 0;

            if (number5 != null)
            {
                key = Integer.valueOf(this.number5.getLastTwo());
            }
            else if (number4 != null)
            {
                key = Integer.valueOf(this.number4.getLastTwo());
            }
            else if (number3 != null)
            {
                key = Integer.valueOf(this.number3.getLastTwo());
            }
            else if (number2 != null)
            {
                key = Integer.valueOf(this.number2.getLastTwo());
            }
            else if (number1 != null)
            {
                key = Integer.valueOf(this.number1.getLastTwo());
            }

            if (key == 0)
            {
                returningArray.add(this);
                return returningArray;
            }

            numbers.get(key).forEach(numberStatus -> {
                if (this.canAdd(numberStatus))
                {
                    CyclicalNumbers tmp_CyclicalNumbers = new CyclicalNumbers(this);
                    tmp_CyclicalNumbers.setNextNumber(numberStatus);

                    returningArray.add(tmp_CyclicalNumbers);
                }
            });

            return returningArray;
        }
    }
}
