package org.example.exception;

import org.example.model.ParkingLot;

import java.io.IOException;

public class ParkingLotException extends RuntimeException {

    public ParkingLotException(String msg) {
        super(msg);
    }
}
