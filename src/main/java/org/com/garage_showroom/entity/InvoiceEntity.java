package org.com.garage_showroom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoice")
@Getter
@Setter
public class InvoiceEntity extends BaseEntity {
    @Column(name = "invoice_code", unique = true, nullable = false, length = 50)
    private String invoiceCode;

    @Column(name = "invoice_type", length = 20, nullable = false)
    private String invoiceType; // SERVICE / CAR

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private UserEntity customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private UserEntity employee; // chỉ cho hóa đơn bán xe

    @Column(name = "car_id")
    private Long carId; // nếu có bảng Car thì FK

    @Column(name = "total_amount")
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Column(length = 30)
    private String status = "PENDING";

    @Column(columnDefinition = "TEXT")
    private String notes;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceItemEntity> items = new ArrayList<>();
}