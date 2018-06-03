import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem01StudentResults {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] studentResult = reader.readLine().split("[,\\s\\-]+");
        String name = studentResult[0];
        double jadv = Double.parseDouble(studentResult[1]);
        double javaOOP = Double.parseDouble(studentResult[2]);
        double advOOP = Double.parseDouble(studentResult[3]);
        double average = (jadv + javaOOP + advOOP) / 3;

        System.out.printf("%1$-10s|%2$7s|%3$7s|%4$7s|%5$7s|%n", "Name", "JAdv", "JavaOOP", "AdvOOP", "Average");
        System.out.printf("%1$-10s|%2$7.2f|%3$7.2f|%4$7.2f|%5$7.4f|", name, jadv, javaOOP, advOOP, average);
    }
}
