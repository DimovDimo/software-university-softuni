import java.io.*;
import java.util.Scanner;

public class Problem05WriteEveryThirdLine {
    public static void main(String[] args) {
        String resources = "D:\\Soft\\SoftUni\\02.Software-University-SoftUni\\Software-University-SoftUni\\Java-Fundamentals-May-2018\\Java-Advanced-May-2018\\09.Files And Directories - Lab\\Resources";
        String inputPath = resources + "\\input.txt";
        String outputPath = resources + "\\output.txt";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputPath)));
             PrintWriter writer = new PrintWriter(new FileWriter(outputPath))){
            String line = reader.readLine();
            int counter = 1;
            while (line != null){
                if (counter % 3 == 0){
                    writer.println(line);
                }

                line = reader.readLine();
                counter++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
