package pl.parsers.nbp.exchangerate;

import pl.parsers.nbp.datasource.Request;

import java.time.LocalDate;
import java.util.Currency;

public class NbpExchangeRatesRequest implements Request {

	private final Currency currency;
	private final LocalDate startDate;
	private final LocalDate endDate;
	private static final String CONTENT_TYPE_XML = "application/xml; charset=utf-8";
	private static final int READ_TIMEOUT = 500;
	private static final int CONNECT_TIMEOUT = 500;

	static final String API_URL = "http://api.nbp.pl/api/exchangerates/rates/C/%s/%s/%s/";

	private NbpExchangeRatesRequest(final Currency currency, final LocalDate startDate, final LocalDate endDate) {
		this.currency = currency;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Override
	public String getAddress() {
		return String.format(API_URL, currency, startDate, endDate);
	}

	@Override
	public int getReadTimeout() {
		return READ_TIMEOUT;
	}

	@Override
	public int getConnectTimeout() {
		return CONNECT_TIMEOUT;
	}

	@Override
	public String getContentType() {
		return CONTENT_TYPE_XML;
	}

	public static NbpExchangeRatesRequest of(final Currency currency, final LocalDate startDate, final LocalDate endDate) {
		return new NbpExchangeRatesRequest(currency, startDate, endDate);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		NbpExchangeRatesRequest that = (NbpExchangeRatesRequest) o;

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
}

