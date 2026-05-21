package com.viheakode.api.service.serviceImp;

import com.viheakode.api.dto.request.PermissionRequest;
import com.viheakode.api.dto.response.PermissionDto;
import com.viheakode.api.exception.DuplicateException;
import com.viheakode.api.exception.ResourceNotFoundException;
import com.viheakode.api.mapper.PermissionMapper;
import com.viheakode.api.model.Permission;
import com.viheakode.api.repository.PermissionRepository;
import com.viheakode.api.service.IPermissionService;
import com.viheakode.api.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PermissionServiceImp implements IPermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    @Override
    @PreAuthorize("hasAuthority('CREATE_PERMISSION')")
    public PermissionDto create(PermissionRequest request) {

        String permissionName = request.getPermissionName().toUpperCase();
        if (permissionRepository.existsByPermissionName(permissionName)){
            throw new DuplicateException("Permission already exists");
        }

        Permission permission = new Permission();
        permission.setUuid(UUID.randomUUID().toString());
        permission.setPermissionName(permissionName);
        permission.setDescription(request.getDescription());
        permission.setPublisher(SecurityUtil.getPublisher());
        permissionRepository.save(permission);
        return permissionMapper.toDto(permission);
    }

    @Override
    @PreAuthorize("hasAuthority('READ_PERMISSION')")
    public PermissionDto get(Long permissionId) {
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));
        return permissionMapper.toDto(permission);
    }

    @Override
    @PreAuthorize("hasAuthority('READ_PERMISSION')")
    public List<PermissionDto> getAll() {
        List<Permission> permissionList = permissionRepository.findAll();
        return permissionList.stream().map(permissionMapper::toDto).toList();
    }

    @Override
    @PreAuthorize("hasAuthority('UPDATE_PERMISSION')")
    public PermissionDto update(Long permissionId, PermissionRequest request) {
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));

        String permissionName = request.getPermissionName().toUpperCase();
        if (permissionRepository.existsByPermissionName(permissionName)){
            throw new DuplicateException("Permission already exists");
        }

        permission.setPermissionName(permissionName);
        permission.setDescription(request.getDescription());
        permission.setModifiedDate(new Date());
        permissionRepository.save(permission);
        return permissionMapper.toDto(permission);
    }

    @Override
    @PreAuthorize("hasAuthority('DELETE_PERMISSION')")
    public PermissionDto delete(Long permissionId) {
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));
        permissionRepository.delete(permission);
        return permissionMapper.toDto(permission);
    }
}
