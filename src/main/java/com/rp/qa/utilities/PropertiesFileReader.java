package com.rp.qa.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PropertiesFileReader class reads properties files and returns an instance of the Properties class.
 * It provides a method - read(fileName), which takes the filepath as the parameter.
 *
 * @author QED42
 */
public class PropertiesFileReader {

	private static final Logger LOGGER = Logger.getLogger(PropertiesFileReader.class.getName());

	/**
	 * Reads a properties file and returns an instance of Properties class.
	 *
	 * @param fileName the path to the properties file
	 * @return Properties instance containing the loaded properties, or null if loading fails
	 */
	public static Properties read(String fileName) {
		Properties properties = new Properties();

		// Try-with-resources ensures FileInputStream is closed after use
		try (FileInputStream ip = new FileInputStream(fileName)) {
			properties.load(ip);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Error loading properties file: " + fileName, e);
			return null;  // Return null if file cannot be loaded
		}

		return properties;
	}
}
