import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Problem12VehiclePark {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");
        List<String> vehiclePark = new ArrayList<>();
        Collections.addAll(vehiclePark, input);
        int vehiclesSold = 0;
        while (true){
            String line = scanner.nextLine();
            if (line.equals("End of customers!")){
                break;
            }

            String[] order = line.split("\\s+");
            String model = order[0];
            int countSeats = Integer.parseInt(order[2]);
            String orderString = getOrderString(model, countSeats);
            vehiclesSold = orderAnswer(vehiclePark, vehiclesSold, countSeats, orderString);
        }

        System.out.printf("Vehicles left: %s%n", String.join(", ", vehiclePark));
        System.out.printf("Vehicles sold: %d", vehiclesSold);
    }

    private static int orderAnswer(List<String> vehiclePark, int vehiclesSold, int countSeats, String orderString) {
        if (vehiclePark.contains(orderString)){
            vehiclesSold++;
            int price = orderString.charAt(0) * countSeats;
            System.out.printf("Yes, sold for %d$%n", price);
            vehiclePark.remove(orderString);
        } else {
            System.out.printf("No%n");
        }
        return vehiclesSold;
    }

    private static String getOrderString(String model, int countSeats) {
        String firstOrderLetter = "";
        switch (model){
            case "Car":
                firstOrderLetter = "c";
                break;
            case "Bus":
                firstOrderLetter = "b";
                break;
            case "Van":
                firstOrderLetter = "v";
                break;
            default:
                break;
        }

        return firstOrderLetter + countSeats;
    }
}
