import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Problem02SumBytes {
    public static void main(String[] args) {
        String resources = "D:\\Soft\\SoftUni\\02.Software-University-SoftUni\\Software-University-SoftUni\\Java-Fundamentals-May-2018\\Java-Advanced-May-2018\\09.Files And Directories - Exercises\\Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources";
        String inputPathString = resources + "\\input.txt";
        String outputPathString = resources + "\\output.txt";
        Path inputPath = Paths.get(inputPathString);
        Path outputPath = Paths.get(outputPathString);
        long sum = 0;
        try (BufferedReader reader = Files.newBufferedReader(inputPath)){
            String line = reader.readLine();
            while (line != null){
                for (char c : line.toCharArray()) {
                    sum += c;
                }

                line = reader.readLine();
            }

            System.out.println(sum);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
