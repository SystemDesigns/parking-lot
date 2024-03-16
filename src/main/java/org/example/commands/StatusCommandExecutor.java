package org.example.commands;

import org.example.OutputPrinter;
import org.example.model.Command;
import org.example.model.Slot;
import org.example.service.ParkingLotService;

import java.io.IOException;
import java.util.List;

public class StatusCommandExecutor extends CommandExecutor{

    public static final String COMMAND_NAME = "status";

    public StatusCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        return command.getParams().isEmpty();
    }

    @Override
    public void execute(Command command) throws IOException {
        List<Slot> slots = parkingLotService.getOccupiedSlots();
        if (!slots.isEmpty()) {
            outputPrinter.printWithNewLine("Slot Number\t\tRegistration Number\t\tColor");
            for (Slot slot : slots) {
                outputPrinter.printWithNewLine(slot.getSlotNumber() + "\t\t" + slot.getParkedCar().getRegistrationNumber() + "\t\t" + slot.getParkedCar().getColor());
            }
        }
    }
}
