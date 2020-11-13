package com.closa.authentication.dao;

import com.closa.authentication.model.UserUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UUsrRepository extends JpaRepository<UserUser, Long> {
}
