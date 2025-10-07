package org.com.garage_showroom.entity;

import jakarta.persistence.*;
import lombok.*;
import org.com.garage_showroom.common.enums.MechanicState;
import org.com.garage_showroom.common.enums.UserRole;
import org.com.garage_showroom.common.enums.UserStatus;


import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    private String passwordHash;
    private String name;
    private String phone;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    private MechanicState mechanicState;

    private String notes;

    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();
}