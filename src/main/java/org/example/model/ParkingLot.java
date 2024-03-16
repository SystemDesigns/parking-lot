package org.example.model;

import org.example.exception.InvalidSlotException;
import org.example.exception.ParkingLotException;
import org.example.exception.SlotAlreadyOccupiedException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static final int MAX_CAPACITY = 1000000;
    private final int capacity;

    private final Map<Integer, Slot> slots;

    public ParkingLot(final int capacity) throws IOException {
        if (capacity > MAX_CAPACITY || capacity <= 0) {
            throw new ParkingLotException("Invalid capacity given for parking lot.");
        }
        this.capacity = capacity;
        this.slots = new HashMap<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public Map<Integer, Slot> getSlots() {
        return slots;
    }

    private Slot getSlot(final Integer slotNumber) throws IOException {
        if (slotNumber > getCapacity() || slotNumber <= 0) {
            throw new InvalidSlotException();
        }
        final Map<Integer, Slot> allSlots = getSlots();
        if (!allSlots.containsKey(slotNumber)) {
            allSlots.put(slotNumber, new Slot(slotNumber));
        }
        return allSlots.get(slotNumber);
    }

    public Slot park(final Car car, final Integer slotNumber) throws IOException {
        final Slot slot = getSlot(slotNumber);
        if (!slot.isSlotFree()) {
            throw new SlotAlreadyOccupiedException();
        }
        slot.assignCar(car);
        return slot;
    }

    public Slot makeSlotFree(final Integer slotNumber) throws IOException {
        final Slot slot = getSlot(slotNumber);
        slot.unAssignCar();
        return slot;
    }

}
