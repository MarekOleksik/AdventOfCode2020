package day8.part1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream fileReader = new FileInputStream("src\\day8\\part1\\files\\input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileReader, StandardCharsets.UTF_8));
        List<String> listOfRows = new ArrayList<>();
        String row;
        int accumulator = 0;
        boolean programRun = true;
        int index = 0;
        List<Integer> visitedIndexes = new ArrayList<>();

        while ((row = br.readLine()) != null) {
            listOfRows.add(row);
        }

        while (programRun) {
            String[] splitedRow = listOfRows.get(index).split(" ");
            visitedIndexes.add(index);

            switch (splitedRow[0]) {
                case "acc":
                    accumulator += Integer.parseInt(splitedRow[1]);
                    index++;
                    break;
                case "jmp":
                    index += Integer.parseInt(splitedRow[1]);
                    break;
                case "nop":
                    index++;
                    break;
            }

            if (visitedIndexes.contains(index) || index >= listOfRows.size()) {
                programRun = false;
            }
        }

        System.out.println("Value in the accumulator is: " + accumulator);
    }
}
