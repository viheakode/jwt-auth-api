package com.viheakode.api.mapper;

import com.viheakode.api.dto.response.PermissionDto;
import com.viheakode.api.model.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {
    public PermissionDto toDto(Permission permission){
        if (permission == null){
            return null;
        }
        PermissionDto dto = new PermissionDto();
        dto.setPermissionId(permission.getPermissionId());
        dto.setUuid(permission.getUuid());
        dto.setPermissionName(permission.getPermissionName());
        dto.setDescription(permission.getDescription());
        dto.setStatus(permission.getStatus());
        dto.setPublisher(permission.getPublisher());
        dto.setPublishedDate(permission.getPublishedDate());
        dto.setModifiedDate(permission.getModifiedDate());
        return dto;
    }
}
