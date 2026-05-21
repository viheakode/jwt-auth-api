package com.viheakode.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RemovePermissionFromRoleRequest {
    @NotNull(message = "The permissionId is required")
    private Long permissionId;
}
