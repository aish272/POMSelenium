package commonMethods;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterTestNg {

    public static ExtentReports getReportObject()
    {
        String path = "/Users/aish/Documents/POMSelenium/test-output/index.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
        sparkReporter.config().setReportName("E-commerceTest");
        sparkReporter.config().setDocumentTitle("Extent Reporter");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Env","UAT");
        return extentReports;
    }
}
