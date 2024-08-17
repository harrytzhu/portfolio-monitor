package com.portfolio.monitor.generator.options;

public class CallOptionPriceGenerator extends AbstractOptionPriceGenerator {
    protected CallOptionPriceGenerator(Double riskFreeRate) {
        super(riskFreeRate);
    }

    @Override
    Double getEuropeanOptionPrice(Double stockPrice, Double strikePrice, Double timeToMaturity, Double d1, Double d2) {
        return stockPrice * cumulativeNormal(d1) -
                strikePrice * Math.exp(-riskFreeRate * timeToMaturity) * cumulativeNormal(d2);
    }
}
