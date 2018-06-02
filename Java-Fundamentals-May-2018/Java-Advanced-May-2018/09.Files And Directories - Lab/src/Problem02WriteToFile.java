import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Problem02WriteToFile {
    public static void main(String[] args) {
        String resources = "D:\\Soft\\SoftUni\\02.Software-University-SoftUni\\Software-University-SoftUni\\Java-Fundamentals-May-2018\\Java-Advanced-May-2018\\09.Files And Directories - Lab\\Resources";
        String inputPath = resources + "\\input.txt";
        String outputPath = resources + "\\output.txt";
        Set<Character> punctuation = new HashSet<>();
        Collections.addAll(punctuation, '.', ',', '!', '?');

        try (InputStream fileInputStream = new FileInputStream(inputPath);
             OutputStream fileOutputStream = new FileOutputStream(outputPath);
        ){
            int oneByte = fileInputStream.read();
            while (oneByte >= 0){
                if (!punctuation.contains((char)oneByte)){
                    fileOutputStream.write(oneByte);
                }

                oneByte = fileInputStream.read();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
