package com.app.devicemanager.services;

import com.app.devicemanager.domain.State;
import com.app.devicemanager.dto.DeviceDTO;
import com.app.devicemanager.dto.DeviceDTOMapper;
import com.app.devicemanager.exception.DeviceInUseException;
import com.app.devicemanager.repos.DeviceRepository;
import com.app.devicemanager.repos.entity.DeviceEntity;
import com.app.devicemanager.exception.DeviceNotFoundException;
import com.app.devicemanager.repos.entity.mapper.DeviceEntityMapper;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.app.devicemanager.domain.State.IN_USE;

@Slf4j
@Service
public class DeviceServices {

    private final DeviceRepository deviceRepository;
    private final DeviceDTOMapper deviceDTOMapper;
    private final DeviceEntityMapper deviceEntityMapper;

    public DeviceServices(DeviceRepository deviceRepository, DeviceDTOMapper deviceDTOMapper, DeviceEntityMapper deviceEntityMapper) {
        this.deviceRepository = deviceRepository;
        this.deviceDTOMapper = deviceDTOMapper;
        this.deviceEntityMapper = deviceEntityMapper;
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
                .map(deviceEntityMapper::toDeviceDTO)
                .orElseThrow(() -> new DeviceNotFoundException(deviceId));
    }

    @Transactional
    public List<DeviceDTO> deleteDeviceById(String deviceId) {
        DeviceEntity entityToDelete = deviceRepository.findDeviceById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException(deviceId));

        if (entityToDelete.getState() == IN_USE){
            throw new DeviceInUseException(deviceId);
        }

        try {
            deviceRepository.delete(entityToDelete);
        } catch (Exception e){
            log.error("Unable to delete Device data from persistence", e);
            throw new PersistenceException(e);
        }

        return getAllDevices();
    }

    public List<DeviceDTO> updateDeviceById(String deviceId, DeviceEntity entityInfo){
        
        return getAllDevices();
    }

    public List<DeviceDTO> getDevicesByBrand(String deviceBrand){
        try{
            return deviceRepository.findDevicesByBrand(deviceBrand)
                    .stream()
                    .map(deviceDTOMapper)
                    .collect(Collectors.toList());
        }
        catch (Exception e){
            log.error("Unable to read Device data from persistence", e);
            throw new PersistenceException(e);
        }
    }

    public List<DeviceDTO> getDevicesByState(String deviceState) {

        State stateEnum = State.valueOf(deviceState.toUpperCase());

        try {
            return deviceRepository.findDevicesByState(stateEnum)
                    .stream()
                    .map(deviceDTOMapper)
                    .collect(Collectors.toList());
        }
        catch(Exception e){
            log.error("Unable to read Device data from persistence", e);
            throw new PersistenceException(e);
        }
    }
}
