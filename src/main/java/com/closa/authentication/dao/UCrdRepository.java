package com.closa.authentication.dao;

import com.closa.authentication.model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UCrdRepository extends JpaRepository<UserCredentials, Long> {
}
