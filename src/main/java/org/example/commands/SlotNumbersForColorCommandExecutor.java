package org.example.commands;

import org.example.OutputPrinter;
import org.example.model.Command;
import org.example.model.Slot;
import org.example.service.ParkingLotService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SlotNumbersForColorCommandExecutor extends CommandExecutor {

    public static final String COMMAND_NAME = "slot_numbers_for_cars_with_colour";

    public SlotNumbersForColorCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(final Command command) {
        return command.getParams().size() == 1;
    }

    @Override
    public void execute(Command command) throws IOException {
        final List<Slot> occupiedSlots = parkingLotService.getOccupiedSlots();
        final String colorToFind = command.getParams().get(0);
        String slotNumbersForCarsWithColor = occupiedSlots.stream()
                .filter(slot -> slot.getParkedCar().getColor().equals(colorToFind))
                .map(slot -> String.valueOf(slot.getSlotNumber()))
                .collect(Collectors.joining(", "));
        outputPrinter.printWithNewLine(slotNumbersForCarsWithColor);
    }
}
