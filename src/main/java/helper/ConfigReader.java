package helper;

public class ConfigReader {

	public static final String CONFIG = System.getProperty("user.dir") + "/src/main/resources/config.properties";
	public static final String EXTENT_CONFIG = System.getProperty("user.dir") + "/Reports/ExtentReports";
	public static String getBaseURI() {
		return PropertyConfig.getProperty("baseURI");
	}
	public static String getToken() {
		return PropertyConfig.getProperty("token");
	}

}
