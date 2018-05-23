import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Problem01ParkingLot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<String> parkingLot = new HashSet<>();
        while (true){
            String input = scanner.nextLine();
            if (input.equals("END")){
                break;
            } else {
                String[] reminder = input.split(", ");
                if ("IN".equals(reminder[0])){
                    parkingLot.add(reminder[1]);
                } else {
                    parkingLot.remove(reminder[1]);
                }
            }
        }

        if (parkingLot.isEmpty()){
            System.out.println("Parking Lot is Empty");
        } else {
            for (String car : parkingLot) {
                System.out.println(car);
            }
        }
    }
}
