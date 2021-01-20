package com.example.parking.controller.advice;

import com.example.parking.exception.AllocationException;
import com.example.parking.exception.SlotException;
import com.example.parking.model.ErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ParkingControllerAdvice {
    private static final Logger LOGGER= LoggerFactory.getLogger(ParkingControllerAdvice.class);


    @ExceptionHandler(AllocationException.class)
    public ResponseEntity<ErrorInfo> allocationExceptionHandler(AllocationException allocationException) {
        LOGGER.error(allocationException.getMessage(),allocationException);
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage(allocationException.getMessage());
        return ResponseEntity.badRequest().body(errorInfo);
    }

    @ExceptionHandler(SlotException.class)
    public ResponseEntity<ErrorInfo> slotExceptionHandler(SlotException slotException) {
        LOGGER.error(slotException.getMessage());
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage(slotException.getMessage());
        return ResponseEntity.badRequest().body(errorInfo);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> ExceptionHandler(Exception exception){
        LOGGER.error(exception.getMessage());
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage("Some error occured, please contact admin.");
        return ResponseEntity.badRequest().body(errorInfo);
    }



}
