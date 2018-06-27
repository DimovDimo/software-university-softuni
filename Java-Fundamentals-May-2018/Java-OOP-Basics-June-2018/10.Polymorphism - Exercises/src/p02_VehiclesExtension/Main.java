package p02_VehiclesExtension;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        DecimalFormat df = new DecimalFormat("#.##");

        Vehicle car = getCar(reader);
        Vehicle truck = getTruck(reader);
        Bus bus = getBus(reader);

        int countCommands = Integer.parseInt(reader.readLine());
        for (int i = 0; i < countCommands; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            String action = tokens[0];
            String vehicle = tokens[1];
            try {
                if ("Refuel".equals(action)){
                    double liters = Double.parseDouble(tokens[2]);
                    switch (vehicle){
                        case "Car":
                            car.refueled(liters);
                            break;
                        case "Truck":
                            truck.refueled(liters);
                            break;
                        case "Bus":
                            bus.refueled(liters);
                            break;
                        default:
                            break;
                    }
                } else if ("Drive".equals(action)){
                    double distance = Double.parseDouble(tokens[2]);
                    switch (vehicle){
                        case "Car":
                            car.driven(distance);
                            System.out.printf("Car travelled %s km%n", df.format(distance));
                            break;
                        case "Truck":
                            truck.driven(distance);
                            System.out.printf("Truck travelled %s km%n", df.format(distance));
                            break;
                        case "Bus":
                            bus.driven(distance);
                            System.out.printf("Bus travelled %s km%n", df.format(distance));
                            break;
                        default:
                            break;
                    }
                } else if("DriveEmpty".equals(action)){
                    double distance = Double.parseDouble(tokens[2]);
                    bus.drivenEmpty(distance);
                    System.out.printf("Bus travelled %s km%n", df.format(distance));
                }
            } catch (IllegalArgumentException ex){
                System.out.println(ex.getMessage());
            }
        }

        printFuels(car, truck, bus);
    }

    private static Bus getBus(BufferedReader reader) throws IOException {
        String[] busTokens = reader.readLine().split("\\s+");
        double buskFuel = Double.parseDouble(busTokens[1]);
        double busConsumation = Double.parseDouble(busTokens[2]);
        double busTank =  Double.parseDouble(busTokens[3]);
        return new Bus(buskFuel, busConsumation, busTank);
    }

    private static Vehicle getTruck(BufferedReader reader) throws IOException {
        String[] truckTokens = reader.readLine().split("\\s+");
        double truckFuel = Double.parseDouble(truckTokens[1]);
        double truckConsumation = Double.parseDouble(truckTokens[2]);
        double truckTank =  Double.parseDouble(truckTokens[3]);
        return new Truck(truckFuel, truckConsumation, truckTank);
    }

    private static Vehicle getCar(BufferedReader reader) throws IOException {
        String[] carTokens = reader.readLine().split("\\s+");
        double carFuel = Double.parseDouble(carTokens[1]);
        double carConsumation = Double.parseDouble(carTokens[2]);
        double carTank =  Double.parseDouble(carTokens[3]);
        return new Car(carFuel, carConsumation, carTank);
    }

    private static void printFuels(Vehicle car, Vehicle truck, Vehicle bus) {
        System.out.printf("Car: %.2f%n", car.getFuel());
        System.out.printf("Truck: %.2f%n", truck.getFuel());
        System.out.printf("Bus: %.2f%n", bus.getFuel());
    }
}
