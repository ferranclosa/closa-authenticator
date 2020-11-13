package com.closa.global.trace.dao;

import com.closa.global.trace.model.Connection;
import com.closa.global.trace.model.EventStats;
import com.closa.global.trace.model.enums.EventsHandled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventStatsRepository extends JpaRepository<EventStats, Long > {
    @Query("select e from EventStats e where e.eventsHandled = :eventHandled and e.connection = :connection")
    Optional<EventStats> findEventTypeAndConnection(@Param("eventHandled") EventsHandled eventHandled, @Param("connection") Connection connection);

    @Query("select e from EventStats e where e.eventsHandled = :eventHandled and e.LogonId = :logonId")
    Optional<EventStats> findEventTypeAndLogonId(@Param("eventHandled") EventsHandled eventHandled, @Param("logonId") String logonId);

}
