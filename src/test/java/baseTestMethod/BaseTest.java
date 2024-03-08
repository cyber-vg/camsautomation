package baseTestMethod;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.TestNG;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.microsoft.playwright.Page;

import adminRegressionPages.adminLoginPage;
import initBrowser.startBrowser;
import io.qameta.allure.Allure;
import memberRegressionPages.ApplyforLoan;
import memberRegressionPages.memberContribution;
import memberSmokePages.memberCreditUnion;
import memberSmokePages.memberSignIn;
import memberSmokePages.moreTabsDetails;
import superAdminSmokePages.SuperAdminLogin;

@Listeners(RetryListener.class)
public class BaseTest {
	public static String failedTestClass;
	public static Boolean failedstatus=false;
	startBrowser sb;
	public Page page;
	public Page adminpage;
	public Page superAdminPage;
	protected Properties prop ;
	protected memberSignIn signin;
	protected memberCreditUnion unionList;
	protected moreTabsDetails moreTabs;
	protected memberContribution addcontribution;
	protected adminLoginPage adminLogin;
	protected SuperAdminLogin superAdmin;
	protected ApplyforLoan applyLoan;
	 private static int maxRetryCount = 1;
	 private static int retryCount = 0;
	
	@BeforeTest
	@Parameters({"Portal", "browser"})
	public void setup(String Portal ,String browserName) throws IOException { 
	  sb=new startBrowser();
	  prop=	sb.init_prop();
	  if (browserName != null) {
			prop.setProperty("browser", browserName);
		}
	  if(Portal.equalsIgnoreCase("Member")) {
		  page =sb.initBrowserMember(prop); }
	  else if(Portal.equalsIgnoreCase("Admin")) {
		  adminpage=sb.initBrowserAdmin(prop);}
	  else if(Portal.equalsIgnoreCase("SuperAdmin")) {
		  superAdminPage=sb.initBrowserSuper(prop);
	  }
//for member page	
	  signin =new memberSignIn(page);
	  unionList=new memberCreditUnion(page);
	  moreTabs=new moreTabsDetails(page);
	  addcontribution=new  memberContribution(page);
	  applyLoan=new ApplyforLoan(page);	  
	  //for admin page
	  adminLogin=new adminLoginPage(adminpage);
	  //for superAdmin Page 
	 superAdmin=new SuperAdminLogin(superAdminPage);
	  
	}
//	 @AfterMethod
//	 @Parameters({"Portal", "browser"})
//	    public void retryFailedTestMethods(ITestResult result,String Portal ,String browser) {
//	        if (!result.isSuccess()) {
//	            if (retryCount < maxRetryCount) {
//	                retryCount++;
//	                // Rerun the entire test class
//	                retryFailedTestClass(Portal , browser);
//	            }
//	        }
//	        
//	        
//	    }

//	 private void retryFailedTestClass(String portal, String browser) {
//		    System.out.println("Retrying failed test class...");
//		
//		    // Rerun the entire test class with the Portal parameter
//		    TestNG testNG = new TestNG();
//		    XmlSuite suite = new XmlSuite();
//		    suite.setName("Retry Failed Test Class");
//
//		    XmlTest test = new XmlTest(suite);
//		    test.setName("Failed Test");
//		    test.addParameter("Portal", portal);
//		    test.addParameter("browser", browser);
//		    XmlClass xmlClass = new XmlClass(this.getClass().getName());
//		    List<XmlClass> classes = new ArrayList<>();
//		    classes.add(xmlClass);
//		    test.setXmlClasses(classes);
//
//		    List<XmlSuite> suites = new ArrayList<>();
//		    suites.add(suite);
//		    testNG.setXmlSuites(suites);
//		    testNG.run();
//		}

	@AfterTest
	@Parameters({"Portal", "browser"})
		public void tearDown(String Portal,String browser) {

		  if(Portal.equalsIgnoreCase("Member")) {
		try {				  					  	  
		        page.close(); 
		        videoRecroding(page);
		        page.context().browser().close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		  
	 else if(Portal.equalsIgnoreCase("Admin")) {
		 try {
			 adminpage.close();
			 videoRecroding(adminpage);
		     adminpage.context().browser().close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
			  
		
		}
	 else if(Portal.equalsIgnoreCase("SuperAdmin")) {
		 try {
			superAdminPage.close();
			videoRecroding(superAdminPage);
		    superAdminPage.context().browser().close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
			  
		
		}
		  
		  if(failedstatus==true && retryCount <maxRetryCount) {
			  retryCount++;
		  retryFailedTestClass(failedTestClass, Portal, browser);
		  failedstatus=false;
		  }
		  else if(failedstatus!=true && retryCount >=maxRetryCount) {
			  retryCount=0;
		  }
		 
		  
	
	}
	
//		public void retryFailedTestMethods(ITestResult result, String Portal, String browser) {
//			System.out.println("789+6321896562146");
//		        if (!result.isSuccess() && retryCount < maxRetryCount) {
//		            retryCount++;
//		            retryFailedTestClass(Portal, browser,failedTestClass);
//		        }
//		    }

		    private void retryFailedTestClass(String failedTestClass,String portal, String browser) {
		        System.out.println("Retrying failed test class...");

		        // Rerun only the current test class
		        TestNG testNG = new TestNG();
		        XmlSuite suite = new XmlSuite();
		        suite.setName("Retry Failed Test Class");

		        XmlTest test = new XmlTest(suite);
		        test.setName("Failed Test");
		        test.addParameter("Portal", portal);
		        test.addParameter("browser", browser);
		        XmlClass xmlClass = new XmlClass(failedTestClass);
		        List<XmlClass> classes = new ArrayList<>();
		        classes.add(xmlClass);
		        test.setXmlClasses(classes);

		        List<XmlSuite> suites = new ArrayList<>();
		        suites.add(suite);
		        testNG.setXmlSuites(suites);
		        testNG.run();
		    }
		

	
	
	
	public void videoRecroding(Page page) {
		try {
		  Path videoFolder = Paths.get("video");
	        if (!Files.exists(videoFolder)) {
	            Files.createDirectories(videoFolder);
	        }
	        // Save the video in the video folder with a unique name based on the current timestamp
	        Path videoPath = videoFolder.resolve(System.currentTimeMillis() + ".webm");
	        page.video().saveAs(videoPath);
	        // Attach the saved video to the Allure report
	        byte[] videoBytes = Files.readAllBytes(videoPath);
	        Allure.addAttachment("Video Recording", "video/webm", new ByteArrayInputStream(videoBytes), ".mp4");
		}catch (Exception e) {
	        e.printStackTrace();

		
	}}
	
	
}
			  
	     	   

