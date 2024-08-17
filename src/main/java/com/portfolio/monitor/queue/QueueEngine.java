package com.portfolio.monitor.queue;

import com.portfolio.monitor.model.dto.StockDto;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class QueueEngine {
    private final Queue<Map<String, Double>> queue = new ConcurrentLinkedQueue<>();

    public void publish(Map<String, Double> securityPriceMap) {
        System.out.println("Publishing: " + securityPriceMap);
        queue.add(securityPriceMap);
    }

    public Map<String, Double> poll() {
        System.out.println("Polling: " + queue);
        return queue.poll();
    }
}
