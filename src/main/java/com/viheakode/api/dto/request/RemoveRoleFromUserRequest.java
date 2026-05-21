package com.viheakode.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RemoveRoleFromUserRequest {
    @NotNull(message = "The roleId is required")
    private Long roleId;
}
