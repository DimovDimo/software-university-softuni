import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem16ExtractHyperlinks {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder input = new StringBuilder();
        readHTML(reader, input);
        selectText(input);
    }

    private static void selectText(StringBuilder input) {
        Pattern pattern = Pattern.compile("<a[^>]+?href\\s*=\\s*(\".*?\"|'.*?'|.*?)[\\s>]");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String group = matcher.group(1);
            group = getGroup(group);
            printResult(group);
        }
    }

    private static void printResult(String group) {
        System.out.println(group);
    }

    private static String getGroup(String group) {
        if (group.startsWith("\"") || group.startsWith("'")) {
            group = group.substring(1, group.length() - 1);
        }
        return group;
    }

    private static void readHTML(BufferedReader reader, StringBuilder input) throws IOException {
        while (true){
            String line = reader.readLine();
            if ("END".equals(line)){
                break;
            }

            input.append(line);
        }
    }
}
