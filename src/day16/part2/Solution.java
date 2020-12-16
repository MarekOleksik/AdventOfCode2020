package day16.part2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//TODO
public class Solution {
    public static void main(String[] args) throws IOException {
        String filePath = "src\\day16\\part1\\files\\input.txt";
        List<String> listOfRulesForTicket = getDepartureRulesForTicketFromFile(filePath);
        List<String> listOfNearbyTickets = getNearbyTicketsFromFile(filePath);
        Set<Integer> validNumbers = getValidNumbers(listOfRulesForTicket);
        int result = getResult(listOfNearbyTickets, validNumbers);
        System.out.println("My ticket scanning error rate is: " + result);
    }

    private static int getResult(List<String> listOfNearbyTickets, Set<Integer> validNumbers) {
        List<String> validTickets = new ArrayList<>();

        for (String ticket : listOfNearbyTickets) {
            String[] tickets = ticket.split(",");
            boolean isValidRow = true;
            for (String number : tickets) {
                int temp = Integer.parseInt(number);
                if (!validNumbers.contains(temp)) {
                    isValidRow = false;
                }
            }
            if (isValidRow) {
                validTickets.add(ticket);
            }
        }

        int result = 0;

        return result;
    }

    private static Set<Integer> getValidNumbers(List<String> listOfRulesForTicket) {
        Set<Integer> validNumbers = new HashSet<>();

        for (String rule : listOfRulesForTicket) {
            String[] rules = rule.replace("platform", "")
                    .replace("or", ":")
                    .split(":");

            String[] splitedRules1 = rules[1].trim().split("-");
            int firstNumber = Integer.parseInt(splitedRules1[0]);
            int secondNumber = Integer.parseInt(splitedRules1[1]);
            for (int i = firstNumber; i <= secondNumber; i++) {
                validNumbers.add(i);
            }
            String[] splitedRules2 = rules[2].trim().split("-");
            firstNumber = Integer.parseInt(splitedRules2[0]);
            secondNumber = Integer.parseInt(splitedRules2[1]);
            for (int i = firstNumber; i <= secondNumber; i++) {
                validNumbers.add(i);
            }
        }
        return validNumbers;
    }

    private static List<String> getDepartureRulesForTicketFromFile(String path) throws IOException {
        FileInputStream fileReader = new FileInputStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(fileReader, StandardCharsets.UTF_8));
        List<String> listOfRows = new ArrayList<>();
        String row;

        while ((row = br.readLine()) != null && row.contains("departure")) {
            listOfRows.add(row);
            System.out.println(row);
        }
        return listOfRows;
    }

    private static List<String> getNearbyTicketsFromFile(String path) throws IOException {
        FileInputStream fileReader = new FileInputStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(fileReader, StandardCharsets.UTF_8));
        List<String> listOfRows = new ArrayList<>();
        String row;
        boolean isNearbyTicket = false;
        while ((row = br.readLine()) != null) {
            if (isNearbyTicket) {
                listOfRows.add(row);
            }
            if ("nearby tickets:".equals(row)) {
                isNearbyTicket = true;
            }
        }
        return listOfRows;
    }

}
