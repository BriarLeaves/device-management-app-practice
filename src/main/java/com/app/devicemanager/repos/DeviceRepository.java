package com.app.devicemanager.repos;

import com.app.devicemanager.domain.State;
import com.app.devicemanager.repos.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {

    Optional<DeviceEntity> findDeviceById(String deviceId);
    List<DeviceEntity> findDevicesByState(State deviceState);
    List<DeviceEntity> findDevicesByBrand(String deviceBrand);

}
