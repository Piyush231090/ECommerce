package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccount;
import testBases.BaseClass;
import utilities.DataProviders;

public class LoginDataDrivenTest extends BaseClass {
	
	@Test(dataProvider = "loginData", dataProviderClass = DataProviders.class, groups = "datadriven") // getting dataProvide from different class
	public void verifyLoginDDT(String email, String pwd, String exp) {
		logger.info("Inside verify login method..");
		
		try {
		// homepage
		HomePage hp = new HomePage(driver);
		hp.click_myaccount();
		hp.click_loginButton();
		
		// loginpage
		LoginPage lg = new LoginPage(driver);
		lg.setEmail(email);
		lg.setPassword(pwd);
		lg.click_loginButton();
		
		
		// myaccount
		MyAccount ma = new MyAccount(driver);
		Boolean targetPage = ma.isMyAccountPageExists();
		/*
		 * valid data --- login success --- test pass --- logout
		 * 					login fail ---test fail
		 * 
		 * invalid data--- login success ---test fail---logout
		 * 					login fail -----test pass
		 * */
		
		if(exp.equalsIgnoreCase("valid")) {
			if(targetPage==true) {
				ma.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("invalid")) 
		{
			if(targetPage==true) 
			{
				ma.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		
			
	}

}
