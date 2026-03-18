package com.app.devicemanager.dto;

import com.app.devicemanager.repos.entity.DeviceEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DeviceDTOMapper implements Function<DeviceEntity, DeviceDTO> {

    @Override
    public DeviceDTO apply(DeviceEntity device){
        return new DeviceDTO(
                device.getId(),
                device.getName(),
                device.getBrand(),
                device.getState(),
                device.getCreatedAt()
        );
    }

}
