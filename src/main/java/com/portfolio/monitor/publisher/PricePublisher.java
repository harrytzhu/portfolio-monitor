package com.portfolio.monitor.publisher;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Timer;

@Component
public class PricePublisher implements ApplicationListener<ApplicationReadyEvent> {

    private static final int PUBLISH_INTERVAL = 1000;

    private final PricePublisherTimerTask pricePublisherTimerTask;

    public PricePublisher(PricePublisherTimerTask pricePublisherTimerTask) {
        this.pricePublisherTimerTask = pricePublisherTimerTask;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Timer timer = new Timer();
        timer.schedule(pricePublisherTimerTask, 0, PUBLISH_INTERVAL);
    }
}
