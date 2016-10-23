package pl.parsers.nbp;


import org.w3c.dom.Document;
import pl.parsers.nbp.datasource.DataSource;
import pl.parsers.nbp.datasource.Request;
import pl.parsers.nbp.datasource.URLDataSource;
import pl.parsers.nbp.datasource.XmlDocumentDataSource;
import pl.parsers.nbp.exchangerate.ExchangeRates;
import pl.parsers.nbp.exchangerate.NbpExchangeRatesRequest;
import pl.parsers.nbp.exchangerate.XmlExchangeRatesProvider;
import pl.parsers.nbp.exchangerate.calculator.AverageCalculator;
import pl.parsers.nbp.exchangerate.calculator.StandardDeviationCalculator;

public class MainClass {

	public static void main(String[] args) {

		final Parameters parameters = ParametersProvider.of(args).getParameters();
		final Request request = NbpExchangeRatesRequest.of(parameters.getCurrency(), parameters.getStartDate(), parameters.getEndDate());
		final DataSource dataSource = URLDataSource.of(request);
		final Document document = XmlDocumentDataSource.of(dataSource).get();
		final ExchangeRates exchangeRates = XmlExchangeRatesProvider.of(document).get();

		System.out.println(AverageCalculator.of(exchangeRates.getBids()).calculate());
		System.out.println(StandardDeviationCalculator.of(exchangeRates.getAsks()).calculate());

	}
}

