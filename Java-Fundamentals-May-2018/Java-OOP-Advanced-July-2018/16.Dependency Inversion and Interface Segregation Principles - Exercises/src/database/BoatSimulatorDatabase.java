package database;

import contracts.Modelable;
import contracts.Repository;
import models.boats.MotorBoat;

public class BoatSimulatorDatabase {
    Repository<MotorBoat> boats;
    Repository<Modelable> engines;

    @SuppressWarnings("unchecked")
    public BoatSimulatorDatabase() {
        this.boats = new RepositoryImpl();
        this.engines = new RepositoryImpl();
    }

    public Repository<MotorBoat> getBoats() {
        return this.boats;
    }

//    private void setBoats(RepositoryImpl<MotorBoat> boats) {
//        this.boats = boats;
//    }

    public Repository<Modelable> getEngines() {
        return this.engines;
    }

//    private void setEngines(contracts.RepositoryImpl<Modelable> engines) {
//        this.engines = engines;
//    }
}
