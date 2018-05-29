import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Scanner;

public class Problem14SimpleTextEditor {
    public static void main(String[] args) throws IOException {
        ArrayDeque<String> stack = new ArrayDeque<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(reader.readLine());
        String workingString = "";
        for (int i = 0; i < number; i++) {
            String[] input = reader.readLine().split("\\s+");
            int working = Integer.parseInt(input[0]);
            switch (working) {
                case 1:
                    workingString = getStringAppends(stack, workingString, input);
                    break;
                case 2:
                    workingString = getStringErases(stack, workingString, input);
                    break;
                case 3:
                    printCharAtPosition(workingString, input[1]);
                    break;
                case 4:
                    workingString = stack.pop();
                    break;
            }
        }
    }

    private static void printCharAtPosition(String workingString, String s) {
        int position = Integer.parseInt(s) - 1;
        System.out.println(workingString.charAt(position));
    }

    private static String getStringErases(ArrayDeque<String> previousCommands, String workingString, String[] input) {
        previousCommands.push(workingString);
        workingString = workingString.substring(0, workingString.length() - Integer.parseInt(input[1]));
        return workingString;
    }

    private static String getStringAppends(ArrayDeque<String> previousCommands, String workingString, String[] input) {
        previousCommands.push(workingString);
        workingString += input[1];
        return workingString;
    }
}
