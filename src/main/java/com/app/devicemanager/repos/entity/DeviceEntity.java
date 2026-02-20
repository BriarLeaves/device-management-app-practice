package com.app.devicemanager.repos.entity;

import com.app.devicemanager.domain.State;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Entity
public class DeviceEntity {

    @GeneratedValue
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private State state;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
}
