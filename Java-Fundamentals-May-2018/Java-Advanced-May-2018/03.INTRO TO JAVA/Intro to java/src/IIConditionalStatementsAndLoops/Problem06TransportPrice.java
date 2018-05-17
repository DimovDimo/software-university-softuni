package IIConditionalStatementsAndLoops;

import java.util.Scanner;

public class Problem06TransportPrice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer kilometers = scanner.nextInt();
        scanner.nextLine();
        String time = scanner.nextLine();
        double price = 0;

        if (kilometers < 20){
            price = 0.7;
            if ("day".equals(time)){
                price += (kilometers * 0.79);
            } else {
                price += (kilometers * 0.9);
            }
        } else if (kilometers < 100){
            price = kilometers * 0.09;
        } else {
            price = kilometers *0.06;
        }

        System.out.printf("%.2f", price);
    }
}
