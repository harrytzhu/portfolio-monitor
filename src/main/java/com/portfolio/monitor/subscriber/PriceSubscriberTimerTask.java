package com.portfolio.monitor.subscriber;

import com.portfolio.monitor.queue.QueueEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TimerTask;

@Component
public class PriceSubscriberTimerTask extends TimerTask {

    private final QueueEngine queueEngine;

    @Autowired
    public PriceSubscriberTimerTask(QueueEngine queueEngine) {
        this.queueEngine = queueEngine;
    }

    @Override
    public void run() {
        Map<String, Double> priceMap = queueEngine.poll();
        if (priceMap != null) {
            for (Map.Entry<String, Double> entry : priceMap.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
    }
}
