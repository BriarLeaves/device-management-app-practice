package com.app.devicemanager.exception;

public class DeviceInUseException extends RuntimeException {

    public static final String DEVICE_IN_USE = "Device with ID '%s' is currently in-use.";

    public DeviceInUseException(String deviceId) {
        super(String.format(DEVICE_IN_USE, deviceId));
    }

}
