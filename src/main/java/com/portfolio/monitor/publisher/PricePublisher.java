package com.portfolio.monitor.publisher;

import java.util.Timer;

public class PricePublisher {

    private PricePublisherTimerTask pricePublisherTimerTask;

    public PricePublisher(PricePublisherTimerTask pricePublisherTimerTask) {
        this.pricePublisherTimerTask = pricePublisherTimerTask;
    }

    public void scheduledPublish() {
        Timer timer = new Timer();
        timer.schedule(pricePublisherTimerTask, 0, 1000);
    }
}
