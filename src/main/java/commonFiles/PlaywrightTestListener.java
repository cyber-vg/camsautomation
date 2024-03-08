package commonFiles;

import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PlaywrightTestListener extends TestListenerAdapter {
	private static       int count  = 0;
	private final static int maxTry = 4;

	public void onTestFailure(ITestResult iTestResult) {
	    System.out.println("I am in onTestFailure method "  + " failed");
	    if (count < maxTry) {
	        count++;
	        TestNG tng = new TestNG();
	        tng.setDefaultTestName("RETRY TEST");
	        Class[] classes1 = { iTestResult.getTestClass().getRealClass() };
	        tng.setTestClasses(classes1);
	        tng.addListener(new PlaywrightTestListener());
	        tng.run();
	        System.out.println("reerun hu me");
	    }
	    else {
	    	count=0;
	    }
	}
}
