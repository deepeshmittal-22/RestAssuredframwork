package api.utilites;

import java.awt.Desktop;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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



public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext)
	{
		/*
		 * SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.mm.ss"); Date dt = new
		 * Date(); String currentdatetimestamp=df.format(dt);
		 */
		
		String timestamp = new SimpleDateFormat("yyyy.mm.dd.HH.MM.ss").format(new Date());// time stamp
		
		repName ="Test-Report- " + timestamp + ".html";
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName); // specify location of the report
		
		sparkReporter.config().setDocumentTitle("Rest Assured API Testing"); // Title of report
		sparkReporter.config().setReportName("API Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application","opencart");
		
		extent.setSystemInfo("module", "Admin");
		extent.setSystemInfo("submodule", "customers");
		extent.setSystemInfo("userName", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("operating system", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
	    
		if(!includedGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());  // to display groups in report
		test.log(Status.PASS, result.getName()+" got successfully executed") ;
		
		
	}
	
	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());  // to display groups in report
		test.log(Status.FAIL, result.getName()+" got failed") ;
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		
	}
	
	public void onTestSkipped (ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());  // to display groups in report
		test.log(Status.SKIP, result.getName()+" got skipped") ;
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext) {
		
		extent.flush();
		String pathofExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathofExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		try {
			URL url= new URL("file:///"+System.getProperty("user.dir" + "\\reports\\" +repName));
			// create the email message
			ImageHtmlEmail email= new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("deepeshmittal.22@gmail.com","Deep@9412"));
			email.setSSLOnConnect(true);
			email.setFrom("deepeshmittal.22@gmail.com");//sender
			email.setSubject("Test Results");
			email.setMsg("please find the Attached Report......");
			email.addTo("1995agrawaldeepesh@gmail.com"); //Receiver
			email.attach(url,"extent report", "please check report...");
			email.send();// send the email
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	

}
