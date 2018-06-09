import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.OptionalDouble;

public class p06_FindAndSumIntegers {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        OptionalDouble sum = Arrays.stream(reader.readLine().split("\\s+"))
                .filter(x -> {
                    try {
                        Integer.parseInt(x);
                        return true;
                    } catch (Exception e){
                        return false;
                    }
                })
                .mapToDouble(Double::parseDouble)
                .reduce((x, y) -> x + y);
        if (sum.isPresent()){
            System.out.printf("%.0f", sum.getAsDouble());
        } else {
            System.out.println("No match");
        }
    }
}
