package app.entities.rices;

import app.entities.cars.Car;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Race {

    private int lenght;
    private String route;
    private int prizePool;
    private Collection<Car> participants;

    public Race(int lenght, String route, int prizePool) {
        this.lenght = lenght;
        this.route = route;
        this.prizePool = prizePool;
        this.participants = new ArrayList<>();
    }

    public Collection<Car> getParticipants() {
        return Collections.unmodifiableCollection(this.participants);
    }

    public int getPreformancePoint(String raceType, Car car){

        switch (raceType){
            case "CasualRace":
                return car.getOverallPerformance();
            case "DragRace":
                return car.getEnginePerformance();
            case "DriftRace":
                return car.getSuspensionPerformance();
            default:
                return 0;
        }
    }

    public List<Car> getWinners(){
        return this.participants.stream()
                .sorted((car1, car2) -> {
                    String raceType = this.getClass().getSimpleName();
                    return this.getPreformancePoint(raceType, car2) - this.getPreformancePoint(raceType, car1);
                })
                .limit(3)
                .collect(Collectors.toList());
    }


    public int getWinnerMoney(int place){
        switch (place){
            case 1:
                return this.getFirstPlaceMoney();
            case 2:
                return this.getSecondPlaceMoney();
            case 3:
                return this.getThirdPlaceMoney();
            default:
                return 0;
        }
    }

    public int getFirstPlaceMoney(){
        return (this.prizePool * 50) / 100;
    }

    public int getSecondPlaceMoney(){
        return (this.prizePool * 30) / 100;
    }

    public int getThirdPlaceMoney(){
        return (this.prizePool * 20) / 100;
    }

    public void addParticipan(Car car){

        this.participants.add(car);
    }

    public boolean hasCar(Car car) {
        return this.participants.contains(car);
    }

    public boolean hasParticipants() {
        return !this.participants.isEmpty();
    }

    @Override
    public String toString() {
        Collection<Car> winners = this.getWinners();
        String raceType = this.getClass().getSimpleName();
        StringBuilder race = new StringBuilder()
                .append(String.format("%s - %d", this.route, this.lenght));

        for (int i = 0; i < winners.size(); i++) {
            Car winner = ((List<Car>) winners).get(i);
            race.append(System.lineSeparator())
                    .append(String.format("%d. %s %s %dPP - $%d", i + 1, winner.getBrand(), winner.getModel(), this.getPreformancePoint(raceType, winner), this.getWinnerMoney(i + 1)));
        }

        return race.toString();
    }
}
