package app.engines;

import app.core.HealthManager;
import app.io.ConsoleInputReader;
import app.io.ConsoleOutputWriter;
import app.utilities.InputParser;

import java.util.List;

public class Engine {
    private ConsoleInputReader inputReader;
    private ConsoleOutputWriter outputWriter;
    private InputParser inputParser;
    private HealthManager healthManager;

    public Engine(ConsoleInputReader inputReader, ConsoleOutputWriter outputWriter, InputParser inputParser, HealthManager healthManager) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.inputParser = inputParser;
        this.healthManager = healthManager;
    }

    public void run(){
        String inputLine;

        while (true){
            inputLine = this.inputReader.readLine();
            List<String> commandParams = this.inputParser.parseInput(inputLine);

            this.dispachCommand(commandParams);
            //INPUT_TERMINATING_COMMAND - this is your terminared command
            if (inputLine.equals("BEER IS COMING")){
                break;
            }
        }
    }

    private void dispachCommand(List<String> commandParams) {
        String command = commandParams.get(0);

        String organismName;
        String result = null;
        switch (command){
            case "checkCondition":
                organismName = commandParams.get(1);
                result = this.healthManager.checkCondition(organismName);
                break;
            case "createOrganism":
                organismName = commandParams.get(1);
                result = this.healthManager.createOrganism(organismName);
                break;
            case "addCluster":
                organismName = commandParams.get(1);
                String id = commandParams.get(2);
                int rows = Integer.parseInt(commandParams.get(3));
                int cols = Integer.parseInt(commandParams.get(4));
                result = this.healthManager.addCluster(organismName, id, rows, cols);
                break;
            case "addCell":
                organismName = commandParams.get(1);
                String clusterId = commandParams.get(2);
                String cellType = commandParams.get(3);
                String cellId = commandParams.get(4);
                int health = Integer.parseInt(commandParams.get(5));
                int positionRow = Integer.parseInt(commandParams.get(6));
                int positionCol = Integer.parseInt(commandParams.get(7));
                int additionalProperty = Integer.parseInt(commandParams.get(8));
                result = this.healthManager.addCell(organismName, clusterId, cellType, cellId, health, positionRow, positionCol, additionalProperty);
                break;
            case "activateCluster":
                organismName = commandParams.get(1);
                result = this.healthManager.activateCluster(organismName);
                break;
        }

        if (result != null){
            outputWriter.writeLine(result);
        }
    }
}
