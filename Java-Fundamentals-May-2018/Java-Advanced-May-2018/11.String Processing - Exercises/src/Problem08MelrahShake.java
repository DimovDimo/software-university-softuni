import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem08MelrahShake {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder input = new StringBuilder(reader.readLine());
        StringBuilder element = new StringBuilder(reader.readLine());
        getShaked(input, element);
        printShakedIt("No shake.");
        printResult(input);
    }

    private static void printResult(StringBuilder input) {
        System.out.println(input);
    }

    private static void getShaked(StringBuilder input, StringBuilder element) {
        int start = getStart(input, element);
        int end = getEnd(input, element);
        while (isaBooleanMelrahShake(element, start, end)) {
            deleteStartAndEnd(input, element, start, end);
            printShakedIt("Shaked it.");
            element.deleteCharAt(element.length() / 2);
            start = input.indexOf(element.toString());
            end = input.lastIndexOf(element.toString());
        }
    }

    private static void printShakedIt(String s) {
        System.out.println(s);
    }

    private static void deleteStartAndEnd(StringBuilder input, StringBuilder element, int start, int end) {
        input.delete(end, end + element.length());
        input.delete(start, start + element.length());
    }

    private static boolean isaBooleanMelrahShake(StringBuilder element, int start, int end) {
        return element.length() > 0 && start != end && start != -1 && end != -1;
    }

    private static int getEnd(StringBuilder input, StringBuilder element) {
        return input.lastIndexOf(element.toString());
    }

    private static int getStart(StringBuilder input, StringBuilder element) {
        return input.indexOf(element.toString());
    }
}
