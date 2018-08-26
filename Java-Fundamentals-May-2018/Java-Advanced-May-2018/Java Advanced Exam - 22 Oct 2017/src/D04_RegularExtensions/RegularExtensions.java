package D04_RegularExtensions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegularExtensions {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String text = reader.readLine();

        while (true){
            String line = reader.readLine();
            if ("Print".equals(line)){
                break;
            }

            if (line.indexOf('%') > -1){
                line = line.replace("%", "[\\S]*");
                line = line.replace(".", "\\.");
                Pattern pattern = Pattern.compile(line);
                Matcher matcher = pattern.matcher(text);
                while (matcher.find()){
                    String group = matcher.group();
                    text = text.replace(group, new StringBuilder(group).reverse().toString());
                }
            } else {
                text = text.replace(line, new StringBuilder(line).reverse().toString());
            }
        }

        System.out.println(text);
    }
}
