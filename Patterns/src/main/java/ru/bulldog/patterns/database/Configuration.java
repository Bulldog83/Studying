package ru.bulldog.patterns.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

public final class Configuration {

	private static final Logger logger = LogManager.getLogger(Configuration.class);
	private static final String PROPERTIES = "application.properties";

	private static Configuration config;

	public static Configuration getInstance() {
		if (config == null) {
			config = new Configuration();
		}
		return config;
	}

	private final Properties properties;

	private Configuration() {
		this.properties = new Properties();
		try (InputStream fis = getClass().getResourceAsStream(PROPERTIES)) {
			properties.load(fis);
		} catch (Exception ex) {
			logger.catching(ex);
		}
	}

	public String getProperty(String name) {
		return properties.getProperty(name);
	}
}
