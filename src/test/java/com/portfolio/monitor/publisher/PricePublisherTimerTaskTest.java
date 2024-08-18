package com.portfolio.monitor.publisher;

import com.portfolio.monitor.generator.options.OptionPriceGeneratorFactory;
import com.portfolio.monitor.generator.stock.StockPriceGenerator;
import com.portfolio.monitor.model.entity.OptionEntity;
import com.portfolio.monitor.model.entity.StockEntity;
import com.portfolio.monitor.queue.QueueEngine;
import com.portfolio.monitor.repository.OptionRepository;
import com.portfolio.monitor.repository.StockRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PricePublisherTimerTaskTest {

    @Test
    void run() {
        StockRepository stockRepository = mock(StockRepository.class);
        List<StockEntity> stockEntities = new ArrayList<>();
        StockEntity stockEntity = new StockEntity();
        stockEntity.setId("1");
        stockEntity.setSymbol("AAPL");
        stockEntity.setExpectedReturn(0.01);
        stockEntity.setVolatility(0.02);
        stockEntity.setInitialPrice(100.0);
        stockEntities.add(stockEntity);
        when(stockRepository.findAll()).thenReturn(stockEntities);
        OptionRepository optionRepository = mock(OptionRepository.class);
        List<OptionEntity> optionEntities = new ArrayList<>();
        OptionEntity optionEntity = new OptionEntity();
        optionEntity.setId("1");
        optionEntity.setSymbol("AAPL-AUG-2024-225-C");
        optionEntity.setStockSymbol("AAPL");
        optionEntity.setVolatility(0.02);
        optionEntity.setOptionType("CALL");
        optionEntity.setStrikePrice(100.0);
        optionEntity.setDaysToMaturity(30);
        optionEntities.add(optionEntity);
        when(optionRepository.findAll()).thenReturn(optionEntities);
        PricePublisherTimerTask pricePublisherTimerTask = new PricePublisherTimerTask(stockRepository, optionRepository, new StockPriceGenerator(), new OptionPriceGeneratorFactory(0.02), new QueueEngine());
        assertDoesNotThrow(pricePublisherTimerTask::run);
    }

}