package pl.parsers.nbp.exchangerate;

import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


public class XmlExchangeRatesProviderTest {

	private final String XML_DATA_FILE = "nbp_currency_rates_data.xml";

	@Test
	public void should_create_exchange_rates_from_resource_xml() throws Exception {

		final ExchangeRates expected = createExpected();
		final Document document = getDocumentFromResource();
		final XmlExchangeRatesProvider objectUnderTest = XmlExchangeRatesProvider.of(document);

		final ExchangeRates result = objectUnderTest.get();

		assertThat(result).isEqualTo(expected);
	}

	private Document getDocumentFromResource() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.parse(classLoader.getResource(XML_DATA_FILE).openStream());
		return document;
	}

	private ExchangeRates createExpected() {
		final Rates bids = Rates.of(Arrays.asList(asBigDecimal(4.2608), asBigDecimal(4.2590), asBigDecimal(4.2492)));
		final Rates asks = Rates.of(Arrays.asList(asBigDecimal(4.3468), asBigDecimal(4.3450), asBigDecimal(4.3350)));
		return ExchangeRates.of(bids, asks);
	}

	private BigDecimal asBigDecimal(final double value) {
		return new BigDecimal(value).setScale(4, RoundingMode.HALF_UP);
	}
}