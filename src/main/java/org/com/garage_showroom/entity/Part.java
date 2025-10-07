package org.com.garage_showroom.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;



@Entity
@Table(name = "parts")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Part {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String unit;

    @Column(name = "stock_qty", precision = 14, scale = 3, nullable = false)
    private BigDecimal stockQty;

    @Column(name = "reserved_qty", precision = 14, scale = 3, nullable = false)
    private BigDecimal reservedQty;

    @Column(name = "reorder_threshold", precision = 14, scale = 3, nullable = false)
    private BigDecimal reorderThreshold;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt = Instant.now();
}