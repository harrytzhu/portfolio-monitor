package com.portfolio.monitor.model.dto;

import com.portfolio.monitor.constant.OptionTypeEnum;

public class OptionDto {

    private String symbol;

    private String stockSymbol;

    private Double volatility;

    private Double strikePrice;

    private Double timeToMaturity;

    private Double stockPrice;

    private Double price;

    private OptionTypeEnum optionType;

    public Double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(Double stockPrice) {
        this.stockPrice = stockPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public Double getVolatility() {
        return volatility;
    }

    public void setVolatility(Double volatility) {
        this.volatility = volatility;
    }

    public Double getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(Double strikePrice) {
        this.strikePrice = strikePrice;
    }

    public Double getTimeToMaturity() {
        return timeToMaturity;
    }

    public void setTimeToMaturity(Double timeToMaturity) {
        this.timeToMaturity = timeToMaturity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public OptionTypeEnum getOptionType() {
        return optionType;
    }

    public void setOptionType(OptionTypeEnum optionType) {
        this.optionType = optionType;
    }
}
