package com.viheakode.api.dto.response;

import lombok.Data;

import java.util.*;

@Data
public class RoleDto {
    private Long roleId;
    private String uuid;
    private String roleName;
    private String description;
    private Set<String> permissions = new HashSet<>();
    private String status;
    private String publisher;
    private Date publishedDate;
    private Date modifiedDate;
}
