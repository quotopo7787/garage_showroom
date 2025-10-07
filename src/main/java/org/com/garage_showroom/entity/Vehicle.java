package org.com.garage_showroom.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    private String vin;
    private String licensePlate;
    private String brand;
    private String model;
    private String variant;
    private Integer year;
    private String color;
    private Instant createdAt = Instant.now();
}
