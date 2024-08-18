package com.portfolio.monitor.queue;

import com.portfolio.monitor.model.dto.PriceDto;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class QueueEngine {
    private final Queue<PriceDto> queue = new ConcurrentLinkedQueue<>();

    public void publish(PriceDto priceDto) {
        //System.out.println("Publishing: " + securityPriceMap);
        queue.add(priceDto);
    }

    public PriceDto poll() {
        //System.out.println("Polling: " + queue);
        return queue.poll();
    }
}
