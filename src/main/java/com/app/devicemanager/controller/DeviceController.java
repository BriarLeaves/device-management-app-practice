package com.app.devicemanager.controller;

import com.app.devicemanager.dto.DeviceDTO;
import com.app.devicemanager.repos.entity.DeviceEntity;
import com.app.devicemanager.services.DeviceServices;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<DeviceDTO>> getAllDevices(){
        return ResponseEntity.ok(deviceServices.getAllDevices());
    }

    @PostMapping
    public ResponseEntity<List<DeviceDTO>> createNewDevice(@RequestBody DeviceEntity deviceEntity){
        return ResponseEntity.ok(deviceServices.createNewDevice(deviceEntity));
    }

    @GetMapping("device")
    public ResponseEntity<DeviceDTO> getDeviceById(@RequestParam String id){
        return ResponseEntity.ok(deviceServices.getDeviceById(id));
    }

    @GetMapping("{id}/delete")
    public ResponseEntity<List<DeviceDTO>> deleteDeviceById(@PathVariable String id){
        return ResponseEntity.ok(deviceServices.deleteDeviceById(id));
    }

    @PostMapping("{id}/update")
    public ResponseEntity<List<DeviceDTO>> updateDeviceById(@PathVariable String id, @RequestBody DeviceEntity entityInfo){
        return ResponseEntity.ok(deviceServices.updateDeviceById(id, entityInfo));
    }

    @GetMapping("device/{state}")
    public ResponseEntity<List<DeviceDTO>> getDevicesByState(@PathVariable String state){
        return ResponseEntity.ok(deviceServices.getDevicesByState(state));
    }

    @GetMapping("device/brand/{brand}")
    public ResponseEntity<List<DeviceDTO>> getDevicesByBrand(@PathVariable String brand){
        return ResponseEntity.ok(deviceServices.getDevicesByBrand(brand));
    }
}
