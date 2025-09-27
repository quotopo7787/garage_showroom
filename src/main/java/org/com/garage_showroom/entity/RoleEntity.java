package org.com.garage_showroom.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter @Setter
public class RoleEntity extends BaseEntity {


    private String name;
}