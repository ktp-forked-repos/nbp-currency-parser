package pl.parsers.nbp.exchangerate.calculator;

import pl.parsers.nbp.exchangerate.Rates;

import java.math.BigDecimal;
import java.util.List;

import static java.math.RoundingMode.HALF_DOWN;
import static java.math.RoundingMode.HALF_UP;

public class StandardDeviationCalculator implements Calculator {

	private final Rates rates;

	private StandardDeviationCalculator(final Rates rates) {
		this.rates = rates;
	}

	public BigDecimal calculate() {
		final List<BigDecimal> elements = rates.getRates();
		if (elements.isEmpty()) {
			return BigDecimal.ZERO;
		}

		final BigDecimal average = average();
		final BigDecimal deviationSummary = elements.stream()
				.map(i -> i.subtract(average).pow(2))
				.reduce(BigDecimal::add).get();

		final BigDecimal averageDeviation = deviationSummary.divide(BigDecimal.valueOf(elements.size()), HALF_DOWN);
		final double sqrt = Math.sqrt(averageDeviation.doubleValue());
		return new BigDecimal(sqrt).setScale(BIG_DECIMAL_SCALE, HALF_UP);
	}

	private BigDecimal average() {
		return AverageCalculator.of(rates).calculate();
	}

	public static StandardDeviationCalculator of(final Rates rates) {
		return new StandardDeviationCalculator(rates);
	}
}
