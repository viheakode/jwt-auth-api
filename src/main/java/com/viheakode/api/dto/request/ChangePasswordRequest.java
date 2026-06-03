package com.viheakode.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangePasswordRequest {
    @NotBlank(message = "The currentPassword is required")
    private String currentPassword;
    @NotBlank(message = "The newPassword is required")
    private String newPassword;
}
