package day4.part2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) throws IOException {
        FileInputStream fileReader = new FileInputStream("src\\day4\\part2\\files\\input.txt");
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
        boolean isPassportValid = false;
        boolean isByrValid = false;
        boolean isIyrValid = false;
        boolean isEyrValid = false;
        boolean isHgtValid = false;
        boolean isHclValid = false;
        boolean isEclValid = false;
        boolean isPidValid = false;
        String key = "";
        String value = "";

        String[] splitedStrings = str.split(" ");
        for (String s : splitedStrings) {
            String[] sSplited = s.trim().split(":");

            if (sSplited.length == 2) {
                key = sSplited[0];
                value = sSplited[1];
            }

            switch (key) {
                case "byr": {
                    if (Integer.parseInt(value) >= 1920 && Integer.parseInt(value) <= 2002) {
                        isByrValid = true;
                    }
                }
                break;
                case "iyr": {
                    if (Integer.parseInt(value) >= 2010 && Integer.parseInt(value) <= 2020) {
                        isIyrValid = true;
                    }
                }
                break;
                case "eyr": {
                    if (Integer.parseInt(value) >= 2020 && Integer.parseInt(value) <= 2030) {
                        isEyrValid = true;
                    }
                }
                break;
                case "hgt": {
                    int heightInCm = getUnitValue(value, "cm");
                    int heightInInch = getUnitValue(value, "in");
                    if ((heightInCm >= 150 && heightInCm <= 193) || (heightInInch >= 59 && heightInInch <= 76)) {
                        isHgtValid = true;
                    }
                }
                break;
                case "hcl": {
                    if (value.matches("#[0-9,a-f]{6}")) {
                        isHclValid = true;
                    }
                }
                break;
                case "ecl": {
                    if ("amb".equals(value) || "blu".equals(value) || "brn".equals(value) || "gry".equals(value) ||
                            "grn".equals(value) || "hzl".equals(value) || "oth".equals(value)) {
                        isEclValid = true;
                    }
                }
                break;
                case "pid": {
                    if (value.matches("[0-9]{9}")) {
                        isPidValid = true;
                    }
                }
                break;
            }
        }

        if (isByrValid && isIyrValid && isEyrValid && isHgtValid && isHclValid && isEclValid && isPidValid) {
            isPassportValid = true;
        }

        return isPassportValid;
    }


    private static int getUnitValue(String value, String unit) {
        int unitValue = 0;
        if (value.contains(unit)) {
            String[] height = value.split(unit);
            unitValue = Integer.parseInt(height[0]);
        }
        return unitValue;
    }
}
