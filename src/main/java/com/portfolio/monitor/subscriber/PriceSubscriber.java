package com.portfolio.monitor.subscriber;


import java.util.Timer;

public class PriceSubscriber {

    private final PriceSubscriberTimerTask pricePublisherTimerTask;

    public PriceSubscriber(PriceSubscriberTimerTask pricePublisherTimerTask) {
        this.pricePublisherTimerTask = pricePublisherTimerTask;
    }

    public void scheduledPoll() {
        Timer timer = new Timer();
        timer.schedule(pricePublisherTimerTask, 0, 1000);
    }
}
