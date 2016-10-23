package pl.parsers.nbp.exchangerate.calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.parsers.nbp.exchangerate.Rates;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(Parameterized.class)
public class AverageCalculatorTest {

	private final List<BigDecimal> elements;
	private final BigDecimal expected;

	public AverageCalculatorTest(final List<BigDecimal> elements, final BigDecimal expected) {
		this.elements = elements;
		this.expected = expected;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> parameters() {
		return Arrays.asList(
				new Object[][]{
						{Arrays.asList(BigDecimal.valueOf(1.0000), BigDecimal.valueOf(10.0000)), BigDecimal.valueOf(5.500).setScale(4)},
						{Arrays.asList(BigDecimal.valueOf(4.2608), BigDecimal.valueOf(4.2590), BigDecimal.valueOf(4.2492)), BigDecimal.valueOf(4.2563)},
						{Arrays.asList(BigDecimal.valueOf(4.3350), BigDecimal.valueOf(4.3450), BigDecimal.valueOf(4.3468)), BigDecimal.valueOf(4.3423)}
				}
		);
	}

	@Test
	public void should_calculate_average() throws Exception {

		final AverageCalculator objectUnderTest = AverageCalculator.of(Rates.of(elements));

		final BigDecimal result = objectUnderTest.calculate();

		assertThat(result).isEqualTo(expected);
	}

}