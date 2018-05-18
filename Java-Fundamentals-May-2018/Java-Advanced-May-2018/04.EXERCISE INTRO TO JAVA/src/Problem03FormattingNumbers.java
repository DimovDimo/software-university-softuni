import java.util.Scanner;

public class Problem03FormattingNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberA = scanner.nextInt();
        double numberB = scanner.nextDouble();
        double numberC = scanner.nextDouble();

        String hex = Integer
                .toHexString((int)numberA)
                .toUpperCase();
        while (hex.length() < 10){
            hex += " ";
        }

        String binary = Integer
                .toBinaryString((int)numberA)
                .toUpperCase();
        while (binary.length() < 10){
            binary = "0" + binary;
        }

        String thirdColumn = String.format("%.2f", numberB);
        while (thirdColumn.length() < 10){
            thirdColumn = " " + thirdColumn;
        }

        String fourthColumn = String.format("%.3f", numberC);
        while ( fourthColumn.length() < 10){
            fourthColumn = fourthColumn + " ";
        }

        System.out.printf("|%s|%s|%s|%s|",
                hex, binary, hex, hex);
    }
}
