package com.portfolio.monitor.subscriber;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Timer;

@Component
public class PriceSubscriber implements ApplicationListener<ApplicationReadyEvent> {

    private static final int POLLING_INTERVAL = 5000;

    private final PriceSubscriberTimerTask pricePublisherTimerTask;

    public PriceSubscriber(PriceSubscriberTimerTask pricePublisherTimerTask) {
        this.pricePublisherTimerTask = pricePublisherTimerTask;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Timer timer = new Timer();
        timer.schedule(pricePublisherTimerTask, 0, POLLING_INTERVAL);
    }
}
