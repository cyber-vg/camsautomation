package listners;
import static initBrowser.startBrowser.takeScreenshot;

import java.util.Base64;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

@Listeners({AllureReportListener.class})
public class AllureReportListener implements ITestListener  {
  
    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Test Suite started!");
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println(("Test Suite is ending!"));
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        // Implement if needed
    	Allure.step("Started");
 }

    @Step("{stepDescription}")
    public void allureStep(String stepDescription, String base64Screenshot) {
        attachScreenshot(base64Screenshot);	
        Allure.step(stepDescription);
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] attachScreenshot(String base64Screenshot) {
        return Base64.getDecoder().decode(base64Screenshot);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        allureStep("Test passed: " + result.getMethod().getMethodName(),takeScreenshot());
 }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        allureStep("Test failed: " + result.getMethod().getMethodName(), takeScreenshot());
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        allureStep("Test skipped: " + result.getMethod().getMethodName(),takeScreenshot());
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Implement if needed
    }
}
