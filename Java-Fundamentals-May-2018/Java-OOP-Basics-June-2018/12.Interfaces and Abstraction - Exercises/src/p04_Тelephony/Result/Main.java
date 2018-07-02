

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] phoneNumbers = reader.readLine().split("\\s+");
        String[] sites = reader.readLine().split("\\s+");
        Smartphone smartphone = new Smartphone();
        callingPhoneNumbers(phoneNumbers, smartphone);
        browsingSites(sites, smartphone);
    }

    private static void browsingSites(String[] sites, Smartphone smartphone) {
        for (String site : sites) {
            System.out.println(smartphone.Browsing(site));
        }
    }

    private static void callingPhoneNumbers(String[] phoneNumbers, Smartphone smartphone) {
        for (String phoneNumber : phoneNumbers) {
            System.out.println(smartphone.Calling(phoneNumber));
        }
    }
}
