package com.portfolio.monitor.generator.options;

import com.portfolio.monitor.constant.OptionTypeEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OptionPriceGeneratorFactory {

    @Value("${app.risk-free-rate}")
    private double riskFreeRate;

    public OptionPriceGenerator getOptionPriceGenerator(OptionTypeEnum optionType) {
        switch (optionType) {
            case CALL:
                return new CallOptionPriceGenerator(riskFreeRate);
            case PUT:
                return new PutOptionPriceGenerator(riskFreeRate);
            default:
                return null;
        }
    }
}
