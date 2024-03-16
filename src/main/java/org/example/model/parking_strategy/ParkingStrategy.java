package org.example.model.parking_strategy;

import org.example.exception.NoFreeSlotAvailableException;

public interface ParkingStrategy {
    public void addSlot(Integer slotNumber);

    public void removeSlot(Integer slotNumber);

    public Integer getNextSlot() throws NoFreeSlotAvailableException;
}
