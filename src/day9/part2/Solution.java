package day9.part2;

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
        List<String> listOfRows = getListFromFile("src\\day9\\part2\\files\\input.txt");

        long result = getFirstNumberThatIsNotSum(listOfRows);

        long weaknessNumber = getWeaknessNumber(listOfRows, result);

        System.out.println("Encryption weakness is: " + weaknessNumber);
    }

    private static long getWeaknessNumber(List<String> listOfRows, long result) {
        int index = getIndexOfResultInList(listOfRows, result);
        long weaknessNumber = 0;
        boolean foundSum = false;

        for (int i = 0; i < index - 2; i++) {
            for (int j = i + 1; j < index; j++) {
                long sum = 0;
                long smallest = Long.MAX_VALUE;
                long largest = Long.MIN_VALUE;
                for (int k = i; k <= j; k++) {
                    long value = Long.parseLong(listOfRows.get(k));
                    sum += value;

                    if (value < smallest)
                        smallest = value;
                    else if (value > largest)
                        largest = value;
                }
                if (sum == result) {
                    foundSum = true;
                    weaknessNumber = smallest + largest;
                    break;
                }
            }
            if (foundSum) break;
        }
        return weaknessNumber;
    }

    private static int getIndexOfResultInList(List<String> listOfRows, long result) {
        int index = 0;
        for (int i = 0; i < listOfRows.size(); i++) {
            if (result == Long.parseLong(listOfRows.get(i))) {
                index = i;
                break;
            }
        }
        return index;
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

    private static long getFirstNumberThatIsNotSum(List<String> listOfRows) {
        long result = 0;
        for (int i = 25; i < listOfRows.size(); i++) {
            long currentValue = Long.parseLong(listOfRows.get(i));
            boolean isValid = false;
            boolean isNotSum = false;
            for (int j = i - 25; j < i; j++) {
                for (int k = i - 25; k < i; k++) {
                    if (k == j) continue;
                    long sum = Long.parseLong(listOfRows.get(j)) + Long.parseLong(listOfRows.get(k));
                    if (sum == currentValue) {
                        isValid = true;
                        isNotSum = false;
                        break;
                    } else {
                        isNotSum = true;
                    }
                }
                if (isValid) {
                    break;
                }

            }
            if (isNotSum) {
                result = currentValue;
                break;
            }
        }
        return result;
    }
}

