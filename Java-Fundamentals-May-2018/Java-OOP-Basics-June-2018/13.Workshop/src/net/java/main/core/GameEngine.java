package net.java.main.core;

import net.java.main.dispachers.CommandDispatcher;
import net.java.main.exceptions.GameException;
import net.java.main.interfaces.Engine;
import net.java.main.interfaces.InputReader;
import net.java.main.interfaces.OutputWriter;
import net.java.main.io.ConsoleWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameEngine implements Engine {

    private InputReader consoleReader;
    private OutputWriter consoleWriter;
    private CommandDispatcher commandDispatcher;
    private boolean isStarted;

    public GameEngine(
            InputReader consoleReader,
            OutputWriter consoleWriter,
            CommandDispatcher commandDispatcher) {
        this.consoleWriter = consoleWriter;
        this.consoleReader = consoleReader;
        this.commandDispatcher = commandDispatcher;
    }

    public void start() throws IOException {
        this.isStarted = true;
        while (this.isStarted) {
            try {
                String userInput = this.consoleReader.readLine();
                String[] args = userInput.split("\\s+");

                String result = this.commandDispatcher.dispatchCommand(args);

                this.consoleWriter.writeLine(result);

                if (result.equals("Game over!")) {
                    this.isStarted = false;
                }
            } catch (GameException | IOException e) {
                this.consoleWriter.writeLine(e.getMessage());
            }
        }
    }
}
