import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Problem08GetFolderSize {
    public static void main(String[] args) {
        String resources = "D:\\Soft\\SoftUni\\02.Software-University-SoftUni\\Software-University-SoftUni\\Java-Fundamentals-May-2018\\Java-Advanced-May-2018\\09.Files And Directories - Exercises\\Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources";
        String rootDirectoryPath = resources + "\\Exercises Resources";
        File rootDir = new File(rootDirectoryPath);
        if (!rootDir.exists()){
            return;
        }

        System.out.printf("Folder size: %d", rootDir.length());
    }
}
