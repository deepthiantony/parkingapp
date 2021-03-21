package com.example.parking.exception;

public class ParkingApplicationException extends RuntimeException {

    public ParkingApplicationException() {
        super();
    }

    public ParkingApplicationException(String message) {
        super(message);
    }

    public ParkingApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
