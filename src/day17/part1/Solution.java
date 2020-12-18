package day17.part1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) throws IOException {
        String path = "src\\day17\\part1\\files\\input1.txt";
        List<String> listOfRows = getListFromFile(path);
        List<Cube> listOfCubes = new ArrayList<>();

        for (int i = 0; i < listOfRows.size(); i++) {
            String[] splitedRow = listOfRows.get(i).split("");
            Cube cube = null;
            int z = 0;
            for (int j = 0; j < splitedRow.length; j++) {
                if (".".equals(splitedRow[j])) {
                    cube = new Cube(j, i, z, false);
                } else if ("#".equals(splitedRow[j])) {
                    cube = new Cube(j, i, z, true);
                }
                listOfCubes.add(cube);
            }
        }
        for (int z = - 1; z < 2; z++) {
            if (z==0) continue;
            for (int x = 0; x < listOfRows.size(); x++) {
                for (int y = 0; y < listOfRows.size(); y++) {
                    listOfCubes.add(new Cube(x, y, z, false));
                }
            }
        }

        Cube cube1 = new Cube(0,1,0, true);
        cube1.setActive(true);
        listOfCubes.add(cube1);

        for (Cube cube : listOfCubes) {
            cube.getX();
            System.out.println(cube.getX() + " " + cube.getY() + " " + cube.getZ() + " " + cube.isActive());
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
