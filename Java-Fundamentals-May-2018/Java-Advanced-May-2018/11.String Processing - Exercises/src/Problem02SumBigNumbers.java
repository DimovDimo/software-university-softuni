import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Problem02SumBigNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstNum = reader.readLine();
        String secondNum = reader.readLine();
        StringBuilder result = getResult(firstNum, secondNum);
        printResult(result);
    }

    private static void printResult(StringBuilder result) {
        System.out.println(result.reverse()
                .toString()
                .replaceFirst("^0+(?!$)", ""));
    }

    private static StringBuilder getResult(String firstNum, String secondNum) {
        int size = getMax(firstNum, secondNum);
        String firstStringNum = getFormat(firstNum, size);
        String secondStringNum = getFormat(secondNum, size);
        StringBuilder result = new StringBuilder();
        int overflow = 0;
        overflow = getOverflow(size, firstStringNum, secondStringNum, result, overflow);
        checkOverflow(result, overflow);
        return result;
    }

    private static int getOverflow(int size, String firstStringNum, String secondStringNum, StringBuilder result, int overflow) {
        for (int i = size - 1; i >= 0; i--) {
            int sum = firstStringNum.charAt(i) - '0' + secondStringNum.charAt(i) - '0' + overflow;
            result.append(sum % 10);
            overflow = sum / 10;
        }
        return overflow;
    }

    private static String getFormat(String firstNum, int size) {
        return String.format("%0" + size + "d", new BigInteger(firstNum));
    }

    private static int getMax(String firstNum, String secondNum) {
        return Math.max(firstNum.length(), secondNum.length());
    }

    private static void checkOverflow(StringBuilder result, int overflow) {
        if (overflow == 1) {
            result.append('1');
        }
    }
}
