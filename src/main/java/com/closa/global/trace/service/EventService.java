package com.closa.global.trace.service;

import com.closa.global.trace.dao.EventBasicRepository;
import com.closa.global.trace.dao.EventStatsRepository;
import com.closa.global.trace.dao.LocalEventRepository;
import com.closa.global.trace.model.*;
import com.closa.global.trace.model.enums.EventsHandled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class EventService {

    //@Value("${global.trace.local")
    @Value("#{new Boolean('${global.trace.local}')}")
    private boolean traceLocal;

    //@Value("${global.trace.statistics")
    @Value("#{new Boolean('${global.trace.statistics}')}")
    private boolean traceStatistic;

    //@Value("${global.trace.audit")
    @Value("#{new Boolean('${global.trace.audit}')}")
    private boolean traceAudit;

    @Autowired
    EventBasicRepository eventBasicRepository;

    @Autowired
    EventStatsRepository eventStatsRepository;

    @Autowired
    LocalEventRepository localEventRepository;

    public void insertEvent(Connection connection, EventsHandled eventsHandled) throws Exception {
        if (traceAudit) {
            EventBasic wEvnt = new EventBasic();
            wEvnt.setEventHandled(eventsHandled);
            wEvnt.setConnection(connection);
            wEvnt.setLogonId(connection.getLogonId());
            wEvnt.setWhenEventHappened();
            eventBasicRepository.save(wEvnt);
            if (traceStatistic) {
                saveStatsEvent(wEvnt);
            }
            if (traceLocal) {
                LocalEvent localEvent = new LocalEvent(connection.getLogonId(), eventsHandled, wEvnt.toJson());
                localEventRepository.save(localEvent);
            }
        }
    }

    public void insertEvent(String who, EventsHandled eventsHandled) throws Exception {
        if (traceAudit) {
            EventBasic wEvnt = new EventBasic();
            wEvnt.setEventHandled(eventsHandled);
            wEvnt.setConnection(null);
            wEvnt.setWhenEventHappened();
            wEvnt.setLogonId(who);
            eventBasicRepository.save(wEvnt);

            if (traceStatistic) {
                saveStatsEvent(wEvnt);
            }

            if (traceLocal) {
                LocalEvent localEvent = new LocalEvent(who, eventsHandled, wEvnt.toJson());
                localEventRepository.save(localEvent);
            }
        }

    }

    public void insertEvent(String who, EventsHandled eventsHandled, String object) throws Exception {

        if (traceAudit) {
            EventEnhanced wEvnt = new EventEnhanced();
            wEvnt.setEventHandled(eventsHandled);
            wEvnt.setConnection(null);
            wEvnt.setWhenEventHappened();
            wEvnt.setLogonId(who);
            wEvnt.setBigData(object.toCharArray());
            eventBasicRepository.save(wEvnt);

            if (traceStatistic) {
                saveStatsEvent(wEvnt);
            }

            if (traceLocal) {
                LocalEvent localEvent = new LocalEvent(who, eventsHandled, wEvnt.toJson());
                localEventRepository.save(localEvent);
            }
        }
    }

    private void saveStatsEvent(EventBasic wEvnt) {
        EventStats wEventSt;
        Optional<EventStats> wEvent;
        if (wEvnt.getConnection() != null) {
            wEvent = eventStatsRepository.findEventTypeAndConnection(wEvnt.getEventHandled(), wEvnt.getConnection());
        } else {
            wEvent = eventStatsRepository.findEventTypeAndLogonId(wEvnt.getEventHandled(), wEvnt.getLogonId());
        }


        if (!wEvent.isPresent()) {
            wEventSt = new EventStats();
            wEventSt.setEventsHandled(wEvnt.getEventHandled());
            if (wEvnt.getConnection() != null) {
                wEventSt.setConnection(wEvnt.getConnection());
                wEventSt.setLogonId(null);
            } else {
                wEventSt.setConnection(null);
                wEventSt.setLogonId(wEvnt.getLogonId());
            }

            wEventSt.setFirstEverRecorded(Instant.now());
            wEventSt.setLastEverRecorded(Instant.now());
            wEventSt.setLongTermCnt(Long.valueOf(1));
            wEventSt.setShortTermCnt(Long.valueOf(1));

        } else {
            wEventSt = wEvent.get();
            wEventSt.setLastEverRecorded(Instant.now());
            wEventSt.setLongTermCnt(wEventSt.getLongTermCnt() + 1);
            wEventSt.setShortTermCnt(wEventSt.getShortTermCnt() + 1);
        }
        eventStatsRepository.save(wEventSt);
    }

    public Boolean getTraceLocal() {
        return traceLocal;
    }

    public Boolean getTraceStatistic() {
        return traceStatistic;
    }

    public Boolean getTraceAudit() {
        return traceAudit;
    }
}
