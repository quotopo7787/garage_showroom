package org.com.garage_showroom.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "audit_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User actorUser;

    private String action;
    private String entity;
    private String entityId;

    @Column(columnDefinition = "jsonb")
    private String meta;

    private Instant createdAt = Instant.now();
}