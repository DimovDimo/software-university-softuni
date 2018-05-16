package IOAndDataTypes;

import java.math.BigDecimal;
import java.util.Scanner;

public class Problem04EuroTrip {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double quantity = scanner.nextDouble();
        double quantityToLevs = quantity * 1.2;
        BigDecimal levs = new BigDecimal(quantityToLevs);
        BigDecimal levsToDeutscheMarks = new BigDecimal("4210500000000");
        BigDecimal deutscheMarks = levs.multiply(levsToDeutscheMarks);
        System.out.printf("%.2f marks", deutscheMarks);
    }
}
