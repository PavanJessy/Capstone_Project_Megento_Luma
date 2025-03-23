package base;

import java.io.FileReader;
import java.util.Properties;

public class Utility {

	 public static String properties(String data) throws Exception {
	        String propertiesData = null;
	        try {
	          
	            String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\Configure.properties";
	            FileReader reader = new FileReader(filePath);
	            Properties p = new Properties();
	            p.load(reader);
	            propertiesData = p.getProperty(data);   
	        } catch (Exception e) {
	            System.out.println("Utility - properties: " + e.getMessage());
	        }
	        return propertiesData;   
	    }

}
