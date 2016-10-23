package pl.parsers.nbp.datasource;

public interface Request {

	String getContentType();

	String getAddress();

	int getReadTimeout();

	int getConnectTimeout();

}
