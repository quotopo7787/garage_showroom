package org.com.garage_showroom.entity;

import jakarta.persistence.*;
import lombok.*;
import org.com.garage_showroom.common.enums.PaymentMethod;
import org.com.garage_showroom.common.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ServiceOrder order;

    @ManyToOne(fetch = FetchType.LAZY)
    private SalesContract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    private java.sql.Date issueDate;

    @Column(precision = 14, scale = 2) private BigDecimal subtotal;
    @Column(precision = 14, scale = 2) private BigDecimal tax;
    @Column(precision = 14, scale = 2) private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private Instant createdAt = Instant.now();
}
