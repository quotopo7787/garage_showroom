import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "audit_log")
@Getter
@Setter
public class AuditLog {

    @Id
    @UuidGenerator
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "entity_name", nullable = false, length = 64)
    private String entityName;

    @Column(name = "entity_id", columnDefinition = "uuid")
    private UUID entityId; //

    @Column(nullable = false, length = 16)
    private String action;

    @Column(name = "actor_user_id", columnDefinition = "uuid")
    private UUID actorUserId;

    @Column(columnDefinition = "jsonb")
    private String data;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();
}
