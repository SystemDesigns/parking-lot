package org.example;

import org.example.commands.CommandExecutorFactory;
import org.example.exception.InvalidModeException;
import org.example.mode.FileMode;
import org.example.mode.InteractiveMode;
import org.example.service.ParkingLotService;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws IOException {
        final OutputPrinter outputPrinter = new OutputPrinter();
        final ParkingLotService parkingLotService = new ParkingLotService();

        final CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(parkingLotService);

        if (isInteractiveMode(args)) {
            new InteractiveMode(commandExecutorFactory, outputPrinter).process();
        } else if (isFileMode(args)) {
            new FileMode(commandExecutorFactory, outputPrinter, args[1]).process();
        } else {
            throw new InvalidModeException();
        }
    }

    private static boolean isInteractiveMode(String[] args) {
        return args.length == 0;
    }

    private static boolean isFileMode(String[] args) {
        return args.length == 0;
    }
}