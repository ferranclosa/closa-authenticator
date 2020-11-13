package com.closa.authentication.dao;

import com.closa.authentication.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UAddRepository extends JpaRepository<UserAddress, Long> {
}
