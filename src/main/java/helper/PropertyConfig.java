package helper;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyConfig {

	public static String getProperty(String key) {
		Properties prop = new Properties();

		FileInputStream fis;
		String value = null;

		try {
			File file = new File(ConfigReader.CONFIG);
			fis = new FileInputStream(file);
			try {
				prop.load(fis);
				value = prop.getProperty(key);
			} catch (Exception e) {
				throw new RuntimeException("Not able to load property");
			}
		} catch (Exception e) {
			throw new RuntimeException("File not found");
		}

		return value;

	}

}
