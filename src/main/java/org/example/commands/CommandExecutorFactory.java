package org.example.commands;

import org.example.model.Command;
import org.example.service.ParkingLotService;

public class CommandExecutorFactory {

    public CommandExecutorFactory(ParkingLotService parkingLotService) {

    }

    public CommandExecutor getCommandExecutor(Command command) {
        return null;
    }
}
