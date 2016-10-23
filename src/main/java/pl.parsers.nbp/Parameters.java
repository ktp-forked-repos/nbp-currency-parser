package pl.parsers.nbp;


import java.time.LocalDate;
import java.util.Currency;

class Parameters {
	private final Currency currency;
	private final LocalDate startDate;
	private final LocalDate endDate;

	private Parameters(final Currency currency, final LocalDate startDate, final LocalDate endDate) {
		this.currency = currency;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public static Parameters of(final Currency currency, final LocalDate startDate, final LocalDate endDate) {
		return new Parameters(currency, startDate, endDate);
	}

	Currency getCurrency() {
		return currency;
	}

	LocalDate getStartDate() {
		return startDate;
	}

	LocalDate getEndDate() {
		return endDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Parameters that = (Parameters) o;

		if (!currency.equals(that.currency)) return false;
		if (!startDate.equals(that.startDate)) return false;
		return endDate.equals(that.endDate);

	}

	@Override
	public int hashCode() {
		int result = currency.hashCode();
		result = 31 * result + startDate.hashCode();
		result = 31 * result + endDate.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Parameters{" +
				"currency=" + currency +
				", startDate=" + startDate +
				", endDate=" + endDate +
				'}';
	}
}
