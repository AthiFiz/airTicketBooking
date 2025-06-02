package com.airline.booking.entity;

import com.airline.booking.util.AirplaneCapacityClass;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "airplanes")
@Getter @Setter
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String model;

    @Enumerated(EnumType.STRING)
    @Column(name = "capacity_class", nullable = false)
    private AirplaneCapacityClass capacityClass;

    @Column(name = "first_class_capacity")
    private int firstClassCapacity;

    @Column(name = "business_class_capacity")
    private int businessClassCapacity;

    @Column(name = "economy_class_capacity")
    private int economyClassCapacity;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}


