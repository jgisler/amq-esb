package org.gislers.esb.simulator;

import org.gislers.esb.simulator.config.AppConfig;
import org.gislers.esb.simulator.send.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jim on 6/20/2015.
 */
public class Server {

    static ExecutorService executorService = Executors.newFixedThreadPool(20);
    @Autowired
    private static MessageTracker messageTracker;

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        final MessageSender messageSender = (MessageSender) ctx.getBean( "messageSender" );

        for (int i = 0; i < 100000; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    messageSender.sendRandomMessage();
                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();
        messageTracker.verifyTransactions();
    }
}
