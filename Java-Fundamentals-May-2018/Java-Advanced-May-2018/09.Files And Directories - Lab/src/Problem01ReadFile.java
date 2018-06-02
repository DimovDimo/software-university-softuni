import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Problem01ReadFile {
    public static void main(String[] args) {
        String resources = "D:\\Soft\\SoftUni\\02.Software-University-SoftUni\\Software-University-SoftUni\\Java-Fundamentals-May-2018\\Java-Advanced-May-2018\\09.Files And Directories - Lab\\Resources";
        String path = resources + "\\input.txt";

        try (InputStream fileStream = new FileInputStream(path);
        ){
            int oneByte = fileStream.read();
            while (oneByte >= 0){
                System.out.printf("%s ", Integer.toBinaryString(oneByte));
                oneByte = fileStream.read();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
