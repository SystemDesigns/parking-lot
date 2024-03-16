package org.example.commands;

import org.example.OutputPrinter;
import org.example.model.Command;
import org.example.service.ParkingLotService;
import org.example.validator.IntegerValidator;

import java.io.IOException;
import java.util.List;

public class LeaveCommandExecutor extends CommandExecutor {

    public static final String COMMAND_NAME = "leave";

    public LeaveCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        final List<String> params = command.getParams();
        if (params.size() != 1) return false;
        return IntegerValidator.isInteger(params.get(0));
    }

    @Override
    public void execute(Command command) throws IOException {
        final int slotNumber = Integer.parseInt(command.getParams().get(0));
        parkingLotService.makeSlotFree(slotNumber);
        outputPrinter.printWithNewLine("Slot number: " + slotNumber + " is freed.");
    }
}
