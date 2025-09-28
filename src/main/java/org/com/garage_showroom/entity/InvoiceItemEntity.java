package org.com.garage_showroom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "invoice_item")
@Getter
@Setter
public class InvoiceItemEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    private InvoiceEntity invoice;

    @Column(name = "product_name", nullable = false, length = 255)
    private String productName;

    private int quantity;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "line_total")
    private BigDecimal lineTotal;
}