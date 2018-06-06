import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.BiPredicate;

public class p07_PredicateForNames {
    public static void main(String[] args) throws IOException {
        BiPredicate<Integer, String> isNameLenghtLessOrEqual = (lenght, name) -> {
            if (name.length() <= lenght){
                return true;
            } else {
                return false;
            }
        };

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int targetlenght = Integer.parseInt(reader.readLine());
        String[] names = reader.readLine().split("\\s+");
        for (String name : names) {
            if (isNameLenghtLessOrEqual.test(targetlenght, name)){
                System.out.println(name);
            }
        }
    }
}
