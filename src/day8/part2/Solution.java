package day8.part2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static int accumulator = 0;
    private static boolean infiniteLoop;

    public static void main(String[] args) throws IOException {
        FileInputStream fileReader = new FileInputStream("src\\day8\\part2\\files\\input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileReader, StandardCharsets.UTF_8));
        List<String> listOfRows = new ArrayList<>();
        String row;

        while ((row = br.readLine()) != null) {
            listOfRows.add(row);
        }

        String presentValue = "";
        int index = 0;
        boolean programRun = true;

        do {
            boolean found = false;
            while (!found && index < listOfRows.size()) {
                String[] splitedRow = listOfRows.get(index).split(" ");

                switch (splitedRow[0]) {
                    case "nop": {
                        found = true;
                        presentValue = listOfRows.get(index);
                        listOfRows.set(index, presentValue.replace("nop", "jmp"));
                    }
                    break;
                    case "jmp": {
                        found = true;
                        presentValue = listOfRows.get(index);
                        listOfRows.set(index, presentValue.replace("jmp", "nop"));
                    }
                    break;
                    default:
                        index++;
                }
            }

            accumulator = getAccumulator(listOfRows);
            if (!infiniteLoop) {
                programRun = false;
            } else {
                listOfRows.set(index, presentValue);
            }
            index++;
        } while (programRun && index < listOfRows.size());

        System.out.println("Value in the accumulator is: " + accumulator);
    }

    private static int getAccumulator(List<String> listOfRows) {
        int acc = 0;
        boolean programRun = true;
        int index = 0;
        List<Integer> visitedIndexes = new ArrayList<>();

        while (programRun) {
            String[] splitedRow1 = listOfRows.get(index).split(" ");
            visitedIndexes.add(index);

            switch (splitedRow1[0]) {
                case "acc":
                    acc += Integer.parseInt(splitedRow1[1]);
                    index++;
                    break;
                case "jmp":
                    index += Integer.parseInt(splitedRow1[1]);
                    break;
                case "nop":
                    index++;
                    break;
            }

            if (visitedIndexes.contains(index)) {
                programRun = false;
                infiniteLoop = true;
            } else if (index >= listOfRows.size()) {
                infiniteLoop = false;
                programRun = false;
            }
        }
        return acc;
    }


}
