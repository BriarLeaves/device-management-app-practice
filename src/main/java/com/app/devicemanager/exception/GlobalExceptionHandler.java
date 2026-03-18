package com.app.devicemanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String DEVICE_NOT_FOUND = "device-not-found";

    @ExceptionHandler(DeviceNotFoundException.class)
    public ProblemDetail handleDeviceNotFoundException(DeviceNotFoundException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setType(URI.create(DEVICE_NOT_FOUND));
        problemDetail.setTitle("Device Not Found");
        return problemDetail;
    }
}
