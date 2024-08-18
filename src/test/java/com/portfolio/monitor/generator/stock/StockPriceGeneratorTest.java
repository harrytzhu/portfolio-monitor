package com.portfolio.monitor.generator.stock;

import com.portfolio.monitor.model.dto.StockDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockPriceGeneratorTest {

    @Test
    void generate_positiveExpectedReturn() {
        StockPriceGenerator stockPriceGenerator = new StockPriceGenerator();
        StockDto stockDto = new StockDto();
        stockDto.setSymbol("BRK.B");
        stockDto.setExpectedReturn(0.05);
        stockDto.setVolatility(0.2);
        stockDto.setPrice(440.0);
        double currentPrice = stockPriceGenerator.generate(stockDto);
        assertTrue(currentPrice > 0.0);
    }
}