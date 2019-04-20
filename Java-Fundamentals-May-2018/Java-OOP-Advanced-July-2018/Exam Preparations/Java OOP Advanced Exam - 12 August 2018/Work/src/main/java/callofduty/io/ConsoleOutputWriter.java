package callofduty.io;

import callofduty.interfaces.OutputWriter;

public class ConsoleOutputWriter implements OutputWriter {

//    @Override
//    public void writeLine(String output){
//        System.out.println(output);
//    }
//
//    @Override
//    public void writeLine(String format, Object... params) {
//        System.out.println(String.format(format, params));
//    }

    @Override
    public void print(String output) {
        System.out.print(output);
    }

    @Override
    public void println(String output) {
        System.out.println(output);
    }
}
