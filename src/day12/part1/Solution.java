package day12.part1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static int positionX = 0;
    public static int positionY = 0;
    public static Directions direction = Directions.East;

    public static void main(String[] args) throws IOException {
        List<String> listOfRows = getListFromFile("src\\day12\\part1\\files\\input.txt");

        int distance = getDistance(listOfRows);
        System.out.println("The Manhattan distance is: " + distance);
    }

    private static int getDistance(List<String> listOfRows) {

        for (int i = 0; i < listOfRows.size(); i++) {
            String action = listOfRows.get(i).substring(0, 1);
            int value = Integer.parseInt(listOfRows.get(i).substring(1));

            switch (action) {
                case "N":
                    positionY += value;
                    break;
                case "S":
                    positionY -= value;
                    break;
                case "E":
                    positionX += value;
                    break;
                case "W":
                    positionX -= value;
                    break;
                case "L":
                    direction = turn(direction, value, true);
                    break;
                case "R":
                    direction = turn(direction, value, false);
                    break;
                case "F":
                    moveForward(direction, value);
                    break;

            }
        }
        return Math.abs(positionX) + Math.abs(positionY);
    }

    private static void moveForward(Directions direction, int value) {
        switch (direction) {
            case East:
                positionX += value;
                ;
                break;
            case West:
                positionX -= value;
                break;
            case North:
                positionY += value;
                break;
            case South:
                positionY -= value;
                break;
        }
    }

    private static Directions turn(Directions direction, int angle, boolean isLeft) {
        switch (direction) {
            case East:
                if (angle == 90) {
                    direction = isLeft ? Directions.North : Directions.South;
                } else if (angle == 180) {
                    direction = Directions.West;
                } else if (angle == 270) {
                    direction = isLeft ? Directions.South : Directions.North;
                }
                break;
            case West:
                if (angle == 90) {
                    direction = isLeft ? Directions.South : Directions.North;
                } else if (angle == 180) {
                    direction = Directions.East;
                } else if (angle == 270) {
                    direction = isLeft ? Directions.North : Directions.South;
                }
                break;
            case North:
                if (angle == 90) {
                    direction = isLeft ? Directions.West : Directions.East;
                } else if (angle == 180) {
                    direction = Directions.South;
                } else if (angle == 270) {
                    direction = isLeft ? Directions.East : Directions.West;
                }
                break;
            case South:
                if (angle == 90) {
                    direction = isLeft ? Directions.East : Directions.West;
                } else if (angle == 180) {
                    direction = Directions.North;
                } else if (angle == 270) {
                    direction = isLeft ? Directions.West : Directions.East;
                }
                break;
        }
        return direction;
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
