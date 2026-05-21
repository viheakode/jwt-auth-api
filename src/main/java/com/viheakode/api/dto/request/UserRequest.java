package com.viheakode.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "Username field is required")
    private String username;
    @NotBlank(message = "Email field is required")
    private String email;
    @NotBlank(message = "Password field is required")
    private String password;
}
