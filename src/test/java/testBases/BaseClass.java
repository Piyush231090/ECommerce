package testBases;


import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



public class BaseClass {
	
	public WebDriver driver;
	public Logger logger;  //log4j
	public Properties p;
	
	@BeforeClass(groups = { "sanity", "regression", "master", "datadriven"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
		logger= LogManager.getLogger(this.getClass());
		FileReader file  = new FileReader("./src/test/resources/config.properties");
		p = new Properties();
		p.load(file);
		
		switch(br.toLowerCase()) { 							// switch case to choose the browser
		case "chrome" :driver = new ChromeDriver();
		break;
		case "edge" : driver = new EdgeDriver();
		break;
		case "firefox" : driver = new FirefoxDriver();
		break;
		default: System.out.println("Invalid browser name...");
		return;
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appurl"));  //reading appurl from properties file
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups = { "sanity", "regression", "master", "datadriven"})
	public void teardown() {
		driver.quit();
	}
	
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
		
	}
	
	public String randomNumber() {
		String generateNumber = RandomStringUtils.randomNumeric(10);
		return generateNumber;
	}
	
	public String randomAlphanumeric() {
		String genAlphaNumeric = RandomStringUtils.randomAlphanumeric(10);
		return genAlphaNumeric;
	}

}
