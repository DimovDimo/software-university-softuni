import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem15ValidUsernames {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        Pattern pattern = Pattern.compile("\\b[A-Za-z][\\w]{2,24}\\b");
        Matcher matcher = pattern.matcher(line);

        int bestSum = Integer.MIN_VALUE;
        String bestFirst = "";
        String bestSecond = "";
        List<String> usernames = new ArrayList<String>();

        while (matcher.find()){
            usernames.add(matcher.group());
        }

        for (int i = 1; i < usernames.size(); i++) {
            String currentFirst = usernames.get(i - 1);
            String currentSecond = usernames.get(i);
            int currentSum = currentFirst.length() + currentSecond.length();
            if (bestSum < currentSum){
                bestSum = currentSum;
                bestFirst = currentFirst;
                bestSecond = currentSecond;
            }
        }

        System.out.println(bestFirst);
        System.out.println(bestSecond);
    }
}
