import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Problem07MergeTwoFiles {
    public static void main(String[] args) {
        String resources = "D:\\Soft\\SoftUni\\02.Software-University-SoftUni\\Software-University-SoftUni\\Java-Fundamentals-May-2018\\Java-Advanced-May-2018\\09.Files And Directories - Exercises\\Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources";
        String inputOnePathString = resources + "\\inputOne.txt";
        String inputTwoPathString = resources + "\\inputTwo.txt";
        String outputPathString = resources + "\\mergeTwoFiles.txt";
        Path inputOnePath = Paths.get(inputOnePathString);
        Path inputTwoPath = Paths.get(inputTwoPathString);
        Path outputPath = Paths.get(outputPathString);
        try (BufferedReader readerOne = Files.newBufferedReader(inputOnePath);
             BufferedReader readerTwo = Files.newBufferedReader(inputTwoPath);
             PrintWriter writer = new PrintWriter(new FileWriter(String.valueOf(outputPath)))){
            List<String> firstFile = Files.readAllLines(inputOnePath);
            List<String> secondFile = Files.readAllLines(inputTwoPath);
            for (String line : firstFile) {
                writer.println(line);
            }

            for (String line : secondFile) {
                writer.println(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
