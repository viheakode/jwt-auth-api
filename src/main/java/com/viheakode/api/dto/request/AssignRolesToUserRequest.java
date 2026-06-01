package com.viheakode.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AssignRolesToUserRequest {
    @NotNull(message = "The roleId is required")
    private List<Long> roleId;
}
