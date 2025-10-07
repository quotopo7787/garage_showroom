package org.com.garage_showroom.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "service_order_parts")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceOrderPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "order_id")
    private ServiceOrder order;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "part_id")
    private Part part;

    @Column(name = "qty", precision = 14, scale = 3, nullable = false)
    private BigDecimal qty;               

    @Column(name = "unit_price", precision = 14, scale = 2, nullable = false)
    private BigDecimal unitPrice;
}