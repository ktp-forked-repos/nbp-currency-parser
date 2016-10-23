package pl.parsers.nbp.exchangerate.calculator;

import java.math.BigDecimal;

public interface Calculator {

	int BIG_DECIMAL_SCALE = 4;

	BigDecimal calculate();
}
