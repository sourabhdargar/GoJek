package genericLib;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static genericLib.BaseClass.*;


public class Utility {
	// Implemented to take screenshot
	public static void takeScreenShot(String imageName){
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String datePattern = "dd_MMM_yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		String filePath = "Screenshots\\Screenshot_" + sdf.format(new Date()) + "\\" + imageName + ".jpg";
		try {
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}