package day4.part1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) throws IOException {
        FileInputStream fileReader = new FileInputStream("src\\day4\\part1\\files\\input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileReader, StandardCharsets.UTF_8));
        List<String> listOfRows = new ArrayList<>();
        String row;
        int validPassports = 0;

        while ((row = br.readLine()) != null) {
            listOfRows.add(row);
        }
        listOfRows.add("");

        List<String> listOfPassports = new ArrayList<>();
        String passport = "";

        for (int i = 0; i < listOfRows.size(); i++) {
            passport = passport + " " + listOfRows.get(i);

            if (listOfRows.get(i).isEmpty()) {
                listOfPassports.add(passport);
                passport = " ";
            }
        }

        for (String str : listOfPassports) {
            if (isValidPassport(str)) {
                validPassports++;
            }
        }

        System.out.println("Valid passports: " + validPassports);
    }

    private static boolean isValidPassport(String str) {
        return str.contains("byr") && str.contains("iyr") && str.contains("eyr") && str.contains("hgt") &&
                str.contains("hcl") && str.contains("ecl") && str.contains("pid");
    }
}
