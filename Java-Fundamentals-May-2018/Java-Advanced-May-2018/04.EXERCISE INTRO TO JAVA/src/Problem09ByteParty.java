import java.util.Scanner;

public class Problem09ByteParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countOfNumbers = Integer.parseInt(scanner.nextLine());
        int[] numbers = new int[countOfNumbers];
        for (int i = 0; i < countOfNumbers; i++) {
            int number = Integer.parseInt(scanner.nextLine());
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
            int position = Integer.parseInt(tokens[1]);

            for (int i = 0; i < numbers.length; i++) {
                switch (pattern){
                    case 1:
                        changeValueToOne(numbers, position, i);
                        break;
                    case 0:
                        changeValueToZero(numbers, position, i);
                        break;
                    case -1:
                        changeValueByPosition(numbers, position, i);
                        break;
                }
            }
        }

        for (int number : numbers) {
            System.out.println(number);
        }
    }

    private static void changeValueToZero(int[] numbers, int position, int i) {
        for (int j = 0; j < numbers.length; j++) {
            int a = numbers[i];
            int b = a >> position;
            int c = b&1;
            int d =~(1<<position);
            int result = a&d;
            numbers[i] = result;
        }
    }

    private static void changeValueToOne(int[] numbers, int position, int i) {
        for (int j = 0; j < numbers.length; j++) {
            int a = numbers[i];
            int b = a >> position;
            int c = b&1;
            int d = 1 << position;
            int result = a | d;
            numbers[i] = result;
        }
    }

    public static void changeValueByPosition(int[] numbers, int position, int i){
        int a = numbers[i];
        int b = a>>position;
        int c = b&1;
        if (c == 0){
            int d = 1 << position;
            int result = a | d;
            numbers[i] = result;
        } else {
            int d =~(1<<position);
            int result = a&d;
            numbers[i] = result;
        }
    }
}
