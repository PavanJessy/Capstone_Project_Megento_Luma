package screenshot;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.BaseClass;

public class CaptureScreenShot extends BaseClass{
	
	public static String screenShot(String name) throws Exception { 
		String path=null;
		try {
			TakesScreenshot shot = ((TakesScreenshot) driver);
			File source = shot.getScreenshotAs(OutputType.FILE);
			path = System.getProperty("user.dir") + "\\target\\ScreenShot\\" + name + ".png";
			File destination = new File(path);
			//FileUtils.copyFile(source, destination);
			source.renameTo(destination);
		} catch (Exception e) {
			System.out.println("Screenshot");
		}
		return path;
	}
	

}
