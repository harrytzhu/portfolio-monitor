package com.portfolio.monitor.publisher;

import com.portfolio.monitor.constant.CommonConstant;
import com.portfolio.monitor.constant.OptionTypeEnum;
import com.portfolio.monitor.generator.options.OptionPriceGenerator;
import com.portfolio.monitor.generator.options.OptionPriceGeneratorFactory;
import com.portfolio.monitor.generator.stock.StockPriceGenerator;
import com.portfolio.monitor.model.dto.OptionDto;
import com.portfolio.monitor.model.dto.PriceDto;
import com.portfolio.monitor.model.dto.StockDto;
import com.portfolio.monitor.model.entity.OptionEntity;
import com.portfolio.monitor.model.entity.StockEntity;
import com.portfolio.monitor.queue.QueueEngine;
import com.portfolio.monitor.repository.OptionRepository;
import com.portfolio.monitor.repository.StockRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PricePublisherTimerTask extends TimerTask {

    private Map<String, StockDto> stockMap;

    private List<OptionDto> optionDtos;

    private final StockRepository stockRepository;

    private final OptionRepository optionRepository;

    private final StockPriceGenerator stockPriceGenerator;

    private final OptionPriceGeneratorFactory optionPriceGeneratorFactory;

    private final QueueEngine queueEngine;

    public PricePublisherTimerTask(StockRepository stockRepository, OptionRepository optionRepository,
            StockPriceGenerator stockPriceGenerator, OptionPriceGeneratorFactory optionPriceGeneratorFactory,
            QueueEngine queueEngine) {
        this.stockRepository = stockRepository;
        this.optionRepository = optionRepository;
        this.stockPriceGenerator = stockPriceGenerator;
        this.optionPriceGeneratorFactory = optionPriceGeneratorFactory;
        this.queueEngine = queueEngine;
    }

    @Override
    public void run() {
        // Initialize data if it is null
        if (stockMap == null) {
            initializeStockDtos();
        }
        if (optionDtos == null) {
            initializeOptionDtos();
        }

        // Generate prices
        Map<String, Double> priceMap = new HashMap<>();
        Map<String, Double> changedMap = new HashMap<>();
        for (Map.Entry<String, StockDto> entry : stockMap.entrySet()) {
            StockDto stockDto = entry.getValue();
            Double price = stockDto.getPrice();
            Double newPrice = stockPriceGenerator.generate(stockDto);
            if (Math.abs(price - newPrice) > CommonConstant.STOCK_PRICE_CHANGE_THRESHOLD) {
                changedMap.put(stockDto.getSymbol(), newPrice);
            }
            stockDto.setPrice(newPrice);
            priceMap.put(stockDto.getSymbol(), stockDto.getPrice());
        }
        for (OptionDto optionDto : optionDtos) {
            optionDto.setStockPrice(stockMap.get(optionDto.getStockSymbol()).getPrice());
            OptionPriceGenerator optionPriceGenerator = optionPriceGeneratorFactory.getOptionPriceGenerator(optionDto.getOptionType());
            optionDto.setPrice(optionPriceGenerator.generate(optionDto));
            priceMap.put(optionDto.getSymbol(), optionDto.getPrice());
        }
        PriceDto priceDto = new PriceDto(priceMap, changedMap);
        queueEngine.publish(priceDto);
    }

    private void initializeStockDtos() {
        stockMap = new HashMap<>();
        List<StockEntity> stockEntities = stockRepository.findAll();
        for (StockEntity stockEntity : stockEntities) {
            StockDto stockDto = new StockDto();
            stockDto.setSymbol(stockEntity.getSymbol());
            stockDto.setExpectedReturn(stockEntity.getExpectedReturn());
            stockDto.setVolatility(stockEntity.getVolatility());
            stockDto.setPrice(stockEntity.getInitialPrice());
            stockMap.put(stockEntity.getSymbol(), stockDto);
        }
    }

    private void initializeOptionDtos() {
        optionDtos = new ArrayList<>();
        List<OptionEntity> optionEntities = optionRepository.findAll();
        for (OptionEntity optionEntity : optionEntities) {
            OptionDto optionDto = new OptionDto();
            optionDto.setSymbol(optionEntity.getSymbol());
            optionDto.setStockSymbol(optionEntity.getStockSymbol());
            optionDto.setVolatility(optionEntity.getVolatility());
            optionDto.setStrikePrice(optionEntity.getStrikePrice());
            optionDto.setTimeToMaturity(1.0D * optionEntity.getDaysToMaturity() / CommonConstant.TRADING_DAYS_OF_A_YEAR);
            optionDto.setStockPrice(stockMap.get(optionDto.getStockSymbol()).getPrice());
            optionDto.setOptionType(OptionTypeEnum.valueOf(optionEntity.getOptionType()));
            optionDtos.add(optionDto);
        }
    }
}
