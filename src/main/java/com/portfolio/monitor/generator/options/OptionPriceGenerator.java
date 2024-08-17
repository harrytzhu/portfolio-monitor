package com.portfolio.monitor.generator.options;

import com.portfolio.monitor.model.dto.OptionDto;

public interface OptionPriceGenerator {

    Double generate(OptionDto optionDto);
}
