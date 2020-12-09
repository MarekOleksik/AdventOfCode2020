package day9.part1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        List<String> listOfRows = getListFromFile("src\\day9\\part1\\files\\input.txt");

        long result = getFirstNumberThatIsNotSum(listOfRows);

        System.out.println("The first number, that is not the sum of two of the 25 numbers before it, is : " + result);
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

