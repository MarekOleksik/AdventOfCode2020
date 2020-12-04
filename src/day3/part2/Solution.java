package day3.part2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) throws IOException {

        FileInputStream fileReader = new FileInputStream("src\\day3\\part1\\files\\input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileReader, StandardCharsets.UTF_8));
        List<String> listOfRows = new ArrayList<>();
        String row;

        while ((row = br.readLine()) != null) {
            listOfRows.add(row);
        }

        long slopeOneOne = getNumberOfTrees(listOfRows, 1, 1);
        long slopeThreeOne = getNumberOfTrees(listOfRows, 3, 1);
        long slopeFiveOne = getNumberOfTrees(listOfRows, 5, 1);
        long slopeSevenOne = getNumberOfTrees(listOfRows, 7, 1);
        long slopeOneTwo = getNumberOfTrees(listOfRows, 1, 2);

        long numberOfTrees = slopeOneOne * slopeThreeOne * slopeFiveOne * slopeSevenOne * slopeOneTwo;

        System.out.println("Trees are: " + numberOfTrees);
    }

    private static int getNumberOfTrees(List<String> listOfRows, int slopeRight, int slopeDown) {
        int lengthOfSingleRow = listOfRows.get(0).length();
        int index = 0;
        int numberOfTrees = 0;

        for (int i = slopeDown; i < listOfRows.size(); i += slopeDown) {

            index = index % lengthOfSingleRow + slopeRight;
            if (index >= lengthOfSingleRow) {
                index = index - lengthOfSingleRow;
            }

            if (listOfRows.get(i).charAt(index) == '#') {
                numberOfTrees++;
            }
        }
        return numberOfTrees;
    }
}
