package D04_Telephony;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] phones = scanner.nextLine()
                .split(" ");
        String[] sites = scanner.nextLine().split(" ");
        Smartphone smartphone = new Smartphone();
        smartphone.call(phones);
        smartphone.browse(sites);
    }
}
