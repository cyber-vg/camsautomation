package baseTestMethod;

import org.testng.ITestListener;
import org.testng.ITestResult;
public class RetryListener implements ITestListener {
    
    @Override
    public void onTestFailure(ITestResult result) {
        // Get the name of the failed test class and store it in the BaseTest class
    	System.err.println(result.getTestClass().getRealClass().getName()+" caputre in onTestFailure");
        BaseTest.failedTestClass = result.getTestClass().getRealClass().getName();
        BaseTest.failedstatus=true;
    }
    

    // Implement other methods of the interface if needed
}
