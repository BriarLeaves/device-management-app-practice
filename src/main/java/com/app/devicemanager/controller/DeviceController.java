package com.app.devicemanager.controller;

import com.app.devicemanager.repos.entity.DeviceEntity;
import com.app.devicemanager.services.DeviceServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DeviceController {

    private final DeviceServices deviceServices;

    public DeviceController(DeviceServices deviceServices) {
        this.deviceServices = deviceServices;
    }

    @GetMapping
    public List<DeviceEntity> getAllDevices(){
        return deviceServices.getAllDevices();
    }

    @PostMapping
    public List<DeviceEntity> createNewDevice(@RequestBody DeviceEntity deviceEntity){
        return deviceServices.createNewDevice(deviceEntity);
    }
}
