package org.example.commands;

import org.example.OutputPrinter;
import org.example.model.Command;
import org.example.model.Slot;
import org.example.service.ParkingLotService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class SlotNumberForRegistrationNumberCommandExecutor extends CommandExecutor {

    public static final String COMMAND_NAME = "slot_number_for_registration_number";

    public SlotNumberForRegistrationNumberCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(final Command command) {
        return command.getParams().size() == 1;
    }

    @Override
    public void execute(Command command) throws IOException {
        final List<Slot> occupiedSlots = parkingLotService.getOccupiedSlots();
        final String regNumberToFind = command.getParams().get(0);
        final Optional<Slot> foundSlot = occupiedSlots.stream()
                .filter(slot -> slot.getParkedCar().getRegistrationNumber().equals(regNumberToFind))
                .findFirst();
        if (foundSlot.isPresent()) {
            outputPrinter.printWithNewLine(foundSlot.get().getSlotNumber().toString());
        } else {
            outputPrinter.notFound();
        }
    }
}
