import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem11ReplaceATag {
    public static void main(String[] args) throws IOException {
        StringBuilder text = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String line = reader.readLine();
            if ("END".equals(line)){
                break;
            }

            text.append(line).append(System.lineSeparator());
        }

        replaceHref(text);
        replaceCloseATag(text);
        System.out.println(text);
    }

    private static void replaceCloseATag(StringBuilder text) {
        Pattern pattern = Pattern.compile("</a>");
        Matcher matcher = pattern.matcher(text.toString());
        while (matcher.find()){
            String replasmend = "[/URL]";
            int startIndex = text.indexOf(matcher.group());
            int endIndex = startIndex + matcher.group().length();
            text.replace(startIndex, endIndex, replasmend);
        }
    }

    private static void replaceHref(StringBuilder text) {
        Pattern pattern = Pattern.compile("<a href=(.*?)>");
        Matcher matcher = pattern.matcher(text.toString());
        while (matcher.find()){
            String href = matcher.group(1);
            String replasmend = String.format("[URL href=%s]", href);
            int startIndex = text.indexOf(matcher.group());
            int endIndex = startIndex + matcher.group().length();
            text.replace(startIndex, endIndex, replasmend);
        }
    }
}
