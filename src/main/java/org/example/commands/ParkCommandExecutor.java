package org.example.commands;

import org.example.OutputPrinter;
import org.example.exception.NoFreeSlotAvailableException;
import org.example.model.Car;
import org.example.model.Command;
import org.example.service.ParkingLotService;

import java.io.IOException;

public class ParkCommandExecutor extends CommandExecutor{

    public static final String COMMAND_NAME = "park";

    public ParkCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        return command.getParams().size() == 2;
    }

    @Override
    public void execute(Command command) throws IOException {
        final Car car = new Car(command.getParams().get(0), command.getParams().get(1));
        try {
            final Integer slot = parkingLotService.park(car);
            outputPrinter.printWithNewLine("Allocated slot number: " + slot);
        } catch (NoFreeSlotAvailableException exception) {
            outputPrinter.parkingLotFull();
        }
    }
}
