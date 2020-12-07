package day6.part1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream fileReader = new FileInputStream("src\\day6\\part1\\files\\input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileReader, StandardCharsets.UTF_8));
        List<String> listOfRows = new ArrayList<>();
        String row;

        while ((row = br.readLine()) != null) {
            listOfRows.add(row);
        }
        listOfRows.add("");

        List<String> listOfAnswers = new ArrayList<>();
        String answer = "";

        for (int i = 0; i < listOfRows.size(); i++) {
            answer = answer + listOfRows.get(i);

            if (listOfRows.get(i).isEmpty()) {
                listOfAnswers.add(answer);
                answer = "";
            }
        }

        Set<String> singleAnswersSet = new HashSet<>();
        List<Integer> counterOfAnswers = new ArrayList<>();

        for (int i = 0; i < listOfAnswers.size(); i++) {

            String[] singleAnswers = listOfAnswers.get(i).split("");
            for (String temp : singleAnswers) {
                singleAnswersSet.add(temp);
            }
            counterOfAnswers.add(singleAnswersSet.size());
            singleAnswersSet.clear();
        }

        int sumOfCounts = 0;
        for (int count : counterOfAnswers) {
            sumOfCounts += count;
        }

        System.out.println("The sum of counts is: " + sumOfCounts);
    }
}
