package day18.part1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//TODO
public class Solution {
    public static void main(String[] args) throws IOException {
        String path = "src\\day18\\part1\\files\\input1.txt";
        List<String> listOfRows = getListFromFile(path);
        for (String row : listOfRows) {
            row = row.replace(" ", "");
            System.out.println(row);
            int result = 0;


            int firstNumber = 0;
            int secondNumber = 0;
            char operand = 0;
            result = intFromChar(row.charAt(0));

            for (int i = 1; i < row.length(); i++) {
                    firstNumber = result;
                    operand = row.charAt(i);
                    secondNumber = intFromChar(row.charAt(i + 1));
                    i++;

                result = getResultOperation(firstNumber, operand, secondNumber);
                System.out.println("res:" + result);
            }

            System.out.println(result);
        }

    }

    private static int getResultOperation(int firstNumber, char operand, int secondNumber) {
        int result = 0;
        switch (operand) {
            case '+':
                result = addition(firstNumber, secondNumber);
                break;
            case '*':
                result = multiplication(firstNumber, secondNumber);
                break;
        }
        return result;
    }

    private static int getIndexOfEndParenthese(String row) {
        int counter = 1;
        int index = 0;
        while (counter > 0) {
            index++;

            if (row.charAt(index) == ')') {
                counter--;

            } else if (row.charAt(index) == '(') {
                counter++;
            }

            if (index == row.length() - 1) break;
        }
        return index;
    }

    private static int expressionInParentheses(String expression) {
        System.out.println("expression: " + expression);

        int result = intFromChar(expression.charAt(0));
        for (int i = 0; i < expression.length(); i++) {
            switch (expression.charAt(i)) {
                case '+':
                    result = addition(result, intFromChar(expression.charAt(i + 1)));
                    i++;
                    break;
                case '*':
                    result = multiplication(result, intFromChar(expression.charAt(i + 1)));
                    i++;
                    break;
            }
        }

        return result;
    }

    private static int intFromChar(char charAt) {
        return Character.getNumericValue(charAt);
    }

    private static int addition(int x, int y) {
        return x + y;
    }

    private static int multiplication(int x, int y) {
        return x * y;
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
