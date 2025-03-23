package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\bodir\\Downloads\\LUMA\\src\\main\\resources\\Data.properties");
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties file: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key, "");
    }
}