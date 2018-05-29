import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Problem07FixEmails {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, String> emails = new LinkedHashMap<>();
        fillEmails(reader, emails);
        printResult(emails);
    }

    private static void fillEmails(BufferedReader reader, Map<String, String> emails) throws IOException {
        while(true){
            String line2 = reader.readLine();
            if("stop".equals(line2)){
                break;
            }

            String line1 = reader.readLine().toLowerCase();
            int start = line1.length() - 3;
            String ends = line1.substring(start, line1.length());
            checkEnds(emails, line2, line1, ends);
        }
    }

    private static void checkEnds(Map<String, String> emails, String line2, String line1, String ends) {
        if((ends.equals(".us") || ends.equals(".uk") || ends.equals("com")) == false){
            emails.put(line2, line1);
        }
    }

    private static void printResult(Map<String, String> emails) {
        for (Map.Entry<String, String> emailEntry : emails.entrySet()) {
            System.out.printf("%s -> %s%n", emailEntry.getKey(), emailEntry.getValue());
        }
    }
}
