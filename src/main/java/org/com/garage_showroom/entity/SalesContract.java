package org.com.garage_showroom.entity;

import jakarta.persistence.*;
import lombok.*;
import org.com.garage_showroom.common.enums.ContractStatus;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "sales_contracts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesContract {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private User sale;

    private String modelName;
    private String color;
    private Integer year;

    @Enumerated(EnumType.STRING)
    private ContractStatus status = ContractStatus.NEW;

    private Instant confirmedAt;
    private Instant deliveredAt;
    private Instant cancelledAt;
    private Instant createdAt = Instant.now();
}