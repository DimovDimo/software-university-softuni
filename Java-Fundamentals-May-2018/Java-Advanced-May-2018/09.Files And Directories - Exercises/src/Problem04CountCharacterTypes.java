import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Problem04CountCharacterTypes {
    public static void main(String[] args) {
        String resources = "D:\\Soft\\SoftUni\\02.Software-University-SoftUni\\Software-University-SoftUni\\Java-Fundamentals-May-2018\\Java-Advanced-May-2018\\09.Files And Directories - Exercises\\Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources";
        String inputPathString = resources + "\\input.txt";
        String outputPathString = resources + "\\output.txt";
        Path inputPath = Paths.get(inputPathString);
        Path outputPath = Paths.get(outputPathString);
        try (BufferedReader reader = Files.newBufferedReader(inputPath);
             PrintWriter writer = new PrintWriter(new FileWriter(String.valueOf(outputPath)))){
            List<String> lines = Files.readAllLines(inputPath);
            int countVowels = 0;
            int countConsonants = 0;
            int countPunctuation = 0;
            List<Character> vowels = new LinkedList<>();
            for (String line : lines) {
                char[] characters = line.toCharArray();
                for (char character : characters) {
                    if ("aeiou".indexOf(character) < 0){
                        countVowels++;
                    } else if ("!,.?".indexOf(character) < 0){
                        countPunctuation++;
                    } else if (character != ' '){
                        countConsonants++;
                    }
                }
            }

            writer.println(String.format("Vowels: %d", countVowels));
            writer.println(String.format("Consonants: %d", countConsonants));
            writer.println(String.format("Punctuation: %d", countPunctuation));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
