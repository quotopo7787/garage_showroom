package org.com.garage_showroom.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "part_movements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Part part;

    private String type; // IN, RESERVE, RELEASE, CONSUME, ADJUST
    @Column(precision = 14, scale = 3) private BigDecimal qty;
    private String refType;
    private UUID refId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    private Instant createdAt = Instant.now();
}
