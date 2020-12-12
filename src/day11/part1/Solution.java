package day11.part1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        List<String> listOfRows = getListFromFile("src\\day11\\part1\\files\\input.txt");

        char[][] seatLayout = getSeatLayoutFromList(listOfRows);

        char[][] finishedLayout = getFinishedLayout(seatLayout);

        int occupiedSeats = getOccupiedSeats(finishedLayout);

        System.out.println("End up occupied are " + occupiedSeats + " seats.");
    }

    private static char[][] getFinishedLayout(char[][] seatLayout) {

        boolean same = false;
        char[][] finishedLayout = seatLayout;
        while (!same) {
            char[][] updatedLayout = getUpdatedLayout(finishedLayout);
            same = true;
            for (int i = 0; i < finishedLayout.length; i++) {
                if (!same)
                    break;
                for (int j = 0; j < finishedLayout[i].length; j++) {
                    if (updatedLayout[i][j] != finishedLayout[i][j]) {
                        same = false;
                        break;
                    }
                }
            }
            finishedLayout = updatedLayout;
        }
        return finishedLayout;
    }


    private static char[][] getUpdatedLayout(char[][] seatLayout) {

        char[][] updated = new char[seatLayout.length][seatLayout[0].length];

        for (int i = 0; i < updated.length; i++) {
            for (int j = 0; j < updated[i].length; j++) {
                char chatAt = seatLayout[i][j];
                switch (chatAt) {
                    case 'L':
                        boolean occupied = true;
                        for (int iOff = -1; iOff < 2; iOff++) {
                            if (!occupied)
                                break;
                            for (int jOff = -1; jOff < 2; jOff++) {
                                if (iOff == 0 && jOff == 0 || i + iOff < 0 || j + jOff < 0 || i + iOff >= updated.length || j + jOff >= updated[i].length)
                                    continue;

                                char chatAtOff = seatLayout[i + iOff][j + jOff];
                                if (chatAtOff == '#') {
                                    occupied = false;
                                    break;
                                }
                            }
                        }

                        updated[i][j] = occupied ? '#' : 'L';
                        break;
                    case '#':
                        int occupiedCount = 0;
                        for (int iOff = -1; iOff < 2; iOff++) {
                            for (int jOff = -1; jOff < 2; jOff++) {
                                if (iOff == 0 && jOff == 0 || i + iOff < 0 || j + jOff < 0 || i + iOff >= updated.length || j + jOff >= updated[i].length)
                                    continue;

                                char chatAtOff = seatLayout[i + iOff][j + jOff];
                                if (chatAtOff == '#') {
                                    occupiedCount++;
                                }
                            }
                        }

                        updated[i][j] = occupiedCount >= 4 ? 'L' : '#';
                        break;
                    default:
                        updated[i][j] = '.';
                        break;
                }
            }
        }
        return updated;
    }


    private static char[][] getSeatLayoutFromList(List<String> listOfRows) {
        char[][] seatLayout = new char[listOfRows.size()][listOfRows.get(0).length()];

        for (int i = 0; i < listOfRows.size(); i++) {
            for (int j = 0; j < listOfRows.get(i).length(); j++) {
                seatLayout[i][j] = listOfRows.get(i).charAt(j);
            }
        }
        return seatLayout;
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

    private static int getOccupiedSeats(char[][] seatsOccupied) {
        int occupiedSeats = 0;
        for (char[] ch : seatsOccupied) {
            for (char ch1 : ch) {
                if (ch1 == '#') {
                    occupiedSeats++;
                }
            }
        }
        return occupiedSeats;
    }
}
