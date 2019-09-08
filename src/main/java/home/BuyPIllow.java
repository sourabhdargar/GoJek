package home;

import static genericLib.BaseClass.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BuyPillow {
	BuyPillowLib pageLib=PageFactory.initElements(driver, BuyPillowLib.class);
	WebDriverWait wait=new WebDriverWait(driver, 30);

	public void successfulPaymentFLow() {
		purchaseTillCreditCardPaymentPage(CONSTANTS.getProperty("VALID_CARDNUMBER"));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.tagName("iframe"))));
		wait.until(ExpectedConditions.visibilityOf(pageLib.password)).sendKeys(CONSTANTS.getProperty("OTP"));
		wait.until(ExpectedConditions.visibilityOf(pageLib.okButton)).click();
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.tagName("iframe"))));
		wait.until(ExpectedConditions.visibilityOf(pageLib.transSuccessfulMsg));
		Assert.assertEquals(pageLib.transSuccessfulMsg.getText(), CONSTANTS.getProperty("TRANSACTIN_SUCCESS_MSG"),"Transction Unsuccessful");
		wait.until(ExpectedConditions.visibilityOf(pageLib.confirmationPurchaseMsg));
		Assert.assertEquals(pageLib.confirmationPurchaseMsg.getText(), CONSTANTS.getProperty("CONFIRMATION_MSG"),"Purchase Unsuccessful");
	}
	
	public void failedPaymentFLow() {
		purchaseTillCreditCardPaymentPage(CONSTANTS.getProperty("INVALID_CARDNUMBER"));
		Assert.assertEquals(pageLib.invalidCardErrorMsg.getText(), CONSTANTS.getProperty("INVALIDCARD_ERROR_MSG"),"Invalid Card  Number Error Meesage not displayed");
	}

	public void purchaseTillCreditCardPaymentPage(String cardnumber) {
		driver.navigate().to(CONFIG.getProperty("testSiteURL"));
		wait.until(ExpectedConditions.visibilityOf(pageLib.buyNowButton)).click();
		wait.until(ExpectedConditions.visibilityOf(pageLib.checoutButton)).click();		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.tagName("iframe"))));
		wait.until(ExpectedConditions.visibilityOf(pageLib.continueButton)).click();
		wait.until(ExpectedConditions.visibilityOf(pageLib.creditCard)).click();
		wait.until(ExpectedConditions.visibilityOf(pageLib.cardnNumberTextBox)).sendKeys(cardnumber);
		pageLib.expiryDateTextBox.sendKeys(CONSTANTS.getProperty("EXPIRY_DATE"));
		pageLib.cvvTextBox.sendKeys(CONSTANTS.getProperty("CVV"));
		pageLib.payNow.click();
	}
}