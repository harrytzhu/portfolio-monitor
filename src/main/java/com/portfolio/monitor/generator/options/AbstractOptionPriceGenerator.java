package com.portfolio.monitor.generator.options;

import com.portfolio.monitor.model.dto.OptionDto;

public abstract class AbstractOptionPriceGenerator implements OptionPriceGenerator {

    protected final Double riskFreeRate;

    abstract Double getEuropeanOptionPrice(Double stockPrice, Double strikePrice, Double timeToMaturity, Double d1, Double d2);

    protected AbstractOptionPriceGenerator(Double riskFreeRate) {
        this.riskFreeRate = riskFreeRate;
    }

    @Override
    public Double generate(OptionDto optionDto) {
        Double stockPrice = optionDto.getStockPrice();
        Double strikePrice = optionDto.getStrikePrice();
        Double timeToMaturity = optionDto.getTimeToMaturity();
        Double volatility = optionDto.getVolatility();
        double d1 = (Math.log(stockPrice / strikePrice) +
                (riskFreeRate + 0.5 * volatility * volatility) * timeToMaturity) /
                (volatility * Math.sqrt(timeToMaturity));
        double d2 = d1 - volatility * Math.sqrt(timeToMaturity);
        return getEuropeanOptionPrice(stockPrice, strikePrice, timeToMaturity, d1, d2);
    }

    protected double cumulativeNormal(double x) {
        return 0.5 * (1.0 + erf(x / Math.sqrt(2.0)));
    }

    protected double erf(double z) {
        double t = 1.0 / (1.0 + 0.5 * Math.abs(z));
        double tau = t * Math.exp(-z * z - 1.26551223 +
                t * (1.00002368 +
                        t * (0.37409196 +
                                t * (0.09678418 +
                                        t * (-0.18628806 +
                                                t * (0.27886807 +
                                                        t * (-1.13520398 +
                                                                t * (1.48851587 +
                                                                        t * (-0.82215223 +
                                                                                t * 0.17087277)))))))));
        return z >= 0 ? 1 - tau : tau - 1;
    }
}
