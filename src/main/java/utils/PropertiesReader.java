package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    public PropertiesReader() throws IOException {
        loadProperties();
    }

    Properties properties = new Properties();

    public void loadProperties() throws IOException {
        String propertyFileName = "config.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream stream = loader.getResourceAsStream(propertyFileName)) {
            properties.load(stream);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
