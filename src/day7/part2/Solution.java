package day7.part2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private static Map<String, List<ChildrenBags1>> mapOfBags = new HashMap<>();

    public static void main(String[] args) throws IOException {
        FileInputStream fileReader = new FileInputStream("src\\day7\\part2\\files\\input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileReader, StandardCharsets.UTF_8));
        List<String> listOfRows = new ArrayList<>();
        String row;
        int bagColors = 0;

        while ((row = br.readLine()) != null) {
            listOfRows.add(row);
        }
        listOfRows.add("");

        for (String splitedRow : listOfRows) {
            String[] splitedRows =
                    splitedRow.replace("no other", "")
                            .replace(".", "")
                            .split("bags contain");
            String mainBag = splitedRows[0].trim();
            String bagContent = "";

            if (splitedRows.length == 2) {
                bagContent = splitedRows[1].replace("bags", "").replace("bag", "").trim();
            }
            String[] bagsInMainBag = bagContent.split(",");
            List<ChildrenBags1> childrenBags = new ArrayList<>();

            for (String bagInMainBag : bagsInMainBag) {
                bagInMainBag = bagInMainBag.trim();
                if (!bagInMainBag.isEmpty()) {
                    int quantity = Integer.parseInt(bagInMainBag.substring(0, 1));
                    String name = bagInMainBag.substring(1).trim();
                    childrenBags.add(new ChildrenBags1(quantity, name));
                }
            }

            mapOfBags.put(mainBag, childrenBags);
        }

        System.out.print("In my single shiny gold bag are: " + (countBagsInGoldBag("shiny gold") - 1) + " bags");
    }


    private static int countBagsInGoldBag(String bag) {
        List<ChildrenBags1> bagList = mapOfBags.get(bag);
        int count = 0;
        for (ChildrenBags1 bagInfo : bagList)
            count += (bagInfo.getQuantityOfBags() * countBagsInGoldBag(bagInfo.getNameOfBags()));
        return count;
    }
}
