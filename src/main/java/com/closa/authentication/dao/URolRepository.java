package com.closa.authentication.dao;

import com.closa.authentication.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URolRepository extends JpaRepository<UserRole, Long> {
    UserRole findByRoleCode(String role);
}
