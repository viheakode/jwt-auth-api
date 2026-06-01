package com.viheakode.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleRequest {
    @NotBlank(message = "The roleName is required")
    private String roleName;
    private String description;

    public RoleRequest(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }
}
