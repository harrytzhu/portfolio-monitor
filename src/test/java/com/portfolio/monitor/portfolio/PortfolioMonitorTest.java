package com.portfolio.monitor.portfolio;

import com.portfolio.monitor.model.dto.PriceDto;
import com.portfolio.monitor.position.PositionHolder;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;

class PortfolioMonitorTest {

    @Test
    void display() {
        PositionHolder positionHolder = new PositionHolder();
        PortfolioMonitor portfolioMonitor = new PortfolioMonitor(positionHolder);
        Map<String, Double> priceMap = new HashMap<>();
        priceMap.put("AAPL", 100.0);
        priceMap.put("AAPL-AUG-2024-225-C", 100.0);
        priceMap.put("AAPL-AUG-2024-225-P", 100.0);
        priceMap.put("TESLA", 300.0);
        priceMap.put("TESLA-SEP-2024-215-C", 300.0);
        priceMap.put("TESLA-SEP-2024-215-P", 300.0);
        Map<String, Double> changedMap = new HashMap<>();
        changedMap.put("AAPL", 110.0);
        changedMap.put("TESLA", 310.0);
        PriceDto priceDto = new PriceDto(priceMap, changedMap);
        assertDoesNotThrow(() -> portfolioMonitor.display(priceDto));
    }
}