package za.co.adhd_developers.java.questions;

import za.co.adhd_developers.java.tools.Question;
import za.co.adhd_developers.java.resources.TreeBranch;

import java.util.ArrayList;

/**
 * Created by Grant on 2017/07/31.
 */
public class Question18 implements Question {

    long answer = 0;
    private int[][] theTree = {{75},
            {95, 64},
            {17, 47, 82},
            {18, 35, 87, 10},
            {20, 4, 82, 47, 65},
            {19, 1, 23, 75, 3, 34},
            {88, 2, 77, 73, 7, 63, 67},
            {99, 65, 4, 28, 6, 16, 70, 92},
            {41, 41, 26, 56, 83, 40, 80, 70, 33},
            {41, 48, 72, 33, 47, 32, 37, 16, 94, 29},
            {53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14},
            {70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57},
            {91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48},
            {63, 66, 4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31},
            {4, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 4, 23}};

    private TreeBranch getSubBranches(int row, int col) {
        TreeBranch treeBranch = new TreeBranch();
        treeBranch.myNumber = (long) this.theTree[row][col];

        if (row + 1 < this.theTree.length) {
            treeBranch.left = getSubBranches(row + 1, col);
            treeBranch.right = getSubBranches(row + 1, col + 1);
        }

        return treeBranch;
    }

    @Override
    public void doWork() {
        TreeBranch root = getSubBranches(0, 0);

        this.answer = root.getLargestChain();
    }

    @Override
    public void printAnswer() {
        System.out.println("=================================");
        System.out.println("Question 18");
        System.out.println("Maximum total from top to bottom, is: " + this.answer);
        System.out.println("=================================");
    }
}
