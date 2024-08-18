package com.portfolio.monitor.generator.options;

import com.portfolio.monitor.constant.OptionTypeEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OptionPriceGeneratorFactory {

    private double riskFreeRate;

    public OptionPriceGeneratorFactory(@Value("${app.risk-free-rate}") double riskFreeRate) {
        this.riskFreeRate = riskFreeRate;
    }

    public OptionPriceGenerator getOptionPriceGenerator(OptionTypeEnum optionType) {
        switch (optionType) {
            case CALL:
                return new CallOptionPriceGenerator(riskFreeRate);
            case PUT:
                return new PutOptionPriceGenerator(riskFreeRate);
            default:
                throw new IllegalArgumentException("Unknown option type: " + optionType);
        }
    }
}
