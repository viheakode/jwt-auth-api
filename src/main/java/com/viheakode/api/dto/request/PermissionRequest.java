package com.viheakode.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PermissionRequest {
    @NotBlank(message = "The permissionName is required")
    private String permissionName;
    private String description;
}
