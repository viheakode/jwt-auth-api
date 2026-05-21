package com.viheakode.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "user_roles")
@Data
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRoleId;
    private String uuid;
    private Long userId;
    private Long  roleId;
    private String status;
    private String publisher;
    private Date publishedDate;
    private Date modifiedDate;

    public UserRole(){
        this.status = "ACTIVE";
        this.publisher = "S.ADMIN";
        this.publishedDate = new Date();
        this.modifiedDate = new Date();
    }
}
