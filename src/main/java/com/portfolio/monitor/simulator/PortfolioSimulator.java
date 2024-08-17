package com.portfolio.monitor.simulator;

import java.text.DecimalFormat;
import java.util.Random;

public class PortfolioSimulator {
    public static void main(String[] args) throws InterruptedException {
        // Constants
        double initialStockPrice = 190.0;
        double strikePrice = 195.0;
        double volatility = 0.2;
        double riskFreeRate = 0.02;
        double timeToMaturity = 5.0 / 252; // 5 trading days as a fraction of a year
        int secondsIn5Days = (int) (5 * 6.5 * 60 * 60); // 5 trading days (6.5 hours/day)

        // Decimal format for 2 decimal places
        DecimalFormat df = new DecimalFormat("0.00");

        // Initialize stock price
        double currentStockPrice = initialStockPrice;

        // Random number generator using Guava
        Random random = new Random();

        // Simulate for each second
        for (int i = 0; i < secondsIn5Days; i++) {
            // Calculate option prices
            double d1 = (Math.log(currentStockPrice / strikePrice) +
                    (riskFreeRate + 0.5 * volatility * volatility) * timeToMaturity) /
                    (volatility * Math.sqrt(timeToMaturity));
            double d2 = d1 - volatility * Math.sqrt(timeToMaturity);

            double callPrice = currentStockPrice * cumulativeNormal(d1) -
                    strikePrice * Math.exp(-riskFreeRate * timeToMaturity) * cumulativeNormal(d2);
            double putPrice = strikePrice * Math.exp(-riskFreeRate * timeToMaturity) * cumulativeNormal(-d2) -
                    currentStockPrice * cumulativeNormal(-d1);

            // Print prices
            System.out.println("Stock Price: " + df.format(currentStockPrice) +
                    ", Call Option Price: " + df.format(callPrice) +
                    ", Put Option Price: " + df.format(putPrice));

            // Generate random relative change for stock price
            double epsilon = random.nextGaussian();
            double deltaT = 1.0 / 7257600; // Time increment in years (1 second)
            double relativeChange = (riskFreeRate - 0.5 * volatility * volatility) * deltaT +
                    volatility * epsilon * Math.sqrt(deltaT);

            // Update stock price
            currentStockPrice *= Math.exp(relativeChange);

            // Wait for 1 second
            Thread.sleep(1000);
        }
    }

    // Cumulative distribution function for standard normal distribution
    public static double cumulativeNormal(double x) {
        return 0.5 * (1.0 + erf(x / Math.sqrt(2.0)));
    }

    // Approximation of the error function
    public static double erf(double z) {
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
