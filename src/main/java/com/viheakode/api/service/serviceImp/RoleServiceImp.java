package com.viheakode.api.service.serviceImp;

import com.viheakode.api.dto.request.AssignPermissionToRoleRequest;
import com.viheakode.api.dto.request.RemovePermissionFromRoleRequest;
import com.viheakode.api.dto.request.RoleRequest;
import com.viheakode.api.dto.response.RoleDto;
import com.viheakode.api.exception.DuplicateException;
import com.viheakode.api.exception.ResourceNotFoundException;
import com.viheakode.api.mapper.RoleMapper;
import com.viheakode.api.model.Permission;
import com.viheakode.api.model.Role;
import com.viheakode.api.model.RolePermission;
import com.viheakode.api.repository.PermissionRepository;
import com.viheakode.api.repository.RolePermissionRepository;
import com.viheakode.api.repository.RoleRepository;
import com.viheakode.api.service.IRoleService;
import com.viheakode.api.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImp implements IRoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;

    @Override
    @PreAuthorize("hasAuthority('CREATE_ROLE')")
    public RoleDto create(RoleRequest request) {

        String roleName = request.getRoleName().toUpperCase();
        if (roleRepository.existsByRoleName(roleName)){
            throw new DuplicateException("Role already exists");
        }

        Role role = new Role();
        role.setUuid(UUID.randomUUID().toString());
        role.setRoleName(roleName);
        role.setDescription(request.getDescription());
        role.setPublisher(SecurityUtil.getPublisher());
        roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    @Override
    @PreAuthorize("hasAuthority('READ_ROLE')")
    public RoleDto get(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        return roleMapper.toDto(role);
    }

    @Override
    @PreAuthorize("hasAuthority('READ_ROLE')")
    public List<RoleDto> getAll() {
        List<Role> roleList = roleRepository.findAll();
        return roleList.stream().map(roleMapper::toDto).toList();
    }

    @Override
    @PreAuthorize("hasAuthority('UPDATE_ROLE')")
    public RoleDto update(Long roleId, RoleRequest request) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        String roleName = request.getRoleName().toUpperCase();
        if (roleRepository.existsByRoleName(roleName)){
            throw new DuplicateException("Role already exists");
        }

        role.setRoleName(roleName);
        role.setDescription(request.getDescription());
        role.setModifiedDate(new Date());
        return roleMapper.toDto(role);
    }

    @Override
    @PreAuthorize("hasAuthority('DELETE_ROLE')")
    public RoleDto delete(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        roleRepository.delete(role);
        return roleMapper.toDto(role);
    }

    @Override
    @PreAuthorize("hasAuthority('UPDATE_ROLE')")
    public RoleDto assignPermissionToRole(Long roleId, AssignPermissionToRoleRequest request) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        Permission permission = permissionRepository.findById(request.getPermissionId())
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));

        boolean exist = rolePermissionRepository.existsByRoleIdAndPermissionId(roleId, request.getPermissionId());
        if (exist){
            throw new DuplicateException("Permission already assigned to role");
        }

        RolePermission rolePermission = new RolePermission();
        rolePermission.setUuid(UUID.randomUUID().toString());
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(request.getPermissionId());
        rolePermission.setPublisher(SecurityUtil.getPublisher());
        rolePermissionRepository.save(rolePermission);
        return roleMapper.toDto(role);
    }

    @Override
    public RoleDto removePermissionFromRole(Long roleId, RemovePermissionFromRoleRequest request) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        Permission permission = permissionRepository.findById(request.getPermissionId())
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));

        boolean exist = rolePermissionRepository.existsByRoleIdAndPermissionId(roleId, request.getPermissionId());
        if (!exist){
            throw new ResourceNotFoundException("Permission is not assigned to this role");
        }
        RolePermission rolePermission = rolePermissionRepository.findByRoleIdAndPermissionId(roleId, request.getPermissionId());
        rolePermissionRepository.delete(rolePermission);
        return roleMapper.toDto(role);
    }

}
