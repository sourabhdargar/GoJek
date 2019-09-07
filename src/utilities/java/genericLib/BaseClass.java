package genericLib;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;
import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
/*
 Class Description: Initialize variables and launch browser
 */

public class BaseClass {
	public static WebDriver driver = null;
	public static Properties CONFIG = null;
	public static Properties CONSTANTS = null;
	public static Logger logger;
	public static String environment=null;
	public static String environment1=null;
	public static DesiredCapabilities capability=null;
	public static HashMap<String, String> dbquery =new HashMap<>();

	@BeforeTest(alwaysRun=true)
	public void init() throws IOException{		
		if(driver==null){
			// initialize the properties file
			CONFIG= new Properties();
			CONSTANTS = new Properties();

			try{

				FileInputStream fs=null;
				fs = new FileInputStream("./src/test/resources/Config/config.properties");
				if(fs!=null) {
					CONFIG.load(fs);
				}			

				//logger
				logger = Logger.getLogger(Log4JLogger.class.getName());
				DOMConfigurator.configure("./log4j.xml");
			}
			catch(Exception e){
				e.printStackTrace();
			}

			FileInputStream fs=null;
			//constants
			fs = new FileInputStream("./src/main/resources/Constants/costants.properties");
			CONSTANTS.load(fs);

			if(CONFIG.getProperty("browser").equalsIgnoreCase("firefox")){
				FirefoxProfile profile = new FirefoxProfile();
				profile.setPreference("browser.download.folderList", 1);
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/plain");
				BaseClass.driver=new FirefoxDriver(profile);
				logger.info("Mozilla Firefox Browser is Launching");
			}else if(CONFIG.getProperty("browser").equalsIgnoreCase("ie")){
				System.setProperty("webdriver.ie.driver", "./src/test/resources/Drivers/IEDriverServer.exe");
				BaseClass.driver=new InternetExplorerDriver();
				logger.info("Microsoft Internet Explorer Browser is Launching");
			}else if(CONFIG.getProperty("browser").equalsIgnoreCase("Chrome")){
				System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/chromedriver.exe");
				BaseClass.driver=new ChromeDriver();
				logger.info("Google Chrome Browser is Launching");
			}
			broswerDetails();
			driver.manage().window().maximize();
			driver.get(CONFIG.getProperty("testSiteURL"));
			System.out.println(driver.manage().window().getSize());	

			logger.info("===================================================================================================");
			logger.info("===================================================================================================");
		}
	}

	@AfterSuite(alwaysRun=true)
	public void afterSuite() throws EncryptedDocumentException, InvalidFormatException, IOException, SQLException {
		closeBrowser();
	}

	//Implemented to get browser details
	public void broswerDetails(){
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = cap.getBrowserName().toLowerCase();
		String osType = cap.getPlatform().toString();
		String browser_Version = cap.getVersion().toString();
		logger.info("Configuration Details :- "+"Platform - "+osType+" ,"+" Browser Name - "+browserName+" ,"+" Browser Version - "+browser_Version);
	}

	//Implement to close the browser
	public void closeBrowser() {
		driver.quit();
		driver=null;
	}
}
