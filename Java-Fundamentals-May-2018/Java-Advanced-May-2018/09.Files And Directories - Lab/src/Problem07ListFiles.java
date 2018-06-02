import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Problem07ListFiles {
    public static void main(String[] args) {
        String resources = "D:\\Soft\\SoftUni\\02.Software-University-SoftUni\\Software-University-SoftUni\\Java-Fundamentals-May-2018\\Java-Advanced-May-2018\\09.Files And Directories - Lab\\Resources";
        String rootDirectoryPath = resources + "\\Files-and-Streams";
        String inputPathString = resources + "\\input.txt";
        String outputPathString = resources + "\\output.txt";

        Path inputPath = Paths.get(inputPathString);
        Path outputPath = Paths.get(outputPathString);

        try (BufferedReader reader = Files.newBufferedReader(inputPath)){
            File rootDir = new File(rootDirectoryPath);
            File[] files = rootDir.listFiles();
            for (File file : files) {
                if (!file.isDirectory()){
                    System.out.printf("%s: %s%n", file.getName(), file.length());
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
