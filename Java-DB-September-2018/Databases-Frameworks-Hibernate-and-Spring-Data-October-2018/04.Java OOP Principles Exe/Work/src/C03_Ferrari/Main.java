package C03_Ferrari;

import java.lang.instrument.IllegalClassFormatException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String driver = scanner.nextLine();
        Car ferrari = new Ferrari("488-Spider", driver);

        System.out.printf("%s/", ferrari.getModel());
        ferrari.brakes();
        System.out.print("/");
        ferrari.gasPedal();
        System.out.print("/");
        System.out.printf(ferrari.getDriver());

        String ferrariName = Ferrari.class.getSimpleName();
        String carInterface = Car.class.getSimpleName();
        boolean isCreated = Car.class.isInterface();

    }
}
