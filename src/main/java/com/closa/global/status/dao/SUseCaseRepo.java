package com.closa.global.status.dao;

import com.closa.global.status.model.StatusUseCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  SUseCaseRepo extends JpaRepository<StatusUseCase, String> {
}
