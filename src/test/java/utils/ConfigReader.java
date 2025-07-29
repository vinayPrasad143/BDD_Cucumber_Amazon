package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    public static Properties getProperties() {
        if (prop == null) {
            prop = new Properties();
            try {
                FileInputStream ip = new FileInputStream("src/test/resources/config.properties");
                prop.load(ip);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("❌ Could not load config.properties");
            }
        }
        return prop;
    }

    public static String getProperty(String key) {
        return getProperties().getProperty(key);
    }
}
