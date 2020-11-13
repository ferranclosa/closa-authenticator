package com.closa.authentication.dao;

import com.closa.authentication.model.UserConnection;
import com.closa.global.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UConRepository extends JpaRepository<UserConnection, Long> {

    @Query("select new com.closa.global.security.model.User (c) " +
            "from UserConnection c where c.connectionId = :userName")
    Optional<User> provideUserDetails(@Param("userName") String userName);

    Optional<UserConnection> findByConnectionId(String username);
}
