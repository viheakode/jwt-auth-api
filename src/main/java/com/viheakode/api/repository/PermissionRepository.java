package com.viheakode.api.repository;

import com.viheakode.api.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    boolean existsByPermissionName(String permissionName);
    @Query(value = """
        SELECT p
        FROM Permission p
        JOIN RolePermission rp
            ON p.permissionId = rp.permissionId
        WHERE rp.roleId = :roleId
    """)
    List<Permission> findPermissionsByRoleId(Long roleId);

}
