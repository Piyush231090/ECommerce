package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement myAccount_button;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement register_button;
	
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
	WebElement login_button;
	
	public void click_myaccount() {
		myAccount_button.click();
	}
	
	public void click_registerButton() {
		register_button.click();
	}
	
	public void click_loginButton() {
		login_button.click();
	}
}
