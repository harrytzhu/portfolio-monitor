package com.portfolio.monitor.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "t_option")
public class OptionEntity {

    @Id
    @GeneratedValue
    private String id;

    private String symbol;

    private String stockSymbol;

    private String optionType;

    private Double volatility;

    private Double strikePrice;

    private Integer daysToMaturity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
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

    public Integer getDaysToMaturity() {
        return daysToMaturity;
    }

    public void setDaysToMaturity(Integer daysToMaturity) {
        this.daysToMaturity = daysToMaturity;
    }
}
