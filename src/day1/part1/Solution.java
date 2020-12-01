package day1.part1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {

        FileInputStream fileReader = new FileInputStream("src\\day1\\part1\\files\\input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileReader, StandardCharsets.UTF_8));
        ArrayList<Integer> list = new ArrayList<>();
        String row;
        int numberInRow;
        boolean match = false;
        int result = 0;

        while ((row = br.readLine()) != null) {
            numberInRow = Integer.parseInt(row);
            list.add(numberInRow);
        }

        for (int i = 0; i < list.size(); i++) {
            if (match) break;

            for (int j = 0; j < list.size(); j++) {

                if (list.get(i) == list.get(j)) continue;

                if (list.get(i) + list.get(j) == 2020) {
                    result = list.get(i) * list.get(j);
                    match = true;
                    break;
                }
            }
        }

        System.out.println("The result of the multiplication is: " + result);

        fileReader.close();
        br.close();
    }
}
