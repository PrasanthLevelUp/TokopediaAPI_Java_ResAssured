package utils;

public class ConfigReader {

	public static final String CONFIG = System.getProperty("user.dir") + "/src/main/resources/config.properties";
	public static final String EXTENT_CONFIG = System.getProperty("user.dir") + "/target/ExtentReports";
	public static String getBaseURI() {
		return PropertyUtils.getProperty("baseURI");
	}
	public static String getToken() {
		return PropertyUtils.getProperty("token");
	}


}
