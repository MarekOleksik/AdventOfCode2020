package day2.part2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Solution {
    public static void main(String[] args) throws IOException {

        FileInputStream fileReader = new FileInputStream("src\\day2\\part2\\files\\input.txt");
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

            if ((splitedSecondPartOfRow[numberAtLeast].equals(containingLetter) &&
                    !splitedSecondPartOfRow[numberAtLast].equals(containingLetter) ||
                    splitedSecondPartOfRow[numberAtLast].equals(containingLetter) &&
                            !splitedSecondPartOfRow[numberAtLeast].equals(containingLetter))) {

                validPasswords++;
            }
        }
        System.out.println("Number of valid passwords is: " + validPasswords);
    }
}
