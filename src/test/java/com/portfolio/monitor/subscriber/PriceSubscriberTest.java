package com.portfolio.monitor.subscriber;

import com.portfolio.monitor.portfolio.PortfolioMonitor;
import com.portfolio.monitor.position.PositionHolder;
import com.portfolio.monitor.queue.QueueEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;

class PriceSubscriberTest {

    @Test
    void onApplicationEvent() {
        PriceSubscriberTimerTask priceSubscriberTimerTask = new PriceSubscriberTimerTask(new QueueEngine(), new PortfolioMonitor(new PositionHolder()));
        PriceSubscriber priceSubscriber = new PriceSubscriber(priceSubscriberTimerTask);
        assertDoesNotThrow(() -> priceSubscriber.onApplicationEvent(mock(ApplicationReadyEvent.class)));
    }

}