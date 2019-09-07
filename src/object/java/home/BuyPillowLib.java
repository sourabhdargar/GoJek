package home;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BuyPillowLib {
	@FindBy(css = ".btn.buy")
	public WebElement buyNowButton;

	@FindBy(css = ".cart-checkout")
	public WebElement checoutButton;

	@FindBy(css = ".text-page-title-content")
	public WebElement orderSummaryPage;

	@FindBy(css = ".button-main-content")
	public WebElement continueButton;

	@FindBy(xpath = "//div[text()='Credit Card']")
	public WebElement creditCard;

	@FindBy(xpath = "//input[@name='cardnumber']")
	public WebElement cardnNumberTextBox;

	@FindBy(xpath = "//input[@placeholder='MM / YY']")
	public WebElement expiryDateTextBox;

	@FindBy(xpath = "//input[@placeholder='123']")
	public WebElement cvvTextBox;

	@FindBy(css = ".button-main-content")
	public WebElement payNow;

	@FindBy(xpath = "//input[@type='password']")
	public WebElement password;

	@FindBy(css = ".btn.btn-sm.btn-success")
	public WebElement okButton;

	@FindBy(xpath = "//span[text()='Invalid card number']")
	public WebElement invalidCardErrorMsg;	

	@FindBy(xpath = "//div[text()='Transaction successful']")
	public WebElement transSuccessfulMsg;

	@FindBy(xpath = "//span[text()='Thank you for your purchase.']")
	public WebElement confirmationPurchaseMsg;	
}