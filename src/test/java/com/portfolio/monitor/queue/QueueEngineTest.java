package com.portfolio.monitor.queue;

import com.portfolio.monitor.model.dto.PriceDto;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class QueueEngineTest {

    @Test
    void publish() {
        QueueEngine queueEngine = new QueueEngine();
        assertDoesNotThrow(() -> queueEngine.publish(new PriceDto(new HashMap<>(), new HashMap<>())));
    }

    @Test
    void poll() {
        QueueEngine queueEngine = new QueueEngine();
        Map<String, Double> priceMap = new HashMap<>();
        priceMap.put("a", 1.0);
        priceMap.put("b", 2.0);
        Map<String, Double> changedMap = new HashMap<>();
        changedMap.put("a", 1.0);
        changedMap.put("b", 2.0);
        queueEngine.publish(new PriceDto(priceMap, changedMap));
        PriceDto priceDto = queueEngine.poll();
        Map<String, Double> priceMapFromQueue = priceDto.getPriceMap();
        Map<String, Double> changedMapFromQueue = priceDto.getChangedMap();
        assert(priceMapFromQueue.get("a").equals(1.0));
        assert(priceMapFromQueue.get("b").equals(2.0));
        assert(changedMapFromQueue.get("a").equals(1.0));
        assert(changedMapFromQueue.get("b").equals(2.0));
    }
}