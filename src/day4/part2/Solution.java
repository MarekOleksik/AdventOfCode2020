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
        boolean isValid = false;
        boolean isByr = false;
        boolean isIyr = false;
        boolean isEyr = false;
        boolean isHgt = false;
        boolean isHcl = false;
        boolean isEcl = false;
        boolean isPid = false;

        String[] splitedStrings = str.split(" ");
        for (String s : splitedStrings) {
            String[] sSplited = s.split(":");
            String key = sSplited[0];
            String value = sSplited[1];
            for (int i = 0; i < sSplited.length; i++) {
                switch (key) {
                    case "byr": {
                        if (Integer.parseInt(value) >= 1920 && Integer.parseInt(value) <= 2002) {
                            isByr = true;
                        }
                    }
                        break;
                    case "iyr": {
                        if (Integer.parseInt(value) >=2010 && Integer.parseInt(value) <= 2020) {
                            isIyr = true;
                        }
                    }
                        break;
                    case "eyr": if (Integer.parseInt(value) >=2020 && Integer.parseInt(value) <= 2030) {
                        isIyr = true;
                    }
                        break;
                    case "hgt": isHgt = true;
                        break;
                    case "hcl": isHcl = true;
                        break;
                    case "ecl": isEcl = true;
                        break;
                    case "pid": isPid = true;
                        break;
                }
            }
        }
        if (isByr && isIyr && isEyr && isHgt && isHcl && isEcl && isPid) {
            isValid = true;
        }
        return isValid;
    }
}
