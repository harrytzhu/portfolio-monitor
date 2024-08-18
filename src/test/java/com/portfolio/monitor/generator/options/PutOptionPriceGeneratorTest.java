package com.portfolio.monitor.generator.options;

import com.portfolio.monitor.constant.OptionTypeEnum;
import com.portfolio.monitor.model.dto.OptionDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PutOptionPriceGeneratorTest {

    @Test
    void generate() {
        PutOptionPriceGenerator putOptionPriceGenerator = new PutOptionPriceGenerator(0.02);
        OptionDto optionDto = new OptionDto();
        optionDto.setOptionType(OptionTypeEnum.PUT);
        optionDto.setVolatility(0.2);
        optionDto.setStrikePrice(105.0);
        optionDto.setTimeToMaturity(5.0/252);
        optionDto.setStockPrice(100.0);
        assertTrue(putOptionPriceGenerator.generate(optionDto) > 0.0);
    }
}