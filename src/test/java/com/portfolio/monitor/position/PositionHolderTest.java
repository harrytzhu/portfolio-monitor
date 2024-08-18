package com.portfolio.monitor.position;

import com.portfolio.monitor.model.dto.PositionDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PositionHolderTest {

    @Test
    void getPosition() {
        PositionHolder positionHolder = new PositionHolder();
        List<PositionDto> positions = positionHolder.getPositions();
        assertFalse(positions.isEmpty());
        positions.forEach(position -> assertTrue(position.getSymbol() != null && !position.getSymbol().isEmpty() && position.getQuantity() != 0));
    }
}