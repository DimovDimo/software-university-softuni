import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Problem09SerializeCustomObject {
    public static void main(String[] args) {
        String resources = "D:\\Soft\\SoftUni\\02.Software-University-SoftUni\\Software-University-SoftUni\\Java-Fundamentals-May-2018\\Java-Advanced-May-2018\\09.Files And Directories - Lab\\Resources";
        String serializableObjectPath = resources + "\\object.ser";
        Cube cube = new Cube();
        cube.color = "green";
        cube.width = 15.3d;
        cube.height = 12.4d;
        cube.depth = 3d;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serializableObjectPath));){
            oos.writeObject(cube);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
