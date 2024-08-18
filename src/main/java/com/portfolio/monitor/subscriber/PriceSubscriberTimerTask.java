package com.portfolio.monitor.subscriber;

import com.portfolio.monitor.model.dto.PriceDto;
import com.portfolio.monitor.portfolio.PortfolioMonitor;
import com.portfolio.monitor.queue.QueueEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.TimerTask;

@Component
public class PriceSubscriberTimerTask extends TimerTask {

    private final QueueEngine queueEngine;

    private final PortfolioMonitor portfolioMonitor;

    @Autowired
    public PriceSubscriberTimerTask(QueueEngine queueEngine, PortfolioMonitor portfolioMonitor) {
        this.queueEngine = queueEngine;
        this.portfolioMonitor = portfolioMonitor;
    }

    @Override
    public void run() {
        PriceDto priceDto = getLatestPrice();
        if (priceDto == null) {
            return;
        }
        portfolioMonitor.display(priceDto);
    }

    private PriceDto getLatestPrice() {
        PriceDto priceDto = queueEngine.poll();
        if (priceDto != null) {
            PriceDto nextPriceDto = queueEngine.poll();
            while (nextPriceDto != null) {
                priceDto = nextPriceDto;
                nextPriceDto = queueEngine.poll();
            }
        }
        return priceDto;
    }
}
