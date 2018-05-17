package IIConditionalStatementsAndLoops;

import java.math.BigInteger;
import java.util.Scanner;

public class Problem08ProductOfNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer startNum = scanner.nextInt();
        Integer endNum = scanner.nextInt();
        BigInteger product = new BigInteger("1");

        for (int i = startNum; i <= endNum; i++) {
            product = product.multiply(
                    new BigInteger("" + i)
            );
        }

        System.out.printf("product[%d..%d] = %s",
                startNum, endNum, product);
    }
}
