import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Problem06WordCount {
    public static void main(String[] args) {
        String resources = "D:\\Soft\\SoftUni\\02.Software-University-SoftUni\\Software-University-SoftUni\\Java-Fundamentals-May-2018\\Java-Advanced-May-2018\\09.Files And Directories - Exercises\\Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources";
        String inputPathString = resources + "\\words.txt";
        String textPathString = resources + "\\text.txt";
        String outputPathString = resources + "\\results.txt";
        Path inputPath = Paths.get(inputPathString);
        Path textPath = Paths.get(textPathString);
        Path outputPath = Paths.get(outputPathString);
        try (BufferedReader reader = Files.newBufferedReader(inputPath);
             BufferedReader readerText = Files.newBufferedReader(textPath);
             PrintWriter writer = new PrintWriter(new FileWriter(String.valueOf(outputPath)))){
            String[] words = reader.readLine().split("\\s+");
            String[] textWords = readerText.readLine().split("\\s+");
            Map<String, Integer> wordCount = new LinkedHashMap<>();
            fillWordCountKeys(words, wordCount);
            countTheWords(textWords, wordCount);
            printResult(writer, wordCount);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void printResult(PrintWriter writer, Map<String, Integer> wordCount) {
        for (Map.Entry<String, Integer> wordCountEntry : wordCount.entrySet()) {
            writer.println(String.format("%s - %d", wordCountEntry.getKey(), wordCountEntry.getValue()));
        }
    }

    private static void countTheWords(String[] textWords, Map<String, Integer> wordCount) {
        for (String textWord : textWords) {
            if (wordCount.containsKey(textWord)){
                int currentValue = wordCount.get(textWord) + 1;
                wordCount.put(textWord, currentValue);
            }
        }
    }

    private static void fillWordCountKeys(String[] words, Map<String, Integer> wordCount) {
        for (String word : words) {
            wordCount.put(word, 0);
        }
    }
}
