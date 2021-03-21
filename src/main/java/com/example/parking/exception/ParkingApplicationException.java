package com.example.parking.exception;

import com.example.parking.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ParkingApplicationException extends RuntimeException {
    @Getter
    ErrorCode errorCode;

    public ParkingApplicationException() {
        super();
    }

    public ParkingApplicationException(String message) {
        super(message);
    }

    public ParkingApplicationException(ErrorCode code, String message) {
        super(message);
        this.errorCode = code;
    }

    public ParkingApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParkingApplicationException(ErrorCode code, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = code;
    }
}
