package utilities;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportManager implements ITestListener {

    private ExtentSparkReporter sparkReporter;
    private ExtentReports extent;
    private ExtentTest test;
    private String repName;

    @Override
    public void onStart(ITestContext testcontext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

        sparkReporter.config().setDocumentTitle("Opencart Automation Report");
        sparkReporter.config().setReportName("Opencart Functional Testing");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "Opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customer");
        extent.setSystemInfo("Username", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        String os = testcontext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testcontext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testcontext.getCurrentXmlTest().getIncludedGroups();
        if (includedGroups != null && !includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + " executed successfully.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, "Test case FAILED: " + result.getName());
        test.log(Status.FAIL, result.getThrowable());

        String imgPath = new BaseClass().captureScreen(result.getName());
		if (imgPath != null) {
		    test.addScreenCaptureFromPath(imgPath);
		} else {
		    test.log(Status.WARNING, "Screenshot not available for failed test.");
		}
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " was skipped.");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext testcontext) {
        extent.flush();
        String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
        File extentReport = new File(pathOfExtentReport);

        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(extentReport.toURI());
            } else {
                System.out.println("Report generated at: " + extentReport.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    


    	/*
    	try {
    		URL ulr = new
    		URL("file:///"+System.getProperty("user.dir")+"\\reports\\+repName");
    		
    		/// Create the email message
    		ImageHTMLEmail email= new ImageHtmlEmail();
    		email.setDataSourceResolver(new DataSourceurlResolver(url));
    		email.setHostName("smtp.googleemail.com");
    		email.setsmtPort(465);
    		email.setAunthnticator (new DefaultAuthenticator("sapkalegaurav123@gmail.com", "password"));
    		email.setSSLonConnect(true);
    		email.setFrom("sapkalegaurav@gmail.com");  //sender
    		email.setSubject("Test Results");
    		email.setMsg("Please find Attached Report.....");
    		email.addTo("sapkalegaurav20@gmail.com");
    		email.attach(url, "extent report", "please check report...");
    		email.send();		// send the email.
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();	
    	}*/






