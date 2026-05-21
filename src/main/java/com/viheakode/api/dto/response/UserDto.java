package com.viheakode.api.dto.response;

import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
    private Long userId;
    private String uuid;
    private String username;
    private String email;
    private Set<String> roles = new HashSet<>();
    private String status;
    private String publisher;
    private Date publishedDate;
    private Date modifiedDate;
}
