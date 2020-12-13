package day13.part1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        List<String> listOfRows = getListFromFile("src\\day13\\part1\\files\\input.txt");

        int earliestBusMultipliedByMinutes = getEarliestBusMultipliedByMinutes(listOfRows);

        System.out.println("The ID of the earliest bus I can take to the airport multiplied by the number of minutes " +
                "I'll need to wait for that bus is: " + earliestBusMultipliedByMinutes);
    }

    private static int getEarliestBusMultipliedByMinutes(List<String> listOfRows) {

        double timestamp = Double.parseDouble(listOfRows.get(0));
        String[] busesInService = listOfRows.get(1).split(",");
        List<Double> busIds = new ArrayList<>();

        for (String bus : busesInService) {
            if (!"x".equals(bus)) {
                busIds.add(Double.valueOf(bus));
            }
        }

        int minimumMinutes = Integer.MAX_VALUE;
        int searchingBusId = 0;

        for (double id : busIds) {
            double result = Math.ceil(timestamp / id);
            int resultMulipliedId = (int) (result * id);
            int temp = (int) (resultMulipliedId - timestamp);

            if (temp < minimumMinutes) {
                minimumMinutes = temp;
                searchingBusId = (int) id;
            }
        }
        return minimumMinutes * searchingBusId;
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
}
