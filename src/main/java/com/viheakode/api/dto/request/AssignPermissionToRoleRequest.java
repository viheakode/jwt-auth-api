package com.viheakode.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssignPermissionToRoleRequest {
    @NotNull(message = "The permissionId is required")
    private Long permissionId;
}
