package com.app.devicemanager.repos;

import com.app.devicemanager.repos.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {

}
