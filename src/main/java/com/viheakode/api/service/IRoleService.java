package com.viheakode.api.service;

import com.viheakode.api.dto.request.AssignPermissionToRoleRequest;
import com.viheakode.api.dto.request.RemovePermissionFromRoleRequest;
import com.viheakode.api.dto.request.RoleRequest;
import com.viheakode.api.dto.response.RoleDto;

import java.util.List;

public interface IRoleService {
    RoleDto create(RoleRequest request);
    RoleDto get(Long roleId);
    List<RoleDto> getAll();
    RoleDto update(Long roleId, RoleRequest request);
    RoleDto delete(Long roleId);

    RoleDto assignPermissionToRole(Long roleId, AssignPermissionToRoleRequest request);
    RoleDto removePermissionFromRole(Long roleId, RemovePermissionFromRoleRequest request);
}
