package org.example.commands;

import org.example.OutputPrinter;
import org.example.model.Command;
import org.example.model.parking_strategy.ParkingStrategy;
import org.example.service.ParkingLotService;

import java.io.IOException;

public abstract class CommandExecutor {

    protected ParkingLotService parkingLotService;
    protected OutputPrinter outputPrinter;

    public CommandExecutor(final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
        this.parkingLotService = parkingLotService;
        this.outputPrinter = outputPrinter;
    }

    public abstract boolean validate(final Command command);

    public abstract void execute(final Command command) throws IOException;
}
