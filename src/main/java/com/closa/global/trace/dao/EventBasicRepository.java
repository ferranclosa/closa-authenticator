package com.closa.global.trace.dao;

import com.closa.global.trace.model.Connection;
import com.closa.global.trace.model.EventBasic;
import com.closa.global.trace.model.enums.EventsHandled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventBasicRepository extends JpaRepository<EventBasic, Long> {

    List<EventBasic> findByConnection(Connection pConnection);

    Optional<EventBasic> findTopByConnectionAndEventHandledOrderByWhenEventHappenedDesc(EventsHandled eventsHandled, Connection connection);

}
