import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Problem03CopyBytes {
    public static void main(String[] args) {
        String resources = "D:\\Soft\\SoftUni\\02.Software-University-SoftUni\\Software-University-SoftUni\\Java-Fundamentals-May-2018\\Java-Advanced-May-2018\\09.Files And Directories - Lab\\Resources";
        String inputPath = resources + "\\input.txt";
        String outputPath = resources + "\\output.txt";

        try (InputStream fileInputStream = new FileInputStream(inputPath);
             OutputStream fileOutputStream = new FileOutputStream(outputPath);
        ){
            int oneByte = fileInputStream.read();
            int asciiCodeSpace = 32;
            int asciiCodeNewLine = 10;
            while (oneByte >= 0){
                if (asciiCodeSpace == oneByte || asciiCodeNewLine == oneByte){
                    fileOutputStream.write(oneByte);
                } else {
                    String digits = String.valueOf(oneByte);
                    for (int i = 0; i < digits.length(); i++) {
                        fileOutputStream.write(digits.charAt(i));
                    }
                }

                oneByte = fileInputStream.read();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
