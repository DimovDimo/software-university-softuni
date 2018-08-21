import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A01_DHARMASupplies {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder inputStringBuilder = new StringBuilder();
        int inputLines = 0;
        while (true){
            String line = reader.readLine();
            if ("Collect".equals(line)){
                break;
            }

            inputStringBuilder.append(line).append(System.lineSeparator());
            inputLines++;
        }

        String input = inputStringBuilder.toString();
        Pattern supplyCratePattern = Pattern.compile("(\\[.*?\\])");
        Matcher supplyCrateMatcher = supplyCratePattern.matcher(input);
        List<String> supplyCrates = new ArrayList<>();
        while (supplyCrateMatcher.find()){
            supplyCrates.add(supplyCrateMatcher.group(1));
        }

        int n = supplyCrates.size()/inputLines;

        Pattern validSupplyCratePattern = Pattern.compile("\\[(#(\\d{" + n + "}|[a-z]{" + n + "}))([A-Za-z0-9\\s]+)(\\1)]");
        int countValidSupplyCrates = 0;
        int amountOfFood = 0;
        int amountOfDrinks = 0;
        for (String supplyCrate : supplyCrates) {
            Matcher validSsupplyCrateMatcher = validSupplyCratePattern.matcher(supplyCrate);
            if (validSsupplyCrateMatcher.find()){
                countValidSupplyCrates++;
                String tag = validSsupplyCrateMatcher.group(2);
                String body = validSsupplyCrateMatcher.group(3);
                if (Character.isDigit(tag.charAt(0))){
                    amountOfFood += getAmountOfFood(tag, body);
                } else {
                    amountOfDrinks += getAmountOfDrinks(tag, body);
                }
            }
        }

        if (countValidSupplyCrates == 0){
            System.out.printf("No supplies found!");
        } else {
            System.out.printf(String.format("Number of supply crates: %d%n", countValidSupplyCrates));
            System.out.printf(String.format("Amount of food collected: %d%n", amountOfFood));
            System.out.printf(String.format("Amount of drinks collected: %d%n", amountOfDrinks));
        }
    }

    private static int getAmountOfDrinks(String tag, String body) {
        int sumBody = Arrays.stream(body.split("")).mapToInt(s -> s.charAt(0)).sum();
        int sumTag = Arrays.stream(tag.split("")).mapToInt(s -> s.charAt(0)).sum();

        return sumBody * sumTag;
    }

    private static int getAmountOfFood(String tag, String body) {
        int sumBody = Arrays.stream(body.split("")).distinct().mapToInt(s -> s.charAt(0)).sum();
        int lenghtTag = tag.length();

        return sumBody * lenghtTag;
    }
}
