package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{
	
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtInputEmail;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtCnfrmPassword;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement policyButton;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement continueButton;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement cnfrmMessage;
	
	
	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		txtInputEmail.sendKeys(email);
	}
	
	public void setTelephone(String tele) {
		txtTelephone.sendKeys(tele);
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void cnfrmPassword(String cnfpassword) {
		txtCnfrmPassword.sendKeys(cnfpassword);
	}
	
	public void clickPolicyButton() {
		policyButton.click();
	}
	
	public void clickContinueButton() {
		continueButton.click();
	}
	
	public String getCnfrmMsg() {
		try {
			return (cnfrmMessage.getText());
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
