package p06_RawData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int countCars = Integer.parseInt(reader.readLine());
        List<Car> cars = new LinkedList<>();
        for (int i = 0; i < countCars; i++) {
            readCar(reader, cars);
        }

        List<String> modelWihtCargoType = getModelWihtCargoType(reader, cars);
        printResult(modelWihtCargoType);
    }

    private static void printResult(List<String> modelWihtCargoType) {
        modelWihtCargoType.stream()
                .forEach(c -> System.out.printf("%s%n", c));
    }

    private static List<String> getModelWihtCargoType(BufferedReader reader, List<Car> cars) throws IOException {
        String cargoType = reader.readLine();
        List<String> modelWihtCargoType = new LinkedList<>();
        if("fragile".equals(cargoType)){
            cars.stream()
                    .filter(c ->
                        "fragile".equals(c.getCargoType()) && (c.getTire1Pressure() < 1 || c.getTire2Pressure() < 1 || c.getTire3Pressure() < 1 || c.getTire4Pressure() < 1))
                    .forEach(c -> modelWihtCargoType.add(c.getModel()));
        } else if("flamable".equals(cargoType)){
            cars.stream()
                    .filter(c ->
                            "flamable".equals(c.getCargoType()) && c.getEnginePower() > 250)
                    .forEach(c -> modelWihtCargoType.add(c.getModel()));
        }
        return modelWihtCargoType;
    }

    private static void readCar(BufferedReader reader, List<Car> cars) throws IOException {
        String[] tokens = reader.readLine().split("\\s+");
        String model = tokens[0];
        int engineSpeed = Integer.parseInt(tokens[1]);
        int enginePower = Integer.parseInt(tokens[2]);
        int cargoWeight = Integer.parseInt(tokens[3]);
        String cargoType = tokens[4];
        double tire1Pressure = Double.parseDouble(tokens[5]);
        int tire1Age = Integer.parseInt(tokens[6]);
        double tire2Pressure = Double.parseDouble(tokens[7]);
        int tire2Age = Integer.parseInt(tokens[8]);
        double tire3Pressure = Double.parseDouble(tokens[9]);
        int tire3Age = Integer.parseInt(tokens[10]);
        double tire4Pressure = Double.parseDouble(tokens[11]);
        int tire4Age = Integer.parseInt(tokens[12]);
        Car car = new Car(model, engineSpeed,enginePower, cargoWeight, cargoType, tire1Pressure, tire1Age, tire2Pressure,tire2Age, tire3Pressure, tire3Age, tire4Pressure, tire4Age);
        cars.add(car);
    }
}
