package com.viheakode.api.service;

import com.viheakode.api.dto.request.PermissionRequest;
import com.viheakode.api.dto.response.PermissionDto;

import java.util.List;

public interface IPermissionService {
    PermissionDto create(PermissionRequest request);
    PermissionDto get(Long permissionId);
    List<PermissionDto> getAll();
    PermissionDto update(Long permissionId, PermissionRequest request);
    PermissionDto delete(Long permissionId);
}
