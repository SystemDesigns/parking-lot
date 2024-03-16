package org.example.commands;

import org.example.OutputPrinter;
import org.example.model.Command;
import org.example.model.ParkingLot;
import org.example.model.parking_strategy.NaturalOrderingParkingStrategy;
import org.example.service.ParkingLotService;
import org.example.validator.IntegerValidator;

import java.io.IOException;
import java.util.List;

public class CreateParkingLotCommandExecutor extends CommandExecutor {
    public static final String COMMAND_NAME = "create_parking_lot";

    public CreateParkingLotCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(final Command command) {
        final List<String> params = command.getParams();
        if (params.size() != 1) return false;
        return IntegerValidator.isInteger(params.get(0));
    }

    @Override
    public void execute(final Command command) throws IOException {
        final int parkingLotCapacity = Integer.parseInt(command.getParams().get(0));
        final ParkingLot parkingLot = new ParkingLot(parkingLotCapacity);
        parkingLotService.createParkingLot(parkingLot, new NaturalOrderingParkingStrategy());
        outputPrinter.printWithNewLine("Created a parking lot with " + parkingLot.getCapacity() + " slots");
    }
}
