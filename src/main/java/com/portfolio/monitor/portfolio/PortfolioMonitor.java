package com.portfolio.monitor.portfolio;

import com.portfolio.monitor.model.dto.PriceDto;
import com.portfolio.monitor.position.PositionHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class PortfolioMonitor {

    private final AtomicInteger seqAtomic = new AtomicInteger(0);

    private final DecimalFormat df = new DecimalFormat("0.00");

    private final PositionHolder positionHolder;

    public PortfolioMonitor(PositionHolder positionHolder) {
        this.positionHolder = positionHolder;
    }

    public void display(PriceDto priceDto) {
        Map<String, Double> priceMap = priceDto.getPriceMap();
        int seq = seqAtomic.incrementAndGet();
        if (seq == 1) {
            System.out.format("## %s Market Data Update", seq);
        } else {
            System.out.format("%n%n%n## %s Market Data Update", seq);
        }
        priceDto.getChangedMap().forEach((key, value) -> System.out.format("%n%s change to %s", key, df.format(value)));
        System.out.format("%n%n## Portfolio");
        System.out.format("%n%-24s%10s%16s%16s", "symbol", "price", "qty", "value");
        AtomicReference<Double> totalValue = new AtomicReference<>(0.0);
        positionHolder.getPositions().forEach(positionDto -> {
            String symbol = positionDto.getSymbol();
            Integer qty = positionDto.getQuantity();
            BigDecimal priceBigDecimal = BigDecimal.valueOf(priceMap.get(positionDto.getSymbol()));
            Double price = priceBigDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
            BigDecimal valueBigDecimal = BigDecimal.valueOf(qty * price);
            Double value = valueBigDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
            totalValue.updateAndGet(v -> v + value);
            System.out.format("%n%-24s%10s%16s%16s", symbol, df.format(price), qty, df.format(value));
        });
        System.out.format("%n%n%-24s%10s%16s%16s", "#Total portfolio", "", "", df.format(totalValue.get()));
    }
}
