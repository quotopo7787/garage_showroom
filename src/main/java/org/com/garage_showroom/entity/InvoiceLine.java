package org.com.garage_showroom.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "invoice_lines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Invoice invoice;

    private String itemType; // SERVICE | PART | VEHICLE
    private UUID itemId;
    private String description;
    private BigDecimal  qty;
    private BigDecimal unitPrice;
}
