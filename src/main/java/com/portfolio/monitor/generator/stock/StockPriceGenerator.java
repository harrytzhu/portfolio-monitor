package com.portfolio.monitor.generator.stock;

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
        double deltaT = 1.0 / 7257600; // Time increment in years (1 week)

        // Generate random relative change
        DecimalFormat df = new DecimalFormat("#.00");
        double relativeChange = generateRelativeChange(mu, sigma, deltaT);
        double currentPrice = stockDto.getPrice();
        currentPrice *= (1 + relativeChange);
        if (currentPrice < 0.0) {
            currentPrice = 0.0;
        }
        System.out.println("Relative Change: " + relativeChange);
        System.out.println("Stock price: " + df.format(currentPrice));
        return currentPrice;
    }

    private double generateRelativeChange(double mu, double sigma, double deltaT) {
        // Standard normal distribution
        double epsilon = random.nextGaussian();

        // Geometric Brownian Motion formula
        return mu * deltaT + sigma * epsilon * Math.sqrt(deltaT);
    }
}
