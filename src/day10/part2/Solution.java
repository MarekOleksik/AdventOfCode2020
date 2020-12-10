package day10.part2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        List<String> listOfRows = getListFromFile("src\\day10\\part2\\files\\input.txt");

        int totalNumber = getTotalNumber(listOfRows);

        System.out.println("Number of 1-volt differences multiplied by the number of 3-volt differences is: " + totalNumber);

    }

    private static int getTotalNumber(List<String> listOfRows) {
        List<Integer> numbersFromRows = new ArrayList<>();

        for (String row : listOfRows) {
            numbersFromRows.add(Integer.parseInt(row));
        }

        Collections.sort(numbersFromRows);

        Set<Integer> exceptions = new HashSet<>();

        for (int i = 2; i < numbersFromRows.size(); i++) {
            int value1 = numbersFromRows.get(i) - numbersFromRows.get(i - 2);
            if (value1 == 2) {
                exceptions.add(numbersFromRows.get(i - 1));
                exceptions.add(numbersFromRows.get(i));
            }
        }

        numbersFromRows.forEach(System.out::print);
        System.out.println();
        exceptions.forEach(System.out::print);
        System.out.println();

        int n = numbersFromRows.size();
        int possibleCombinations = 0;
        return 0;
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
