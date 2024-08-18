package com.portfolio.monitor.position;

import com.portfolio.monitor.model.dto.PositionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class PositionHolder {

    private static final Logger log = LoggerFactory.getLogger(PositionHolder.class);

    private static final String HEAD_OF_POSITION_FILE = "symbol,positionSize";

    private final List<PositionDto> positions = new ArrayList<>();;

    public List<PositionDto> getPositions() {
        if (positions.isEmpty()) {
            initPositions();
        }
        return positions;
    }

    private void initPositions() {
        String path = "position.csv";
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            List<String[]> rows = stream
                    .filter(line -> !line.contains(HEAD_OF_POSITION_FILE))
                    .map(line -> line.split(","))
                    .collect(Collectors.toList());

            // 处理解析后的数据
            for (String[] row : rows) {
                PositionDto positionDto = new PositionDto();
                positionDto.setSymbol(row[0]);
                positionDto.setQuantity(Integer.parseInt(row[1]));
                positions.add(positionDto);
            }
        } catch (IOException e) {
            log.error("Failed to read position file.", e);
        }
    }
}
