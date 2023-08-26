package reportConfig;


import commons.BaseTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.Log;

public class AllureTestListener implements ITestListener {

	// Screenshot attachments for Allure
	@Attachment(value = "Screenshot of {0}", type = "image/png")
	public static byte[] saveScreenshotPNG(String testName, WebDriver driver) {
		return (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	public String getTestName(ITestResult iTestResult) {
		return iTestResult.getTestName() != null ? iTestResult.getTestName() : iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	public String getTestDescription(ITestResult iTestResult) {
		return iTestResult.getMethod().getDescription() != null ? iTestResult.getMethod().getDescription() : getTestName(iTestResult);
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		Log.info(iTestResult.getName() + " is failed.");
		Object testClass = iTestResult.getInstance();
		WebDriver driver = ((BaseTest) testClass).getDriverInstance();
		saveScreenshotPNG(iTestResult.getName(), driver);
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		Log.info(iTestResult.getName() + " is passed.");
		Object testClass = iTestResult.getInstance();
		WebDriver driver = ((BaseTest) testClass).getDriverInstance();
		saveScreenshotPNG(iTestResult.getName(), driver);
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		Log.info(iTestResult.getName() + " is skipped.");
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		Log.info("Starting Suite: " + iTestContext.getStartDate());
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		Log.info("Finish Suite: " + iTestContext.getEndDate());
		Log.info("----------------------------");
	}


	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		// TODO Auto-generated method stub
	}


	@Override
	public void onTestStart(ITestResult iTestResult) {
		// TODO Auto-generated method stub
	}


}