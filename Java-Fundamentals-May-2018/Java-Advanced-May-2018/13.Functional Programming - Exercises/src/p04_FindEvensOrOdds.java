import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.BiPredicate;

public class p04_FindEvensOrOdds {
    public static void main(String[] args) throws IOException {
        BiPredicate<Integer, String> isTargetNumber = (num, condition) -> {
            if ("odd".equals(condition) && num % 2 != 0){
                return true;
            } else if ("even".equals(condition) && num % 2 == 0){
                return true;
            } else {
                return false;
            }
        };

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = reader.readLine().split("\\s+");
        String state =reader.readLine();
        int start = Integer.parseInt(inputs[0]);
        int end = Integer.parseInt(inputs[1]);
        for (int i = start; i <= end; i++) {
            if (isTargetNumber.test(i, state)){
                System.out.print(i + " ");
            }
        }
    }
}
