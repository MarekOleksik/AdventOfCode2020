package day3.part1;

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
        int numberOfTrees = 0;

        while ((row = br.readLine()) != null) {
            listOfRows.add(row);
        }

        int index = 0;
        int lengthOfSingleRow = listOfRows.get(0).length();

        for (int i = 1; i < listOfRows.size(); i++) {
            index = index % lengthOfSingleRow + 3;
            if (index >= lengthOfSingleRow) {
                index = index - lengthOfSingleRow;
            }

            if (listOfRows.get(i).charAt(index) == '#') {
                numberOfTrees++;
            }
        }

        System.out.println("Trees are: " + numberOfTrees);
    }
}
