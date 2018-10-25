package D04_Telephony;

import java.util.regex.Pattern;

public class Smartphone implements Callable, Browsable {
    @Override
    public void browse(String[] sites) {
        for (String site : sites) {
            if (Pattern.matches("[^0-9]+", site) || "".equals(site)){
                System.out.printf("Browsing: %s!%n", site);
            } else {
                System.out.println("Invalid URL!");
            }
        }
    }

    @Override
    public void call(String[] phones) {
        for (String phone : phones) {
            if (Pattern.matches("\\d+", phone) || "".equals(phone)){
                System.out.printf("Calling... %s%n", phone);
            } else {
                System.out.println("Invalid number!");
            }
        }
    }
}
