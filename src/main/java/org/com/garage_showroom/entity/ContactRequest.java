package org.com.garage_showroom.entity;

import jakarta.persistence.*;
import lombok.*;
import org.com.garage_showroom.common.enums.ContactStatus;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "contact_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactRequest {
    @Id
    @GeneratedValue
    private UUID id;

    private String customerName;
    private String phone;
    private String email;
    private String modelName;

    @Enumerated(EnumType.STRING)
    private ContactStatus status = ContactStatus.NEW;

    @ManyToOne(fetch = FetchType.LAZY)
    private User assignedSale;

    private Instant createdAt = Instant.now();
    private Instant claimedAt;
    private Instant closedAt;
}
