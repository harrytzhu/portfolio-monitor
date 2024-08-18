package com.portfolio.monitor.generator.options;

import com.portfolio.monitor.constant.OptionTypeEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class OptionPriceGeneratorFactoryTest {

    @Test
    void getOptionPriceGenerator() {
        OptionPriceGeneratorFactory optionPriceGeneratorFactory = new OptionPriceGeneratorFactory(0.02);
        OptionPriceGenerator optionPriceGenerator = optionPriceGeneratorFactory.getOptionPriceGenerator(OptionTypeEnum.CALL);
        assertTrue(optionPriceGenerator instanceof CallOptionPriceGenerator);
        optionPriceGenerator = optionPriceGeneratorFactory.getOptionPriceGenerator(OptionTypeEnum.PUT);
        assertTrue(optionPriceGenerator instanceof PutOptionPriceGenerator);
    }
}