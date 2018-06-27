package p01_Vehicles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String[] carTokens = reader.readLine().split("\\s+");
        double carFuel = Double.parseDouble(carTokens[1]);
        double carConsumation = Double.parseDouble(carTokens[2]);
        Vehicle car = new Car(carFuel, carConsumation);

        String[] truckTokens = reader.readLine().split("\\s+");
        double truckFuel = Double.parseDouble(truckTokens[1]);
        double truckConsumation = Double.parseDouble(truckTokens[2]);
        Vehicle truck = new Truck(truckFuel, truckConsumation);

        actions(reader, car, truck);
        printResult(car, truck);
    }

    private static void printResult(Vehicle car, Vehicle truck) {
        System.out.printf("Car: %.2f%n", car.getFuel());
        System.out.printf("Truck: %.2f%n", truck.getFuel());
    }

    private static void actions(BufferedReader reader, Vehicle car, Vehicle truck) throws IOException {
        int commandsCount = Integer.parseInt(reader.readLine());
        for (int i = 0; i < commandsCount; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            String action = tokens[0];
            String vehicle = tokens[1];
            if ("Drive".equals(action)){
                String distance = tokens[2];
                if ("Car".equals(vehicle)){
                    driveVehicle(car, distance);
                } else if ("Truck".equals(vehicle)){
                    driveVehicle(truck, distance);
                }
            } else if ("Refuel".equals(action)){
                double liters = Double.parseDouble(tokens[2]);
                if ("Car".equals(vehicle)){
                    refuelVehicle(car, liters);
                } else if ("Truck".equals(vehicle)){
                    refuelVehicle(truck, liters);
                }
            }
        }
    }

    private static void refuelVehicle(Vehicle vehicle, double liters) {
        vehicle.refueled(liters);
    }

    private static void driveVehicle(Vehicle vehicle, String distance) {
        System.out.println(vehicle.driven(distance));
    }
}
