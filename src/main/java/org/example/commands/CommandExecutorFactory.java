package org.example.commands;

import org.example.OutputPrinter;
import org.example.exception.InvalidCommandException;
import org.example.model.Command;
import org.example.service.ParkingLotService;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutorFactory {

    private final Map<String, CommandExecutor> commandExecutorMap;

    public CommandExecutorFactory(ParkingLotService parkingLotService) {
        final OutputPrinter outputPrinter = new OutputPrinter();
        commandExecutorMap = new HashMap<>();
        commandExecutorMap.put(ExitCommandExecutor.COMMAND_NAME, new ExitCommandExecutor(parkingLotService, outputPrinter));
        commandExecutorMap.put(ParkCommandExecutor.COMMAND_NAME, new ParkCommandExecutor(parkingLotService, outputPrinter));
        commandExecutorMap.put(CreateParkingLotCommandExecutor.COMMAND_NAME, new CreateParkingLotCommandExecutor(parkingLotService, outputPrinter));
        commandExecutorMap.put(SlotNumberForRegistrationNumberCommandExecutor.COMMAND_NAME, new SlotNumberForRegistrationNumberCommandExecutor(parkingLotService, outputPrinter));
        commandExecutorMap.put(LeaveCommandExecutor.COMMAND_NAME, new LeaveCommandExecutor(parkingLotService, outputPrinter));
        commandExecutorMap.put(StatusCommandExecutor.COMMAND_NAME, new StatusCommandExecutor(parkingLotService, outputPrinter));
        commandExecutorMap.put(RegistrationNumbersForColorCommandExecutor.COMMAND_NAME, new RegistrationNumbersForColorCommandExecutor(parkingLotService, outputPrinter));
        commandExecutorMap.put(SlotNumbersForColorCommandExecutor.COMMAND_NAME, new SlotNumbersForColorCommandExecutor(parkingLotService, outputPrinter));
    }

    public CommandExecutor getCommandExecutor(final Command command) throws InvalidCommandException {
        final CommandExecutor commandExecutor = commandExecutorMap.get(command.getCommandName());
        if (commandExecutor == null) {
            throw new InvalidCommandException();
        }
        return commandExecutor;
    }
}
