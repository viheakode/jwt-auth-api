package com.viheakode.api.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class AssignRolesToUserRequest {
    private List<Long> roleId;
}
