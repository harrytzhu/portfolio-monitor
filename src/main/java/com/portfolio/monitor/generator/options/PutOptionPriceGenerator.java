package com.portfolio.monitor.generator.options;

public class PutOptionPriceGenerator extends AbstractOptionPriceGenerator {

    public PutOptionPriceGenerator(Double riskFreeRate) {
        super(riskFreeRate);
    }

    @Override
    Double getEuropeanOptionPrice(Double stockPrice, Double strikePrice, Double timeToMaturity, Double d1, Double d2) {
        return strikePrice * Math.exp(-riskFreeRate * timeToMaturity) * cumulativeNormal(-d2) -
                stockPrice * cumulativeNormal(-d1);
    }
}
