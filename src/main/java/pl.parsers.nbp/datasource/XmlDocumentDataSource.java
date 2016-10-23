package pl.parsers.nbp.datasource;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlDocumentDataSource {

	private final DataSource dataSource;

	private XmlDocumentDataSource(final DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static XmlDocumentDataSource of(final DataSource dataSource) {
		return new XmlDocumentDataSource(dataSource);
	}

	public Document get() {
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(dataSource.get());
			return document;
		} catch (Exception e) {
			throw new RuntimeException("Exception while creating document ", e);
		}
	}
}
