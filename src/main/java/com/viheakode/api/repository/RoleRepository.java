package com.viheakode.api.repository;

import com.viheakode.api.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByRoleName(String roleName);
    Optional<Role> findByRoleName(String roleName);
    @Query("""
        SELECT r
        FROM Role r
        JOIN UserRole ur
            ON r.roleId = ur.roleId
        WHERE ur.userId = :userId
    """)
    List<Role> findRolesByUserId(Long userId);
}
