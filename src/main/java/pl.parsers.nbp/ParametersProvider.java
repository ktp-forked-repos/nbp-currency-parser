package pl.parsers.nbp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

class ParametersProvider {

	private final String[] args;

	private static final int REQUIRED_SIZE = 3;

	private static final int CURRENCY_INDEX = 0;
	private static final int START_DATE_INDEX = 1;
	private static final int END_DATE_INDEX = 2;

	//	USD, EUR, CHF, GBP
	private static final Set<Currency> ALLOWED_CURRENCIES = new HashSet<>(Arrays.asList(Currency.getInstance("USD"), Currency.getInstance("EUR"), Currency.getInstance("CHF"), Currency.getInstance("GBP")));

	private ParametersProvider(final String[] args) {
		this.args = args;
	}

	public static ParametersProvider of(final String[] args) {
		return new ParametersProvider(args);
	}

	Parameters getParameters() {
		check();
		return Parameters.of(getCurrency(), getStartDate(), getEndDate());
	}

	private Currency getCurrency() {
		checkCurrency();
		return Currency.getInstance(args[CURRENCY_INDEX]);
	}

	private LocalDate getStartDate() {
		return parseDate(args[START_DATE_INDEX]);
	}

	private LocalDate getEndDate() {
		return parseDate(args[END_DATE_INDEX]);
	}

	private void check() {
		checkLength();
		checkCurrency();
	}

	private void checkLength() {
		if (args.length != REQUIRED_SIZE) {
			throw new IllegalArgumentException("Usage: currency startDate(yyyy-mm-dd) endDate(yyyy-mm-dd)");
		}
	}

	private void checkCurrency() {
		isIso4217Currency();
		isAllowedCurrency();
	}

	private void isIso4217Currency() {
		final Currency currency = Currency.getInstance(args[CURRENCY_INDEX]);
		if (currency == null) {
			throw new IllegalArgumentException("Unsupported currency code.");
		}
	}


	private void isAllowedCurrency() {
		final Currency currency = Currency.getInstance(args[CURRENCY_INDEX]);
		if (!ALLOWED_CURRENCIES.contains(currency)) {
			throw new IllegalArgumentException("Currency is not allowed. Allowed values are: " + ALLOWED_CURRENCIES);
		}
	}

	private LocalDate parseDate(final String dateString) {

		try {
			return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		} catch (Exception ex) {
			throw new IllegalArgumentException("Date value " + args[1] + " is incorrect");
		}

	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ParametersProvider that = (ParametersProvider) o;

		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		return Arrays.equals(args, that.args);

	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(args);
	}

	@Override
	public String toString() {
		return "ParametersProvider{" +
				"args=" + Arrays.toString(args) +
				'}';
	}
}
