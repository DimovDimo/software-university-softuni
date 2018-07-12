package app.engines;

import app.entities.Colonist.Colonist;
import app.entities.Colonist.Engineer.HardwareEngineer;
import app.entities.Colonist.Engineer.SoftwareEngineer;
import app.entities.Colonist.Medic.GeneralPractitioner;
import app.entities.Colonist.Medic.Surgeon;
import app.entities.Colonist.Soldier.Soldier;
import app.entities.Colony.Colony;
import app.io.ConsoleInputReader;
import app.io.ConsoleOutputWriter;
import app.utilities.InputParser;

import java.util.List;

public class Engine {
    private ConsoleInputReader inputReader;
    private ConsoleOutputWriter outputWriter;
    private InputParser inputParser;
    private Colony colony;

    public Engine(ConsoleInputReader inputReader, ConsoleOutputWriter outputWriter, InputParser inputParser, Colony colony) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.inputParser = inputParser;
        this.colony = colony;
    }

    public void run(){
        String inputLine;

        while (true){
            inputLine = this.inputReader.readLine();
            List<String> commandParams = this.inputParser.parseInput(inputLine);

            this.dispachCommand(commandParams);

            if (inputLine.equals("end")){
                break;
            }
        }
    }

    private void dispachCommand(List<String> commandParams) {
        String command = commandParams.get(0);
        String result = null;

        String familyId;
        String colonistId;
        int years;

        switch (command){
            case "insert":
                String classCreate = commandParams.get(1);
                colonistId = commandParams.get(2);
                familyId = commandParams.get(3);
                int talent = Integer.parseInt(commandParams.get(4));
                int age = Integer.parseInt(commandParams.get(5));
                String sign = "";
                if (classCreate.equals("GeneralPractitioner") || classCreate.equals("Surgeon")){
                    sign = commandParams.get(6);
                }

                Colonist colonist = null;
                switch (classCreate){
                    case "SoftwareEngineer":
                        colonist = new SoftwareEngineer(colonistId, familyId, talent, age);
                        break;
                    case "HardwareEngineer":
                        colonist = new HardwareEngineer(colonistId, familyId, talent, age);
                        break;
                    case "Soldier":
                        colonist = new Soldier(colonistId, familyId, talent, age);
                        break;
                    case "GeneralPractitioner":
                        colonist = new GeneralPractitioner(colonistId, familyId, talent, age, sign);
                        break;
                    case "Surgeon":
                        colonist = new Surgeon(colonistId, familyId, talent, age, sign);
                        break;
                }

                if (colonist != null){
                    try {
                        this.colony.addColonist(colonist);
                    } catch (IndexOutOfBoundsException error){
                        result = error.getMessage();
                    }
                }

                break;

            case "remove":
                String modificator = commandParams.get(1);
                familyId = commandParams.get(2);
                if (modificator.equals("family")){
                    this.colony.removeFamily(familyId);
                } else {
                    colonistId = commandParams.get(3);
                    this.colony.removeColonist(familyId, colonistId);
                }

                break;

            case "grow":
                years = Integer.parseInt(commandParams.get(1));
                this.colony.grow(years);
                break;

            case "potential":
                result = String.format("potential: %d", this.colony.getPotential());
                break;

            case "capacity":
                result = this.colony.getCapacity();
                break;

            case "family":
                familyId = commandParams.get(1);
                result = this.colony.family(familyId);
                break;
        }

        if (result != null){
            this.outputWriter.writeLine(result);
        }
    }
}
