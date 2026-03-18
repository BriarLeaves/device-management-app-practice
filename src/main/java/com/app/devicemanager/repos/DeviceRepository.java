package com.app.devicemanager.repos;

import com.app.devicemanager.repos.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {

    Optional<DeviceEntity> findDeviceById(String deviceId);
    Optional<DeviceEntity> deleteDeviceById(String deviceId);

}
