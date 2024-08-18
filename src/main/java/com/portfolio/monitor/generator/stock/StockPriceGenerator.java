package com.portfolio.monitor.generator.stock;

import com.portfolio.monitor.constant.CommonConstant;
import com.portfolio.monitor.model.dto.StockDto;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Random;

@Component
public class StockPriceGenerator {

    private static final Random random = new Random();

    public Double generate(StockDto stockDto) {
        // Parameters
        double mu = stockDto.getExpectedReturn(); // Expected return
        double sigma = stockDto.getVolatility(); // Annualized standard deviation
        double deltaT = 1.0 / CommonConstant.TRADING_SECONDS_OF_A_YEAR; // Time increment in years (1 second)

        // Generate random relative change
        double relativeChange = generateRelativeChange(mu, sigma, deltaT);
        double currentPrice = stockDto.getPrice();
        currentPrice *= (1 + relativeChange);
        if (currentPrice < 0.0) {
            currentPrice = 0.0;
        }
        //DecimalFormat df = new DecimalFormat("#.00");
        //System.out.printf("Symbol: %s, Relative Change: %s, Stock price: %s%n", stockDto.getSymbol(), relativeChange, currentPrice);
        return currentPrice;
    }

    private double generateRelativeChange(double mu, double sigma, double deltaT) {
        // Standard normal distribution
        double epsilon = random.nextGaussian();

        // Geometric Brownian Motion formula
        return mu * deltaT + sigma * epsilon * Math.sqrt(deltaT);
    }
}
