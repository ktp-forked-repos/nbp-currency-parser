package pl.parsers.nbp.exchangerate;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class XmlExchangeRatesProvider implements ExchangeRatesProvider {

	private static final String BID_ELEMENT = "Bid";
	private static final String ASK_ELEMENT = "Ask";

	private final Document document;

	private XmlExchangeRatesProvider(final Document document) {
		this.document = document;
	}

	public static XmlExchangeRatesProvider of(final Document document) {
		return new XmlExchangeRatesProvider(document);
	}

	@Override
	public ExchangeRates get() {
		final Rates bids = extractBids();
		final Rates asks = extractAsks();
		return ExchangeRates.of(bids, asks);
	}

	private Rates extractBids() {
		return extractRates(BID_ELEMENT);
	}

	private Rates extractAsks() {
		return extractRates(ASK_ELEMENT);
	}

	private Rates extractRates(final String elementName) {
		final NodeList nodes = getNodes(elementName);
		List<BigDecimal> values = getValuesFromNodes(nodes);
		return Rates.of(values);
	}

	private NodeList getNodes(final String elementName) {
		return document.getElementsByTagName(elementName);
	}

	private List<BigDecimal> getValuesFromNodes(final NodeList nodes) {

		final List<BigDecimal> values = new ArrayList<>();
		for (int i = 0; i < nodes.getLength(); i++) {
			appendValue(nodes.item(i), values);
		}
		return values;
	}

	private void appendValue(final Node node, final List<BigDecimal> values) {
		if (node != null && node.getTextContent() != null) {
			values.add(new BigDecimal(node.getTextContent()));
		}
	}


}
