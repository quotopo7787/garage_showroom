package org.com.garage_showroom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.com.garage_showroom.common.enums.ServiceTicketStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "service_tickets")
@Getter @Setter
public class ServiceTicketEntity extends BaseEntity {

    @Column(name = "code", length = 30, nullable = false, unique = true)
    private String code; // VD: ST2023001

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;  // khách hàng (đã có sẵn entity UserEntity)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private CarEntity car;          // entity Car bạn đã/đang dùng

    @Column(name = "received_date", nullable = false)
    private LocalDate receivedDate;

    @Column(name = "completed_date")
    private LocalDate completedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private ServiceTicketStatus status = ServiceTicketStatus.PROCESSING;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "total_amount", precision = 18, scale = 2, nullable = false)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @OneToMany(mappedBy = "serviceTicket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceTicketItemEntity> items = new ArrayList<>();
}
