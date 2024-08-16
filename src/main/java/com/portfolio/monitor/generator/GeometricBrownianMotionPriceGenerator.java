package com.portfolio.monitor.generator;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@Component
public class GeometricBrownianMotionPriceGenerator {

    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        // Parameters
        double mu = 0.05; // Expected return
        double sigma = 0.2; // Annualized standard deviation
        double deltaT = 1.0 / 7257600; // Time increment in years (1 second)

        // Generate random relative change
        double stockPrice = 190.0;
        for (int i = 1; i <= 10; i++) {
            double relativeChange = generateRelativeChange(mu, sigma, deltaT);
            stockPrice += relativeChange;
            if (stockPrice < 0) {
                stockPrice = 0;
            }
            System.out.println("Relative Change: " + relativeChange);
            System.out.println("Stock price: " + stockPrice);
            Thread.sleep(1000);
        }
    }

    public static double generateRelativeChange(double mu, double sigma, double deltaT) {
        // Standard normal distribution
        double epsilon = random.nextGaussian();

        // Geometric Brownian Motion formula
        return mu * deltaT + sigma * epsilon * Math.sqrt(deltaT);
    }
}
