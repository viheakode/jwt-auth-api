package com.viheakode.api.mapper;

import com.viheakode.api.dto.response.RoleDto;
import com.viheakode.api.model.Permission;
import com.viheakode.api.model.Role;
import com.viheakode.api.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleMapper {

    private final PermissionRepository permissionRepository;

    public RoleDto toDto(Role role){

        if (role == null){
            return null;
        }

        RoleDto dto = new RoleDto();
        dto.setRoleId(role.getRoleId());
        dto.setUuid(role.getUuid());
        dto.setRoleName(role.getRoleName());
        dto.setDescription(role.getDescription());

        Set<String> permissions = permissionRepository
                .findPermissionsByRoleId(role.getRoleId())
                .stream()
                .map(Permission::getPermissionName)
                .collect(Collectors.toSet());

        dto.setPermissions(permissions);

        dto.setStatus(role.getStatus());
        dto.setPublisher(role.getPublisher());
        dto.setPublishedDate(role.getPublishedDate());
        dto.setModifiedDate(role.getModifiedDate());
        return dto;
    }
}
