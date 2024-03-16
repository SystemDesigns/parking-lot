package org.example.mode;

import org.example.OutputPrinter;
import org.example.commands.CommandExecutorFactory;
import org.example.model.Command;

import java.io.*;

public class FileMode extends Mode {

    private final String fileName;


    public FileMode(CommandExecutorFactory commandExecutorFactory, OutputPrinter outputPrinter, String fileName) {
        super(commandExecutorFactory, outputPrinter);
        this.fileName = fileName;
    }

    public void process() throws IOException {
        final File file = new File(fileName);
        final BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            this.outputPrinter.invalidFile();
            return;
        }

        String input = reader.readLine();
        while (input != null) {
            final Command command = new Command(input);
            processCommand(command);
            input = reader.readLine();
        }
    }
}
