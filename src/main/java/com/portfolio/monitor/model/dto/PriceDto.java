package com.portfolio.monitor.model.dto;

import java.util.Map;

public class PriceDto {

    private Map<String, Double> changedMap;

    private Map<String, Double> priceMap;

    public PriceDto(Map<String, Double> priceMap, Map<String, Double> changedMap) {
        this.priceMap = priceMap;
        this.changedMap = changedMap;
    }

    public Map<String, Double> getChangedMap() {
        return changedMap;
    }

    public void setChangedMap(Map<String, Double> changedMap) {
        this.changedMap = changedMap;
    }

    public Map<String, Double> getPriceMap() {
        return priceMap;
    }

    public void setPriceMap(Map<String, Double> priceMap) {
        this.priceMap = priceMap;
    }
}
