package com.closa.global.trace.dao;

import com.closa.global.trace.model.LocalEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalEventRepository extends JpaRepository<LocalEvent, Long> {


}
