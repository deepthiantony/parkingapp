package com.example.parking.controller.advice;

import com.example.parking.exception.ParkingApplicationException;
import com.example.parking.model.ErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ParkingControllerAdvice {
    private static final Logger LOGGER= LoggerFactory.getLogger(ParkingControllerAdvice.class);

    @ExceptionHandler(ParkingApplicationException.class)
    public ResponseEntity<ErrorInfo> slotExceptionHandler(ParkingApplicationException parkingApplicationException) {
        LOGGER.error(parkingApplicationException.getMessage(), parkingApplicationException);
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage(parkingApplicationException.getMessage());
        return ResponseEntity.badRequest().body(errorInfo);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> ExceptionHandler(Exception exception){
        LOGGER.error(exception.getMessage(), exception);
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage("Some error occured, please contact admin.");
        return ResponseEntity.badRequest().body(errorInfo);
    }



}
