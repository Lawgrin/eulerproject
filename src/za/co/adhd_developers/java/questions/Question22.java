package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.resources.NameScore;
import za.co.adhd_developers.java.tools.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Grant on 2017/08/01.
 */
public class Question22 implements Question {

    private ArrayList<NameScore> theNames = new ArrayList<>();

    private long answer = 0;

    @Override
    public void doWork() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/externalResources/Question22_names.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer stringBuffer = new StringBuffer();
            String s = "";
            while ((s = bufferedReader.readLine()) != null) {
                stringBuffer.append(s);
            }

            bufferedReader.close();

            String[] names = stringBuffer.toString().split(",");

            for (String name : names) {
                name = name.replace("\"", "");

                this.theNames.add(new NameScore(name));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.theNames.sort(new Comparator<NameScore>() {
            @Override
            public int compare(NameScore o1, NameScore o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        for (int i = 0; i < this.theNames.size(); i++) {
            this.answer += this.theNames.get(i).calculateScore(i + 1);
        }
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 22");
        System.out.println("The total of all the name scores in the file, is: " + this.answer);
        System.out.println("=================================");

    }
}
