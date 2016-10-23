package pl.parsers.nbp.exchangerate.calculator;

import pl.parsers.nbp.exchangerate.Rates;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class AverageCalculator implements Calculator {

	private final Rates rates;

	private AverageCalculator(final Rates rates) {
		this.rates = rates;
	}

	public BigDecimal calculate() {

		final List<BigDecimal> elements = rates.getRates();
		if (elements.isEmpty()) {
			return BigDecimal.ZERO;
		}
		return elements.stream()
				.reduce(BigDecimal::add)
				.get()
				.divide(new BigDecimal(elements.size()), RoundingMode.HALF_DOWN)
				.setScale(BIG_DECIMAL_SCALE, RoundingMode.HALF_UP);
	}

	public static AverageCalculator of(final Rates rates) {
		return new AverageCalculator(rates);
	}


}
