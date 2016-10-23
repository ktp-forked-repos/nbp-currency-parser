package pl.parsers.nbp;


import org.junit.Test;

import java.time.LocalDate;
import java.util.Currency;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.fail;

public class ParametersProviderTest {

	@Test
	public void should_extract_parameters() throws Exception {

		final String usd = "USD", startDate = "2016-10-23", endDate = "2016-10-30";

		final String[] args = {usd, startDate, endDate};
		final ParametersProvider objectUnderTest = ParametersProvider.of(args);
		final Parameters expected = Parameters.of(Currency.getInstance(usd), LocalDate.parse(startDate), LocalDate.parse(endDate));

		final Parameters result = objectUnderTest.getParameters();

		assertThat(result).isEqualTo(expected);
	}

	@Test(expected = IllegalArgumentException.class)
	public void should_throw_illegal_argument_exception_when_args_is_smaller_than_3() {
		final String[] args = {"USD", "2016-01-01"};

		final ParametersProvider objectUnderTest = ParametersProvider.of(args);
		objectUnderTest.getParameters();

		fail("Test should throw IllegalArgumentException");

	}

	@Test(expected = IllegalArgumentException.class)
	public void should_throw_illegal_argument_exception_when_args_is_bigger_than_3() {
		final String[] args = {"USD", "2016-01-01", "2016-01-01", "2016-01-01"};

		final ParametersProvider objectUnderTest = ParametersProvider.of(args);
		objectUnderTest.getParameters();

		fail("Test should throw IllegalArgumentException");

	}

	@Test(expected = IllegalArgumentException.class)
	public void should_throw_illegal_argument_exception_when_currency_code_is_incorrect() {
		final String[] args = {"Uydfyasdfvsd", "2016-01-01", "2016-01-01"};

		final ParametersProvider objectUnderTest = ParametersProvider.of(args);
		objectUnderTest.getParameters();

		fail("Test should throw IllegalArgumentException");

	}

	@Test(expected = IllegalArgumentException.class)
	public void should_throw_illegal_argument_exception_when_currency_code_is_not_allowed() {
		final String[] args = {"AFN", "2016-01-01", "2016-01-01"};

		final ParametersProvider objectUnderTest = ParametersProvider.of(args);
		objectUnderTest.getParameters();

		fail("Test should throw IllegalArgumentException");

	}

	@Test(expected = IllegalArgumentException.class)
	public void should_throw_illegal_argument_exception_when_start_date_is_incorrect() {
		final String[] args = {"USD", "aseasgadsf", "2016-01-01"};

		final ParametersProvider objectUnderTest = ParametersProvider.of(args);
		objectUnderTest.getParameters();

		fail("Test should throw IllegalArgumentException");

	}

	@Test(expected = IllegalArgumentException.class)
	public void should_throw_illegal_argument_exception_when_end_date_is_incorrect() {
		final String[] args = {"USD", "2016-01-01", "aseasgadsf"};

		final ParametersProvider objectUnderTest = ParametersProvider.of(args);
		objectUnderTest.getParameters();

		fail("Test should throw IllegalArgumentException");

	}

}