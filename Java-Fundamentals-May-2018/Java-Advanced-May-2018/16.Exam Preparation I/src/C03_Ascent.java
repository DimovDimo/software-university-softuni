import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class C03_Ascent {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder message = new StringBuilder();
        while (true){
            String line = reader.readLine();
            if ("Ascend".equals(line)){
                break;
            }
            
            message.append(line)
                    .append(System.lineSeparator());
        }

        Pattern pattern = Pattern.compile("([,_])([A-Za-z]+)([0-9])");
        Matcher matcher = pattern.matcher(message.toString());
        while (matcher.find()){
            String mach = matcher.group();
            String prefix = matcher.group(1);
            char[] values = matcher.group(2).toCharArray();
            int postfix = Integer.parseInt(matcher.group(3));
            for (int i = 0; i < values.length; i++) {
                if (prefix.equals(",")){
                    values[i] += postfix;
                } else {
                    values[i] -= postfix;
                }
            }

            String decoder = getDecoder(values);
            int start = message.indexOf(mach);
            int end = start + mach.length();
            message.replace(start, end, decoder);
            matcher = pattern.matcher(message.toString());
        }

        System.out.println(message);
    }

    private static String getDecoder(char[] values) {
        StringBuilder result = new StringBuilder();
        for (char value : values) {
            result.append(value);
        }

        return result.toString();
    }
}
