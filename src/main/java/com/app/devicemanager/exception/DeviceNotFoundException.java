package com.app.devicemanager.exception;

public class DeviceNotFoundException extends RuntimeException {

    public static final String DEVICE_NOT_FOUND = "Device with ID '%s' not found.";

    public DeviceNotFoundException(String deviceId) {
        super(String.format(DEVICE_NOT_FOUND, deviceId));
    }

}
