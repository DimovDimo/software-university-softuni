import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;

public class Problem08NestedFolders {
    public static void main(String[] args) {
        String resources = "D:\\Soft\\SoftUni\\02.Software-University-SoftUni\\Software-University-SoftUni\\Java-Fundamentals-May-2018\\Java-Advanced-May-2018\\09.Files And Directories - Lab\\Resources";
        String rootDirectoryPath = resources + "\\Files-and-Streams";
        String inputPathString = resources + "\\input.txt";
        String outputPathString = resources + "\\output.txt";

        Path inputPath = Paths.get(inputPathString);
        Path outputPath = Paths.get(outputPathString);

        File rootDir = new File(rootDirectoryPath);
        if (!rootDir.exists()){
            return;
        }

        File[] files = rootDir.listFiles();
        if (files == null){
            return;
        }

        ArrayDeque<File> queue = new ArrayDeque<>();
        queue.add(rootDir);
        System.out.println(rootDir.getName());
        int counter = 1;
        while (!queue.isEmpty()){
            File currentDir = queue.remove();
            File[] currentFiles = currentDir.listFiles();
            if (currentFiles == null){
                continue;
            }

            for (File file : currentFiles) {
                if (file.isDirectory()){
                    counter++;
                    System.out.println(file.getName());
                    queue.add(file);
                }
            }
        }

        System.out.printf("%d folders", counter);
    }
}
