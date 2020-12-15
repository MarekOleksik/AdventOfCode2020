package day15.part2;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        String input = "12,20,0,6,1,17,7";
        String[] inputNumbers = input.split(",");
        Map<Integer, Integer> mapOfOccurencesOfNumber = new HashMap<>();
        int lastNumber = 0;
        int numberSpoken = 0;

        for (int i = 0; i < inputNumbers.length; i++) {
            int number = Integer.parseInt(inputNumbers[i]);
            numberSpoken++;
            if (i == inputNumbers.length - 1) {
                lastNumber = number;

            } else {
                mapOfOccurencesOfNumber.put(number, numberSpoken);
            }
        }

        for (int j = numberSpoken; j < 30000000; j++) {

            int tempLastNumber = mapOfOccurencesOfNumber.getOrDefault(lastNumber, -1);
            mapOfOccurencesOfNumber.put(lastNumber, j);
            if (tempLastNumber != -1) {
                lastNumber = j - tempLastNumber;
            } else {
                lastNumber = 0;
            }
        }

        System.out.println("The 30000000th number spoken is: " + lastNumber);

    }
}
