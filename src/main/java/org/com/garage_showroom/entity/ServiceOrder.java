package org.com.garage_showroom.entity;

import jakarta.persistence.*;
import lombok.*;
import org.com.garage_showroom.common.enums.OrderStatus;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "service_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceOrder {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String orderCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.NEW;

    private Instant requestedDeliveryAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User approvedByStaff;

    @ManyToOne(fetch = FetchType.LAZY)
    private User mechanic;

    private Instant approvedAt;
    private Instant startedAt;
    private Instant completedAt;
    private Instant cancelledAt;
    private String cancelReason;

    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();
}
