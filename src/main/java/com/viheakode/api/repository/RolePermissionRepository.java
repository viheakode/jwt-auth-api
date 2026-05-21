package com.viheakode.api.repository;

import com.viheakode.api.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
    boolean existsByRoleIdAndPermissionId(Long roleId, Long permissionId);
    RolePermission findByRoleIdAndPermissionId(Long roleId, Long permissionId);
}
