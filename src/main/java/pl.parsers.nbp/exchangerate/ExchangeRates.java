package pl.parsers.nbp.exchangerate;

public class ExchangeRates {

	private final Rates bids;
	private final Rates asks;

	private ExchangeRates(final Rates bids, final Rates asks) {
		this.bids = bids;
		this.asks = asks;
	}

	public Rates getBids() {
		return bids;
	}

	public Rates getAsks() {
		return asks;
	}

	public static ExchangeRates of(final Rates bids, final Rates asks) {
		return new ExchangeRates(bids, asks);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ExchangeRates that = (ExchangeRates) o;

		if (!bids.equals(that.bids)) return false;
		return asks.equals(that.asks);

	}

	@Override
	public int hashCode() {
		int result = bids.hashCode();
		result = 31 * result + asks.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "ExchangeRates{" +
				"bids=" + bids +
				", asks=" + asks +
				'}';
	}
}


