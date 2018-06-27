package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;

import java.util.ArrayList;

public class Question65 implements Question {
    @Override
    public void doWork() {

        ArrayList<Integer> convergentsOfE = new ArrayList<>();
        convergentsOfE.add(2);
        convergentsOfE.add(1);
        convergentsOfE.add(2);

        int count = 0;

        for (int i = 3; i < 4; i++) {
            if (count != 2) {
                convergentsOfE.add(1);
                count++;
            } else {
                count = 0;
                convergentsOfE.add(convergentsOfE.get(i - 3) + 2);
            }
        }

        for (int i = 0; i < convergentsOfE.size(); i++) {
            for (int j = i; j > 0; j--) {
                
            }
        }
    }

    @Override
    public void printAnswer() {

    }
}
