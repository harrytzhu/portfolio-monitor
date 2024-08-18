package com.portfolio.monitor.subscriber;

import com.portfolio.monitor.model.dto.PriceDto;
import com.portfolio.monitor.portfolio.PortfolioMonitor;
import com.portfolio.monitor.position.PositionHolder;
import com.portfolio.monitor.queue.QueueEngine;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

class PriceSubscriberTimerTaskTest {

    @Test
    void run() {
        QueueEngine queueEngine = new QueueEngine();
        PriceDto priceDto = new PriceDto(new HashMap<>(), new HashMap<>());
        queueEngine.publish(priceDto);
        PriceDto priceDto2 = new PriceDto(new HashMap<>(), new HashMap<>());
        queueEngine.publish(priceDto2);
        PortfolioMonitor portfolioMonitor = mock(PortfolioMonitor.class);
        doNothing().when(portfolioMonitor).display(any());
        PriceSubscriberTimerTask priceSubscriberTimerTask = new PriceSubscriberTimerTask(queueEngine, portfolioMonitor);
        priceSubscriberTimerTask.run();
    }

}