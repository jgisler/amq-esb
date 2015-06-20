package org.gislers.esb.simulator;

import org.gislers.esb.simulator.config.AppConfig;
import org.gislers.esb.simulator.send.MessageSender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;

/**
 * Created by jim on 6/20/2015.
 */
public class Server {

    static Executor pool = Executors.newFixedThreadPool(20);

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        final MessageSender messageSender = (MessageSender) ctx.getBean( "messageSender" );

        for( int i=0; i<10000; i++ ) {

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    messageSender.sendRandomMessage();
                }
            };
            pool.execute(runnable);
        }
    }
}
