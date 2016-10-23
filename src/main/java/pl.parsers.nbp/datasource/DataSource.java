package pl.parsers.nbp.datasource;

import java.io.IOException;
import java.io.InputStream;

public interface DataSource {
	InputStream get() throws IOException;
}
