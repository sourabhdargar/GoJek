package home;

import java.io.IOException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static genericLib.BaseClass.*;
import genericLib.Utility;

public class BuyPillowTest {
	BuyPillow buyObj;

	@BeforeClass(alwaysRun=true)
	public void init() {		
		buyObj=new BuyPillow();
	}

	@AfterMethod(alwaysRun=true) 
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException { 
		if (testResult.getStatus() == ITestResult.FAILURE) { 
			String imageName=testResult.getTestClass().getName().replaceAll("\\.", "_") + "-" + testResult.getName();
			Utility.takeScreenShot(imageName);
		} 
	}

	@Test(priority=1)
	public void successfulPaymentFLow() {
		logger.info("------------Executing successfulPaymentFLow------------");
		buyObj.successfulPaymentFLow();	
	}

	@Test(priority=2)
	public void failedPaymentFLow() {
		logger.info("------------Executing failedPaymentFLow ------------");
		buyObj.failedPaymentFLow();	
	}
}