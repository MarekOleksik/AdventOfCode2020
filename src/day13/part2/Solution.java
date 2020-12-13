package day13.part2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        List<String> listOfRows = getListFromFile("src\\day13\\part2\\files\\input.txt");

       // List<String> listOfRows = new ArrayList<>();
        //listOfRows.add("1213");
        //listOfRows.add("1789,37,47,1889");


        long sequencedTimestamp = getSequencedTimestamp(listOfRows);

        System.out.println("The earliest timestamp such that all of the listed bus IDs depart at offsets matching their " +
                "positions in the list is: " + sequencedTimestamp);
    }


    private static long getSequencedTimestamp(List<String> listOfRows) {
        String[] busesInService = listOfRows.get(1).split(",");
        List<Integer> busIds = new LinkedList<>();
        int timestampOffset = 0;
        List<Integer> timestampOffsets = new LinkedList<>();
        busIds.add(Integer.valueOf(busesInService[0]));
        timestampOffsets.add(timestampOffset);

        for (int i = 1; i < busesInService.length; i++) {
            String bus = busesInService[i];
            if (!"x".equals(bus)) {
                int value = Integer.valueOf(bus);
                busIds.add(value);
                if (i > value) {
                    timestampOffset = i % value;
                } else {
                    timestampOffset = i;
                }

                timestampOffsets.add(timestampOffset);
                //System.out.print(bus + " ");
                //System.out.println();
                //System.out.print(timestampOffset + " ");
            }
        }

        long timestamp = 99_999_999_999_991L;
        //long timestamp = 0;
        boolean isPartSequenced = false;
        int offset = busIds.get(0);

        for (long i = timestamp; i < Long.MAX_VALUE; i += offset) {
            System.out.println("i: " + i);

            for (int j = 1; j < busIds.size(); j++) {
                //System.out.println("j: " + j ) ;
                // System.out.println(i + "%" + busIds.get(j) + "=" + (i % busIds.get(j)));
                // System.out.println("timestamp: " + (busIds.get(j) - timestampOffsets.get(j)));

                if ((i + timestampOffsets.get(j)) % busIds.get(j) == 0) {
                    isPartSequenced = true;

                    //System.out.println("true: " + timestamp);

                } else {
                    isPartSequenced = false;
                    //System.out.println("false: " + timestamp);
                    //System.out.println(i + "%" + busIds.get(j) + "=" + (i % busIds.get(j)));
                    //System.out.println("timestamp: " + (busIds.get(j) - timestampOffsets.get(j)));
                    //System.out.println("false");
                    break;
                }
            }
            timestamp = i;
            if (isPartSequenced) break;
        }

        return timestamp;
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
