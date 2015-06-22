package org.gislers.esb.simulator;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jim on 6/21/2015.
 */
@Component
public class MessageTracker {

    private ConcurrentMap<String, AtomicInteger> trackingMap;

    @PostConstruct
    public void init() {
        trackingMap = new ConcurrentHashMap<String, AtomicInteger>();
    }

    public synchronized void recordMessageSent(String uuid) {
        if(trackingMap.containsKey(uuid)) {
            throw new RuntimeException("Duplicate uuids");
        }
        trackingMap.put( uuid, new AtomicInteger(1) );
    }

    public synchronized void recordMessageRecieved(String uuid) {
        if( !trackingMap.containsKey(uuid) ) {
            throw new RuntimeException("Missing uuid");
        }
        trackingMap.get(uuid).getAndIncrement();
    }

    public void verifyTransactions() {
        for( String uuid : trackingMap.keySet() ) {
            if( trackingMap.get(uuid).get() != 2 ) {
                throw new RuntimeException( "transaction verification failed: " + uuid );
            }
        }
    }
}
