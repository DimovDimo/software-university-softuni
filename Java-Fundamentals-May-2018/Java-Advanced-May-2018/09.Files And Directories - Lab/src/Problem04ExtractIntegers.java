import java.io.*;
import java.util.Scanner;

public class Problem04ExtractIntegers {
    public static void main(String[] args) {
        String resources = "D:\\Soft\\SoftUni\\02.Software-University-SoftUni\\Software-University-SoftUni\\Java-Fundamentals-May-2018\\Java-Advanced-May-2018\\09.Files And Directories - Lab\\Resources";
        String inputPath = resources + "\\input.txt";
        String outputPath = resources + "\\output.txt";

        try (Scanner scanner = new Scanner(new FileInputStream(inputPath));
        PrintWriter writer = new PrintWriter(new FileOutputStream(outputPath))){
            while (scanner.hasNext()){
                if (scanner.hasNextInt()){
                    writer.println(scanner.nextInt());
                }

                scanner.next();
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
