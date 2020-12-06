package day5.part1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private final static int ROWS_IN_PLANE = 128;
    private final static int COLUMNS_IN_PLANE = 8;

    public static void main(String[] args) throws IOException {
        FileInputStream fileReader = new FileInputStream("src\\day5\\part1\\files\\input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileReader, StandardCharsets.UTF_8));
        List<String> listOfRowsOfFile = new ArrayList<>();
        List<Integer> listOfSeatIds = new ArrayList<>();
        String rowOfFile;
        int highestSeatId = 0;

        while ((rowOfFile = br.readLine()) != null) {
            listOfRowsOfFile.add(rowOfFile);
        }

        for (String elementOfList : listOfRowsOfFile) {
            int seatId = decodeSeatId(elementOfList);
            listOfSeatIds.add(seatId);
        }

        for (int seat : listOfSeatIds) {
            if (seat > highestSeatId) {
                highestSeatId = seat;
            }
        }

        System.out.println("The highest seat ID is: " + highestSeatId);
    }

    private static int decodeSeatId(String elementOfList) {
        String partRows = elementOfList.substring(0, 7);
        String partColumns = elementOfList.substring(7, 10);
        int row = decode(partRows, 'F', 'B');
        int column = decode(partColumns, 'L', 'R');

        return getSeatId(row, column);
    }

    private static int decode(String part, char lowerHalfChar, char upperHalfChar) {
        int lowerHalf = 0;
        int upperHalf = 0;
        int result = 0;

        if (lowerHalfChar == 'F') {
            upperHalf = ROWS_IN_PLANE - 1;
        } else if (lowerHalfChar == 'L') {
            upperHalf = COLUMNS_IN_PLANE - 1;
        }

        for (int i = 0; i < part.length(); i++) {

            if (part.charAt(i) == lowerHalfChar) {
                upperHalf = (lowerHalf + upperHalf) / 2;
                result = lowerHalf;
            } else if (part.charAt(i) == upperHalfChar) {
                lowerHalf = (lowerHalf + upperHalf) / 2 + 1;
                result = upperHalf;
            }
        }

        return result;
    }

    private static int getSeatId(int row, int column) {
        return row * 8 + column;
    }
}
