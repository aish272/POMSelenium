package commonUtilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import commonMethods.ExtentReporterTestNg;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extentReports = ExtentReporterTestNg.getReportObject();
    ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<ExtentTest>();

    public void onTestStart(ITestResult result) {

        test = extentReports.createTest(result.getMethod().getMethodName());
        threadLocal.set(test);

    }

    /**
     * Invoked each time a test succeeds.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS
     */
    public void onTestSuccess(ITestResult result) {
        threadLocal.get().log(Status.PASS, "Test has passed");

    }

    /**
     * Invoked each time a test fails.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#FAILURE
     */
    public void onTestFailure(ITestResult result) {
        threadLocal.get().fail(result.getThrowable());
        String filePath = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").
                    get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            filePath = takeScreenshot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
           e.printStackTrace();
        }
        threadLocal.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName() + "failed");
    }

    public void onFinish(ITestContext context) {
        extentReports.flush();
    }


}
