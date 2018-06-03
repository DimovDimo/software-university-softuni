import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem07ValidUsernames {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> status = new LinkedList<>();
        while (true){
            String line = reader.readLine();
            if ("END".equals(line)){
                break;
            }

            Pattern pattern = Pattern.compile("^[\\w-]{3,16}$");
            Matcher matcher = pattern.matcher(line);

            if (matcher.find()){
                status.add("valid");
            } else {
                status.add("invalid");
            }
        }

        if (status.contains("valid")){
            for (String currentStatus : status) {
                System.out.println(currentStatus);
            }
        }
    }
}
