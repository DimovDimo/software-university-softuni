package net.java.main.io;

import net.java.main.interfaces.OutputWriter;

import java.io.IOException;

public class ConsoleWriter implements OutputWriter {

    @Override
    public void writeLine(String line) throws IOException {
        System.out.println(line);
    }
}
