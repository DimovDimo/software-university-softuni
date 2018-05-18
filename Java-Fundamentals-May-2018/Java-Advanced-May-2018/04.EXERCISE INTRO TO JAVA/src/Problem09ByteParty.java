import java.util.Scanner;

public class Problem09ByteParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countOfNumbers = scanner.nextInt();
        int[] numbers = new int[countOfNumbers];
        for (int i = 0; i < countOfNumbers; i++) {
            int number = scanner.nextInt();
            numbers[i] = number;
        }

        String line;
        while (true){
            line = scanner.nextLine();
            if ("party over".equals(line)) {
                break;
            }

            String[] tokens = line.split("\\s+");
            int pattern = Integer.parseInt(tokens[0]);
            int position =
        }
    }
}
