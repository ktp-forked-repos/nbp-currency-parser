package pl.parsers.nbp.datasource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class URLDataSource implements DataSource {

	private final Request request;

	private URLDataSource(final Request request) {
		this.request = request;
	}

	public InputStream get() throws IOException {
		final URL url = new URL(request.getAddress());
		final URLConnection connection = url.openConnection();
		connection.setConnectTimeout(request.getConnectTimeout());
		connection.setReadTimeout(request.getReadTimeout());
		connection.setRequestProperty("Content-Type", request.getContentType());
		return connection.getInputStream();
	}

	public static URLDataSource of(final Request request) {
		return new URLDataSource(request);
	}


}
