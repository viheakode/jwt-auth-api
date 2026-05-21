package com.viheakode.api.repository;

import com.viheakode.api.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    boolean existsByUserIdAndRoleId(Long userId, Long roleId);
    UserRole findByUserIdAndRoleId(Long userId, Long roleId);
}