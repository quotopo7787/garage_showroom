package org.com.garage_showroom.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "services")
@Getter
@Setter
public class ServiceEntity extends BaseEntity {

    @Column(nullable = false, length = 150)
    private String name;

    @Column(name = "hourly_price", nullable = false)
    private Double hourlyPrice;

    @Column(length = 20, nullable = false)
    private String status = "ACTIVE";
}
