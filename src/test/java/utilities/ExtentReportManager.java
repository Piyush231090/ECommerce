package utilities;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBases.BaseClass;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	// Initialize Extent Report
		public void onStart(ITestContext testContext) {
			
			/*
			SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
			Date dt = new Date();
			String currentDateTimestamp = df.format(dt);
			*/
			
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // created time stamp 
			repName = "Test-Report-"+ timeStamp + ".html";
			sparkReporter = new ExtentSparkReporter(".\\reports\\"+ repName); // specifying location of the report
			
			sparkReporter.config().setDocumentTitle("OpenCart Automation Report"); //setting report title
			sparkReporter.config().setReportName("OpenCart Functional Testing"); // setting report name
			sparkReporter.config().setTheme(Theme.DARK); //setting theme colour
			
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Application", "Opencart");
			extent.setSystemInfo("Module", "Admin");
			extent.setSystemInfo("SubModule", "Customers");
			extent.setSystemInfo("User Name", System.getProperty("user.name"));
			extent.setSystemInfo("Environment", "QA");	
			
			String os = testContext.getCurrentXmlTest().getParameter("os");
			extent.setSystemInfo("Operating System", os);
			
			String browser = testContext.getCurrentXmlTest().getParameter("browser");
			extent.setSystemInfo("Browser", browser);
			
			List<String> includedGroups =  testContext.getCurrentXmlTest().getIncludedGroups();
			if(!includedGroups.isEmpty()) {
				extent.setSystemInfo("Groups", includedGroups.toString());
			}	
		}
		
		public void onTestSuccess(ITestResult result) {
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());       // this will display group names in report
			test.log(Status.PASS, result.getName()+ "got successfully executed");
		}
		
		public void onTestFailure(ITestResult result) {
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());  

			test.log(Status.FAIL, result.getName()+ "got Failed");
			test.log(Status.INFO, result.getThrowable().getMessage()); 
			
			try {
				String imgPath = new BaseClass().captureScreen(result.getName());
				test.addScreenCaptureFromPath(imgPath);
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		public void onTestSkipped(ITestResult result) {
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());  

			test.log(Status.SKIP, result.getName()+ "got skipped");
			test.log(Status.INFO, result.getThrowable().getMessage());	
		}
		
		public void onFinish(ITestContext testcontext) {
			extent.flush();
			
			String pathOfExtentReport = "D:\\ECommerce\\ECommerce\\reports\\" +repName;
			//String pathOfExtentReport = System.getProperty("user.dir")+ "\\reports\\" +repName;
			File extentReport = new File(pathOfExtentReport);
			
			//this block will automatically open the report on test completion
			try {
				Desktop.getDesktop().browse(extentReport.toURI());
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			//this block will send email on test completion. 
			/*
			try {
				Path filepath =  Paths.get(System.getProperty("user.dir"), "reports", repName);
				URL url = filepath.toUri().toURL();
				
				ImageHtmlEmail email = new ImageHtmlEmail();
				email.setDataSourceResolver(new DataSourceUrlResolver(url));
				email.setHostName("smtp.googleemail.com");
				email.setSmtpPort(465);
				email.setAuthenticator(new DefaultAuthenticator("piyush231090@gmail.com", "Apache@turbo1200"));
				email.setSSLOnConnect(true);
				email.setFrom("piyush231090@gmail.com"); // sender
				email.setSubject("Test Results");
				email.setMsg("Please find attached report");
				email.addTo("piyushingle07@gmail.com"); // receiver email address
				email.attach(url, "extent report", "Please check report...");
				email.send();
 				
			} catch(Exception e){
				e.printStackTrace();
			}
	*/
		}
		

}
