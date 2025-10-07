package org.com.garage_showroom.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "service_order_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceOrderService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ServiceOrder order;

    @ManyToOne(fetch = FetchType.LAZY)
    private ServiceCatalog service;

    private String description;
    private BigDecimal qty;
    private BigDecimal unitPrice;
}
