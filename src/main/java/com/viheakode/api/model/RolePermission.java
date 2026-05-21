package com.viheakode.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "role_permissions")
@Data
public class RolePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rolePermissionId;
    private String uuid;
    private Long roleId;
    private Long permissionId;
    private String status;
    private String publisher;
    private Date publishedDate;
    private Date modifiedDate;

    public RolePermission(){
        this.status = "ACTIVE";
        this.publisher = "S.ADMIN";
        this.publishedDate = new Date();
        this.modifiedDate = new Date();
    }
}
