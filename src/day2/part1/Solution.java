package day2.part1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Solution {
    public static void main(String[] args) throws IOException {

        FileInputStream fileReader = new FileInputStream("src\\day2\\part1\\files\\input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileReader, StandardCharsets.UTF_8));
        String row;
        int validPasswords = 0;

        while ((row = br.readLine()) != null) {

            String[] splitedRow = row.split(":");
            String[] splitedFirstPartOfRow = splitedRow[0].split(" ");
            String[] splitedSecondPartOfRow = splitedRow[1].split("");
            String[] numbers = splitedFirstPartOfRow[0].split("-");
            int numberAtLeast = Integer.parseInt(numbers[0]);
            int numberAtLast = Integer.parseInt(numbers[1]);
            String containingLetter = splitedFirstPartOfRow[1];
            int counter = 0;

            for (String s : splitedSecondPartOfRow) {
                if (containingLetter.equals(s)) {
                    counter++;
                }
            }

            if (counter >= numberAtLeast && counter <= numberAtLast) {
                validPasswords++;
            }
        }
        System.out.println("Number of valid passwords is: " + validPasswords);
    }
}
