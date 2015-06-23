package org.gislers.esb.simulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by jim on 6/21/2015.
 */
@Component
public class MessageTracker {

    private static final Logger logger = LoggerFactory.getLogger(MessageTracker.class);

    private ConcurrentMap<String, AtomicLong> trackingMap;

    @PostConstruct
    public void init() {
        trackingMap = new ConcurrentHashMap<String, AtomicLong>();
    }

    public synchronized void recordMessageSent(String uuid) {
        trackingMap.put(uuid, new AtomicLong(System.currentTimeMillis()));
    }

    public synchronized long getSentTime(String uuid) {
        return trackingMap.get(uuid).get();
    }

    public String verifyTransactions() {
        StringBuilder sb = new StringBuilder();
        for( String uuid : trackingMap.keySet() ) {
            sb.append(uuid).append("=").append(trackingMap.get(uuid).get()).append("ms").append("<br>");
        }
        return sb.toString();
    }
}
