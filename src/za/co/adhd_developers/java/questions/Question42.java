package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.tools.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Question42 implements Question {

    private ArrayList<String> theWords = new ArrayList<>();
    private int answer = 0;

    @Override
    public void doWork() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/externalResources/Question42_words.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer stringBuffer = new StringBuffer();
            String s = "";
            while ((s = bufferedReader.readLine()) != null) {
                stringBuffer.append(s);
            }

            bufferedReader.close();

            String[] words = stringBuffer.toString().split(",");

            for (String word : words) {
                word = word.replace("\"", "");

                if (Utils.isTriangleWord(word)) {
                    this.theWords.add(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.answer = this.theWords.size();
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 42");
        System.out.println("The total amount of triangle words in the given file, is: " + this.answer);
        System.out.println("=================================");
    }
}
