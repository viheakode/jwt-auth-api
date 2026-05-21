package com.viheakode.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String uuid;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String status;
    private String publisher;
    private Date publishedDate;
    private Date modifiedDate;

    public User(){
        this.status = "ACTIVE";
        this.publisher = "S.ADMIN";
        this.publishedDate = new Date();
        this.modifiedDate = new Date();
    }
}
