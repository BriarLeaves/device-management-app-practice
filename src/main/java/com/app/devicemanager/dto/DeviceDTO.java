package com.app.devicemanager.dto;

import com.app.devicemanager.domain.State;

import java.time.Instant;

public record DeviceDTO(
        Long id,
        String name,
        String brand,
        State state,
        Instant createdAt
) {

}
