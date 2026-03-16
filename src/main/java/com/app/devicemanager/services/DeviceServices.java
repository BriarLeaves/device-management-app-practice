package com.app.devicemanager.services;

import com.app.devicemanager.domain.State;
import com.app.devicemanager.repos.DeviceRepository;
import com.app.devicemanager.repos.entity.DeviceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServices {

    private final DeviceRepository deviceRepository;

    public DeviceServices(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<DeviceEntity> getAllDevices(){
        Sort sort = Sort.by("name").descending();
        return deviceRepository.findAll(sort);
    }

    public List<DeviceEntity> createNewDevice(DeviceEntity deviceEntity) {
        deviceRepository.save(deviceEntity);
        return getAllDevices();
    }
}
