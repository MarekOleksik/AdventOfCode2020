package day6.part2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream fileReader = new FileInputStream("src\\day6\\part2\\files\\input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileReader, StandardCharsets.UTF_8));
        List<String> listOfRows = new ArrayList<>();
        String row;

        while ((row = br.readLine()) != null) {
            listOfRows.add(row);
        }

        int sumOfCounts = 0;
        List<Character> answersYes = new ArrayList<>();
        boolean first = true;

        for (String s : listOfRows) {
            if (s.trim().isEmpty()) {
                sumOfCounts += answersYes.size();
                answersYes.clear();
                first = true;
            } else {
                if (first) {
                    for (char c : s.trim().toCharArray())
                        answersYes.add(c);
                } else {
                    for (int i = answersYes.size() - 1; i >= 0; i--) {
                        char c = answersYes.get(i);
                        boolean contains = false;
                        for (char c2 : s.trim().toCharArray()) {
                            if (c2 == c) {
                                contains = true;
                                break;
                            }
                        }

                        if (!contains)
                            answersYes.remove(i);
                    }
                }

                first = false;
            }
        }

        sumOfCounts += answersYes.size();

        System.out.println("The sum of counts is: " + sumOfCounts);
    }
}

