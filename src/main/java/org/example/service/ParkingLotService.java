package org.example.service;

import org.example.exception.ParkingLotException;
import org.example.model.Car;
import org.example.model.ParkingLot;
import org.example.model.Slot;
import org.example.model.parking_strategy.ParkingStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingLotService {

    private ParkingLot parkingLot;
    private ParkingStrategy parkingStrategy;

    public void createParkingLot(final ParkingLot parkingLot, final ParkingStrategy parkingStrategy) throws IOException {
        if (this.parkingLot != null) {
            throw new ParkingLotException("Parking lot already exists");
        }
        this.parkingLot = parkingLot;
        this.parkingStrategy = parkingStrategy;
        for (int i = 1; i <= parkingLot.getCapacity(); i++) {
            parkingStrategy.addSlot(i);
        }
    }

    public Integer park(final Car car) throws IOException {
        validateParkingLotExists();
        final Integer nextFreeSlot = parkingStrategy.getNextSlot();
        parkingLot.park(car, nextFreeSlot);
        parkingStrategy.removeSlot(nextFreeSlot);
        return nextFreeSlot;
    }

    public void makeSlotFree(final Integer slotNumber) throws IOException {
        validateParkingLotExists();
        parkingLot.makeSlotFree(slotNumber);
        parkingStrategy.addSlot(slotNumber);
    }

    public List<Slot> getOccupiedSlots() throws IOException {
        validateParkingLotExists();
        final List<Slot> occupiedSlotsList = new ArrayList<>();
        final Map<Integer, Slot> allSlots = parkingLot.getSlots();

        for (int i = 1; i <= parkingLot.getCapacity(); i++) {
            if (allSlots.containsKey(i)) {
                final Slot slot = allSlots.get(i);
                if (!slot.isSlotFree()) {
                    occupiedSlotsList.add(slot);
                }
            }
        }
        return occupiedSlotsList;
    }

    public List<Slot> getSlotsWithCarColor(String color) throws IOException {
        List<Slot> occupiedSlots = getOccupiedSlots();
        return occupiedSlots.stream()
                .filter(slot -> slot.getParkedCar().getColor().equals(color))
                .toList();
    }

    private void validateParkingLotExists() throws ParkingLotException {
        if (this.parkingLot == null) {
            throw new ParkingLotException("Parking lot doesn't exist");
        }
    }
}
