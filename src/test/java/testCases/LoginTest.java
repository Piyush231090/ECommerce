package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccount;
import testBases.BaseClass;

public class LoginTest extends BaseClass{
	
	@Test(groups = {"sanity","master"})
	public void verifyLogin() {
		logger.info("Inside verify login method..");
		try {
		// homepage
		HomePage hp = new HomePage(driver);
		hp.click_myaccount();
		hp.click_loginButton();
		
		// loginpage
		LoginPage lg = new LoginPage(driver);
		lg.setEmail(p.getProperty("email"));
		lg.setPassword(p.getProperty("password"));
		lg.click_loginButton();
		
		// myaccount
		MyAccount ma = new MyAccount(driver);
		Assert.assertTrue(ma.isMyAccountPageExists());
		}
		catch(Exception e) {
			Assert.fail();
		}
	}

}
