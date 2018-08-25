package A01_DHARMASonarFence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DHARMASonarFence {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder numbers = new StringBuilder();
        while (true){
            String line = reader.readLine();
            if ("Reprogram".equals(line)){
                break;
            }

            String binaryString = Long.toBinaryString(
                    Long.parseLong(line));
//            String formatedBinaryString = ("00000000000000000000000000000000" + binaryString).substring(binaryString.length());
            numbers.append(
                    reprogram(binaryString))
                    .append(System.lineSeparator());
        }

        System.out.println(numbers.toString());
    }

    private static long reprogram(String input) {
        input += "2";
        StringBuilder reprogramOutput = new StringBuilder();
        for (int i = 1; i < input.length();) {
            String firstElement = String.valueOf(input.charAt(i - 1));
            String secondElement = String.valueOf(input.charAt(i));
            if (firstElement.equals("0") && secondElement.equals("0")){
                reprogramOutput.append("1");
            } else if (firstElement.equals("1") && secondElement.equals("1")) {
                reprogramOutput.append("0");
            } else {
                reprogramOutput.append(firstElement);
            }

            i++;
        }

//        System.out.println(input);
//        System.out.println(reprogramOutput.toString());
//        return reprogramOutput.toString();
        return Integer.parseInt(reprogramOutput.toString(), 2);
    }

//    private static String binaryformInt(int number) {
//        long remainder;
//
////        if (number <= 1) {
////            System.out.print(number);
////        }
//
//        remainder = number % 2;
//        binaryformInt(number >> 1);
//        return String.valueOf(remainder);
//    }
}
