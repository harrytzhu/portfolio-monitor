package com.portfolio.monitor.model.vo;

import com.portfolio.monitor.constant.OptionTypeEnum;

public class OptionPriceVo {

    private String symbol;

    private OptionTypeEnum type;

    private Double price;

    private Double strikePrice;

    private Double timeToMaturity;

    public Double getTimeToMaturity() {
        return timeToMaturity;
    }
    public void setTimeToMaturity(Double timeToMaturity) {
        this.timeToMaturity = timeToMaturity;
    }
    public Double getStrikePrice() {
        return strikePrice;
    }
    public void setStrikePrice(Double strikePrice) {
        this.strikePrice = strikePrice;
    }

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public OptionTypeEnum getType() {
        return type;
    }
    public void setType(OptionTypeEnum type) {
        this.type = type;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
