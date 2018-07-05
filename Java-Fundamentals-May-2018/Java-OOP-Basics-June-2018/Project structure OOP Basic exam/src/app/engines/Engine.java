package app.engines;

import app.io.ConsoleInputReader;
import app.io.ConsoleOutputWriter;
import app.utilities.InputParser;

import java.util.List;

public class Engine {
    private ConsoleInputReader inputReader;
    private ConsoleOutputWriter outputWriter;
    private InputParser inputParser;
//    private Manager manager;

    public Engine(ConsoleInputReader inputReader, ConsoleOutputWriter outputWriter, InputParser inputParser, Manager manager) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.inputParser = inputParser;
        this.manager = manager;
    }

    public void run(){
        String inputLine;

        while (true){
            inputLine = this.inputReader.readLine();
            List<String> commandParams = this.inputParser.parseInput(inputLine);

            this.dispachCommand(commandParams);
            //INPUT_TERMINATING_COMMAND - this is your terminared command
            if (inputLine.equals("INPUT_TERMINATING_COMMAND")){
                break;
            }
        }
    }

    private void dispachCommand(List<String> commandParams) {
        String command = commandParams.remove(0);

        switch (command){

        }
    }
}
