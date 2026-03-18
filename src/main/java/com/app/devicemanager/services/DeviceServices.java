package com.app.devicemanager.services;

import com.app.devicemanager.dto.DeviceDTO;
import com.app.devicemanager.dto.DeviceDTOMapper;
import com.app.devicemanager.repos.DeviceRepository;
import com.app.devicemanager.repos.entity.DeviceEntity;
import com.app.devicemanager.exception.DeviceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceServices {

    private final DeviceRepository deviceRepository;
    private final DeviceDTOMapper deviceDTOMapper;

    public DeviceServices(DeviceRepository deviceRepository, DeviceDTOMapper deviceDTOMapper) {
        this.deviceRepository = deviceRepository;
        this.deviceDTOMapper = deviceDTOMapper;
    }

    public List<DeviceDTO> getAllDevices(){
        Sort sort = Sort.by("name").descending();
        return deviceRepository.findAll(sort)
                .stream()
                .map(deviceDTOMapper)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<DeviceDTO> createNewDevice(DeviceEntity deviceEntity) {
        deviceRepository.save(deviceEntity);
        return getAllDevices();
    }

    public DeviceDTO getDeviceById(String deviceId){
        return deviceRepository.findDeviceById(deviceId)
                .map(deviceDTOMapper)
                .orElseThrow(() -> new DeviceNotFoundException(deviceId));
    }

    @Transactional
    public List<DeviceDTO> deleteDeviceById(String deviceId) {
        deviceRepository.deleteDeviceById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException(deviceId));
        return getAllDevices();
    }
}
