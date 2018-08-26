package C03_ByteParty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ByteParty {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int countNumbers = Integer.parseInt(reader.readLine());
        int[] numbers = new int[countNumbers];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(reader.readLine());
        }

//        for (int number : numbers) {
//            System.out.println(Integer.toBinaryString(number));
//        }
//
//        System.out.println("-----------------");
        
        while (true){
            String line = reader.readLine();
            if ("party over".equals(line)){
                break;
            }
            
            String[] tokens = line.split(" ");
            int command = Integer.parseInt(tokens[0]);
            int position = Integer.parseInt(tokens[1]);

            if (command == -1){
                for (int i = 0; i < numbers.length; i++) {
                    int target = (numbers[i] >> position) & 1;
                    if (target == 1){
                        int mask = ~(1 << position);
                        numbers[i] = numbers[i] & mask;
                    } else {
                        int mask = 1 << position;
                        numbers[i] = numbers[i] | mask;
                    }
                }
            } else if (command == 0) {
                for (int i = 0; i < tokens.length; i++) {
                    int target = (numbers[i] >> position) & 1;
                    if (target == 1){
                        int mask = ~(1 << position);
                        numbers[i] = numbers[i] & mask;
                    }
                }
            } else {
                for (int i = 0; i < tokens.length; i++) {
                    int target = (numbers[i] >> position) & 1;
                    if (target == 0){
                        int mask = 1 << position;
                        numbers[i] = numbers[i] | mask;
                    }
                }
            }

//            for (int number : numbers) {
//                System.out.println(Integer.toBinaryString(number));
//            }
//
//            System.out.println("-----------------");
        }

        for (int number : numbers) {
            System.out.println(number);
        }
    }
}
