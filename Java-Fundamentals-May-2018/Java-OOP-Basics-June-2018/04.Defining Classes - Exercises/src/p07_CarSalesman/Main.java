
import p07_CarSalesman.Car;
import p07_CarSalesman.Engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int countEngines = Integer.parseInt(reader.readLine());
        List<Engine> engines = new LinkedList<>();
        for (int i = 0; i < countEngines; i++) {
            readEngine(reader, engines);
        }

        int countCars = Integer.parseInt(reader.readLine());
        List<Car> cars = new LinkedList<>();
        for (int i = 0; i < countCars; i++) {
            readCar(reader, cars);
        }

        for (Car car : cars) {
            Engine engine = findFirst(car.getEngine(), engines);
            if (engine.getModel().equals("emptyEngine")){
                continue;
            } else {
                System.out.print(car.toStringModelAndEngine());
                System.out.print(engine.toString());
                System.out.print(car.toStringWeightAndColor());
            }
        }
    }

    private static Engine findFirst(String engineName, List<Engine> engines) {
        for (Engine engine : engines) {
            if (engine.getModel().equals(engineName)){
                return engine;
            }
        }

        Engine emptyEngine = new Engine("emptyEngine", "emptyEngine");
        return emptyEngine;
    }

    private static void readCar(BufferedReader reader, List<Car> cars) throws IOException {
        String[] tokens = reader.readLine().split("\\s+");
        String model = tokens[0];
        String engine = tokens[1];
        Car car = new Car(model, engine);

        try {
            int weight = Integer.parseInt(tokens[2]);
            car.setWeight(weight + "");
            try {
                String color = tokens[3];
                car.setColor(color);
            }catch (Exception e){

            }

        } catch (Exception e){
            try {
                String color = tokens[2];
                car.setColor(color);
            } catch (Exception e2){

            }
        }

        cars.add(car);
    }

    private static void readEngine(BufferedReader reader, List<Engine> engines) throws IOException {
        String[] tokens = reader.readLine().split("\\s+");
        String model = tokens[0];
        String power = tokens[1];
        Engine engine = new Engine(model, power);

        try {
            int displacement = Integer.parseInt(tokens[2]);
            engine.setDisplacement(displacement + "");
            try {
                String efficiency = tokens[3];
                engine.setEfficiency(efficiency);
            }catch (Exception e){

            }

        } catch (Exception e){
            try {
                String efficiency = tokens[2];
                engine.setEfficiency(efficiency);
            } catch (Exception e2){

            }
        }

        engines.add(engine);
    }
}
