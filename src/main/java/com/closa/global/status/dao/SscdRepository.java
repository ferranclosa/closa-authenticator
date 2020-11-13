package com.closa.global.status.dao;

import com.closa.global.status.model.StatusSubCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SscdRepository extends JpaRepository<StatusSubCode, String> {
}
