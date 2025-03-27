package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	// static shared acrs all ins of cls
    private static Properties properties = new Properties();

    static {
        try {
        	//used 2 read the data frm file
            FileInputStream fis = new FileInputStream("C:\\Users\\91767\\git\\Capstone_Pavan\\src\\main\\resources\\Data.properties");
            
            //Loads k-Vpair fm file into prop obj
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties file: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key, "");
    }
}