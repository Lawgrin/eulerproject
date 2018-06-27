package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;

public class Question40 implements Question
{
    private int answer = 1;
    @Override
    public void doWork()
    {
        int length = 0;
        int currNumber = 1;

        int lookingFor = 1;

        while (length < lookingFor)
        {
            String strNumber = String.valueOf(currNumber);
            if (length + strNumber.length() >= lookingFor)
            {
                int index = lookingFor - length - 1;
                this.answer = answer*Integer.valueOf(strNumber.substring(index,index+1));
            }
            length+=strNumber.length();
            currNumber++;
        }

        lookingFor = lookingFor*10;//10
        while (length < lookingFor)
        {
            String strNumber = String.valueOf(currNumber);
            if (length + strNumber.length() >= lookingFor)
            {
                int index = lookingFor - length - 1;
                this.answer = answer*Integer.valueOf(strNumber.substring(index,index+1));
            }
            length+=strNumber.length();
            currNumber++;
        }

        lookingFor = lookingFor*10;//100
        while (length < lookingFor)
        {
            String strNumber = String.valueOf(currNumber);
            if (length + strNumber.length() >= lookingFor)
            {
                int index = lookingFor - length - 1;
                this.answer = answer*Integer.valueOf(strNumber.substring(index,index+1));
            }
            length+=strNumber.length();
            currNumber++;
        }

        lookingFor = lookingFor*10;//1000
        while (length < lookingFor)
        {
            String strNumber = String.valueOf(currNumber);
            if (length + strNumber.length() >= lookingFor)
            {
                int index = lookingFor - length - 1;
                this.answer = answer*Integer.valueOf(strNumber.substring(index,index+1));
            }
            length+=strNumber.length();
            currNumber++;
        }

        lookingFor = lookingFor*10;//10000
        while (length < lookingFor)
        {
            String strNumber = String.valueOf(currNumber);
            if (length + strNumber.length() >= lookingFor)
            {
                int index = lookingFor - length - 1;
                this.answer = answer*Integer.valueOf(strNumber.substring(index,index+1));
            }
            length+=strNumber.length();
            currNumber++;
        }

        lookingFor = lookingFor*10;//100000
        while (length < lookingFor)
        {
            String strNumber = String.valueOf(currNumber);
            if (length + strNumber.length() >= lookingFor)
            {
                int index = lookingFor - length - 1;
                this.answer = answer*Integer.valueOf(strNumber.substring(index,index+1));
            }
            length+=strNumber.length();
            currNumber++;
        }

        lookingFor = lookingFor*10;//1000000
        while (length < lookingFor)
        {
            String strNumber = String.valueOf(currNumber);
            if (length + strNumber.length() >= lookingFor)
            {
                int index = lookingFor - length - 1;
                this.answer = answer*Integer.valueOf(strNumber.substring(index,index+1));
            }
            length+=strNumber.length();
            currNumber++;
        }
    }

    @Override
    public void printAnswer()
    {
        System.out.println("=================================");
        System.out.println("Question 40");
        System.out.println("The product of the 1th, 10th, 100th, 1000th, 10000th, 100000th and 1000000th, is: " + this.answer);
        System.out.println("=================================");
    }
}
