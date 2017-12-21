package za.co.adhd_developers.questions;

import za.co.adhd_developers.tools.PermUtil;
import za.co.adhd_developers.tools.Question;
import za.co.adhd_developers.tools.Utils;

import java.util.ArrayList;

public class Question0 implements Question {
    @Override
    public void doWork()
    {
        ArrayList<String> primeOptions = new ArrayList<>();
        primeOptions.add("3");
        primeOptions.add("7");
        primeOptions.add("109");
        primeOptions.add("673");

        PermUtil permUtil = new PermUtil(Utils.castArrayToList(primeOptions), 2);

        String[] perm = (String[]) permUtil.next();

        while (perm != null)
        {
            String newNumStr = String.join("",perm);

            System.out.println(newNumStr + ": " + Utils.newIsPrime(Long.valueOf(newNumStr)));

            perm = (String[]) permUtil.next();
        }
    }

    @Override
    public void printAnswer() {

    }
}
