package za.co.adhd_developers.resources;

import java.util.Arrays;

/**
 * Created by Grant on 2017/08/01.
 */
public class NameScore
{
    private String name;
    private int[] letterScores;
    private int nameScoreNoPos;

    public NameScore(String name)
    {
        this.name = name;

        int index = 0;
        for (char character : this.name.toCharArray())
        {
            if (this.letterScores == null)
            {
                this.letterScores = new int[this.name.toCharArray().length];
            }

            int letterScore = ((int)character)-64;
            this.letterScores[index] = letterScore;
            index++;

            this.nameScoreNoPos += letterScore;
        }
    }

    public String getName()
    {
        return name;
    }

    public int getNameScoreNoPos()
    {
        return nameScoreNoPos;
    }

    public int calculateScore(int position)
    {
        return this.nameScoreNoPos * position;
    }
}
