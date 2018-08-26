package A01_ExtractBitFromInteger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExtractBitFromInteger {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] tokens = reader.readLine().split(" ");

        int number = Integer.parseInt(tokens[0]);
        int position = Integer.parseInt(tokens[1]);

        int shift = number >> position;
        int bit = shift & 1;

        System.out.println(bit);
    }
}
