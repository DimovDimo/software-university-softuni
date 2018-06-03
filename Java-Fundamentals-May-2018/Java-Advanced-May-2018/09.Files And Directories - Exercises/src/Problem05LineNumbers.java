import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Problem05LineNumbers {
    public static void main(String[] args) {
        String resources = "D:\\Soft\\SoftUni\\02.Software-University-SoftUni\\Software-University-SoftUni\\Java-Fundamentals-May-2018\\Java-Advanced-May-2018\\09.Files And Directories - Exercises\\Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources";
        String inputPathString = resources + "\\inputLineNumbers.txt ";
        String outputPathString = resources + "\\output.txt";
        Path inputPath = Paths.get(inputPathString);
        Path outputPath = Paths.get(outputPathString);
        try (BufferedReader reader = Files.newBufferedReader(inputPath);
             PrintWriter writer = new PrintWriter(new FileWriter(String.valueOf(outputPath)))){
            List<String> lines = Files.readAllLines(inputPath);
            for (int i = 0; i < lines.size(); i++) {
                String outputString = String.format("%d. %s", i+1, lines.get(i));
                writer.println(outputString);
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
