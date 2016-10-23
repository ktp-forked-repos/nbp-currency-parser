package pl.parsers.nbp.exchangerate;

import java.math.BigDecimal;
import java.util.List;

public class Rates {

	private final List<BigDecimal> rates;

	private Rates(final List<BigDecimal> rates) {
		this.rates = rates;
	}

	public List<BigDecimal> getRates() {
		return rates;
	}

	public static Rates of(final List<BigDecimal> rates) {
		return new Rates(rates);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Rates rates1 = (Rates) o;

		return rates.equals(rates1.rates);

	}

	@Override
	public int hashCode() {
		return rates.hashCode();
	}

	@Override
	public String toString() {
		return "Rates{" +
				"rates=" + rates +
				'}';
	}
}
