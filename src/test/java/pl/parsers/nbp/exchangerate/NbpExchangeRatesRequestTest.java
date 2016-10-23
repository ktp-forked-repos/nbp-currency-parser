package pl.parsers.nbp.exchangerate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Currency;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(Parameterized.class)
public class NbpExchangeRatesRequestTest {

	private final String currency;
	private final String startDate;
	private final String endDate;

	public NbpExchangeRatesRequestTest(final String currency, final String startDate, final String endDate) {
		this.currency = currency;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> parameters() {
		return Arrays.asList(
				new Object[][]{
						{"USD", "2001-01-01", "2001-02-01"},
						{"GBP", "1999-12-12", "2000-01-12"},
						{"CHF", "2016-10-01", "2016-10-12"}
				}
		);
	}

	@Test
	public void should_create_url_address() throws Exception {

		final NbpExchangeRatesRequest objectUnderTest = NbpExchangeRatesRequest.of(Currency.getInstance(currency), LocalDate.parse(startDate), LocalDate.parse(endDate));
		final String expected = createExpected();

		final String result = objectUnderTest.getAddress();

		assertThat(result).isEqualTo(expected);

	}

	private String createExpected() {
		return "http://api.nbp.pl/api/exchangerates/rates/C/" + currency + "/" + startDate + "/" + endDate + "/";
	}


}