package com.viheakode.api.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class PermissionDto {
    private Long permissionId;
    private String uuid;
    private String permissionName;
    private String description;
    private String status;
    private String publisher;
    private Date publishedDate;
    private Date modifiedDate;
}
