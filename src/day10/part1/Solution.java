package day10.part1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        List<String> listOfRows = getListFromFile("src\\day10\\part1\\files\\input.txt");

        int multipliedDifferences = getMultipliedDifferences(listOfRows);

        System.out.println("Number of 1-volt differences multiplied by the number of 3-volt differences is: " + multipliedDifferences);
    }

    private static int getMultipliedDifferences(List<String> listOfRows) {
        List<Integer> numbersFromRows = new ArrayList<>();

        for (String row : listOfRows) {
            numbersFromRows.add(Integer.parseInt(row));
        }

        Collections.sort(numbersFromRows);

        int countDifferencesOneVolt = 1;
        int countDifferencesThreeVolt = 1;

        for (int i = 1; i < numbersFromRows.size(); i++) {
            int value = numbersFromRows.get(i) - numbersFromRows.get(i - 1);
            if (value == 1) {
                countDifferencesOneVolt++;
            } else if (value == 3) {
                countDifferencesThreeVolt++;
            }
        }
        return countDifferencesOneVolt * countDifferencesThreeVolt;
    }

    private static List<String> getListFromFile(String path) throws IOException {
        FileInputStream fileReader = new FileInputStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(fileReader, StandardCharsets.UTF_8));
        List<String> listOfRows = new ArrayList<>();
        String row;

        while ((row = br.readLine()) != null) {
            listOfRows.add(row);
        }
        return listOfRows;
    }
}