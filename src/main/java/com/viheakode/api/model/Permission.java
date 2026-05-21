package com.viheakode.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "permissions")
@Data
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;
    private String uuid;

    @Column(nullable = false, unique = true)
    private String permissionName;

    private String description;
    private String status;
    private String publisher;
    private Date publishedDate;
    private Date modifiedDate;

    public Permission(){
        this.status = "ACTIVE";
        this.publisher = "S.ADMIN";
        this.publishedDate = new Date();
        this.modifiedDate = new Date();
    }
}
