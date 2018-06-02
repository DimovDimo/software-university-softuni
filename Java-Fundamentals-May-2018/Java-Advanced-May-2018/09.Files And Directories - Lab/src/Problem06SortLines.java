import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Problem06SortLines {
    public static void main(String[] args) {
        String resources = "D:\\Soft\\SoftUni\\02.Software-University-SoftUni\\Software-University-SoftUni\\Java-Fundamentals-May-2018\\Java-Advanced-May-2018\\09.Files And Directories - Lab\\Resources";
        String inputPathString = resources + "\\input.txt";
        String outputPathString = resources + "\\output.txt";

        Path inputPath = Paths.get(inputPathString);
        Path outputPath = Paths.get(outputPathString);

        try (BufferedReader reader = Files.newBufferedReader(inputPath)){
            List<String> lines = Files.readAllLines(inputPath);
            Collections.sort(lines);
            Files.write(outputPath, lines);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
