package day14.part1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        List<String> listOfRows = getListFromFile("src\\day14\\part1\\files\\input.txt");
        String[] mask;
        for (String row : listOfRows) {
            if (row.contains("mask")) {
                mask = row.split("=");
                System.out.println(mask[1]);
            }
            if (row.contains("mem")) {
                String[] mem = row.split("=");
                System.out.println("mem: " + mem[0]);
                System.out.println("mem1: " + mem[1]);
                int memoryAddress = Integer.parseInt(mem[0].substring(4,mem[0].length()-2));
                System.out.println("address: " + memoryAddress);
            }
        }
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
