package com.portfolio.monitor.generator;

import com.portfolio.monitor.generator.options.OptionPriceGeneratorFactory;
import com.portfolio.monitor.generator.stock.StockPriceGenerator;
import com.portfolio.monitor.model.dto.StockDto;
import com.portfolio.monitor.model.vo.SecurityPriceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SecurityPriceGenerator {

    private StockPriceGenerator stockPriceGenerator;

    private OptionPriceGeneratorFactory optionPriceGeneratorFactory;

    @Autowired
    public SecurityPriceGenerator(
            StockPriceGenerator stockPriceGenerator,
            OptionPriceGeneratorFactory optionPriceGeneratorFactory
    ) {
        this.stockPriceGenerator = stockPriceGenerator;
        this.optionPriceGeneratorFactory = optionPriceGeneratorFactory;
    }

    public Double generate(StockDto stockDto) {
        return stockPriceGenerator.generate(stockDto);
    }
}
