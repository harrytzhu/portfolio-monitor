package com.portfolio.monitor.generator;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

@Component
public class GeometricBrownianMotionPriceGenerator {

    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        GeometricBrownianMotionPriceGenerator generator = new GeometricBrownianMotionPriceGenerator();
        // Parameters
        double mu = 0.15; // Expected return
        double sigma = 0.3; // Annualized standard deviation
        double deltaT = 1.0 / 7257600; // Time increment in years (1 week)

        // Generate random relative change
        double stockPrice = 190.0;
        DecimalFormat df = new DecimalFormat("#.00");
        while (true) {
            double relativeChange = generator.generateRelativeChange(mu, sigma, deltaT);
            stockPrice *= (1 + relativeChange);
            if (stockPrice < 0) {
                stockPrice = 0;
            }
            System.out.println("Relative Change: " + relativeChange);
            System.out.println("Stock price: " + df.format(stockPrice));
            Thread.sleep(1000);
        }
    }

    /*public double generateStockPrice(double mu, double sigma, double deltaT) {
        DecimalFormat df = new DecimalFormat("#.00");
        double stockPrice = 190.0;
        double relativeChange = generateRelativeChange(mu, sigma, deltaT);
        stockPrice *= (1 + relativeChange);
        if (stockPrice < 0) {
            stockPrice = 0;
        }
        return df.format(stockPrice);
    }*/

    private double generateRelativeChange(double mu, double sigma, double deltaT) {
        // Standard normal distribution
        double epsilon = random.nextGaussian();

        // Geometric Brownian Motion formula
        return mu * deltaT + sigma * epsilon * Math.sqrt(deltaT);
    }
}
