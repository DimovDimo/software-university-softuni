package B02_ModifyABit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ModifyABit {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] tokens = reader.readLine().split(" ");

        int number = Integer.parseInt(tokens[0]);
        int position = Integer.parseInt(tokens[1]);
        int value = Integer.parseInt(tokens[2]);

        int mask = 1 << position;
        int modifiedNumber = 0;
        if (value == 1){
            modifiedNumber = number | mask;
        } else {
            modifiedNumber = number & (~mask);
        }

//        System.out.println("number: " + Integer.toBinaryString(number));
//        System.out.println("mask: " + Integer.toBinaryString(mask) + " " + mask);
//        System.out.println("modifiedNumber: " + Integer.toBinaryString(modifiedNumber));
        System.out.println(modifiedNumber);
    }
}
