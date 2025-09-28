package org.com.garage_showroom.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter @Setter
public class UserEntity extends BaseEntity{

    @Column(length = 50, unique = true, nullable = false)
    private String username;

    @Column(length = 255, nullable = false)
    private String password;

    private boolean enabled = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @Column(name = "user_code", length = 32)
    private String userCode;

    @Column(name = "full_name", length = 150)
    private String fullName;

    @Email
    @Column(length = 255)
    private String email;

    @Column(length = 30)
    private String phone;

    @Column(length = 10)
    private String gender;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(name = "avatar_url", columnDefinition = "TEXT")
    private String avatarUrl;

    @Column(name = "updated_password_at")
    private LocalDateTime updatedPasswordAt;
}