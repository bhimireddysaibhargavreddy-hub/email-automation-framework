package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    public static void loadProperties() {

        properties = new Properties();

        try (FileInputStream file =
                     new FileInputStream(
                             "src/test/resources/config.properties")) {

            properties.load(file);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to load config.properties",
                    e
            );
        }
    }

    public static String getProperty(String key) {

        return properties.getProperty(key);
    }
}