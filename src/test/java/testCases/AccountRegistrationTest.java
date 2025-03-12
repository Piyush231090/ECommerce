package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBases.BaseClass;

public class AccountRegistrationTest extends BaseClass{
	
	@Test(groups = {"regression", "master"})
	public void verifyAccountRegistration() {
		try {
		HomePage hp = new HomePage(driver);
		logger.info("**accessing website**");
		hp.click_myaccount();
		hp.click_registerButton();
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		logger.info("**accessing registration page**");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com"); //randomly generate an email
		regpage.setTelephone(randomNumber());
		
		String password = randomAlphanumeric();
		regpage.setPassword(password);
		regpage.cnfrmPassword(password);
		
		regpage.clickPolicyButton();
		regpage.clickContinueButton();
		
		logger.info("Validating expected message");
		String cnfrmmsg = regpage.getCnfrmMsg();
		Assert.assertEquals(cnfrmmsg, "Your Account Has Been Created!");
		} catch (Exception e) {
			logger.error("Test case failed");
			logger.debug("Debug logs");
			Assert.fail();
		}
	}
}
