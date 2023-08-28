package commons;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import reportConfig.ScreenRecorderUtil;
import serviceFactory.BrowserStackFactory;
import serviceFactory.GridFactory;
import serviceFactory.LocalFactory;
import utils.Environment;
import utils.Log;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Objects;
import java.util.Random;

public class BaseTest {
	protected WebDriver driver;
	Environment environment;

	public static String randomString(int maxLength) {
		if (maxLength <= 0) {
			throw new IllegalArgumentException("Max length must be greater than 0.");
		}

		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();

		int length = random.nextInt(maxLength) + 1;
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(characters.length());
			char randomChar = characters.charAt(randomIndex);
			sb.append(randomChar);
		}
		return sb.toString();
	}

	public WebDriver getDriverInstance() {
		return this.driver;
	}


	@BeforeClass
	@Parameters({"service", "browser_name", "browser_version", "os", "os_version"})
	public void beforeClass(@Optional("local") String serviceName, @Optional("Chrome") String browserName, @Optional("latest") String browserVersion, @Optional("Windows") String osName, @Optional("10") String osVersion) {
		Log.info("Run on service: " + serviceName);
		Log.info("Run on browser: " + browserName);

		String environmentName = System.getProperty("environment");
		ConfigFactory.setProperty("env", Objects.requireNonNullElse(environmentName, "dev"));
		Log.info("Run on environment: " + environmentName);

		environment = ConfigFactory.create(Environment.class);
		String appUrl = environment.appUrl();
		Log.info("Run on url: " + appUrl);

		driver = getBrowserDriver(serviceName, browserName, browserVersion, appUrl, osName, osVersion);

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		if (driver != null) {
			driver.quit();
		}
	}

	@BeforeMethod
	@Parameters({"service"})
	public void beforeMethod(String serviceName, Method method) throws Exception {
		if (Objects.equals(serviceName, "local")) {
			ScreenRecorderUtil.startRecord(method.getName());
		}
	}

	@AfterMethod
	@Parameters({"service"})
	public void afterMethod(String serviceName) throws Exception {
		if (Objects.equals(serviceName, "local")) {
			sleepInMilisecond(500);
			ScreenRecorderUtil.stopRecord();
		}

	}


	protected WebDriver getBrowserDriver(String serviceName, String browserName, String browserVersion, String appUrl, String osName, String osVersion) {

		switch (serviceName) {
			case "local":
				driver = new LocalFactory().createDriver(browserName, appUrl);
				break;
			case "browserstack":
				driver = new BrowserStackFactory().createDriver(browserName, browserVersion, appUrl, osName, osVersion);
				break;
			case "grid":
				driver = new GridFactory().createDriver(browserName, osName, browserVersion, appUrl);
				break;
			default:
				throw new IllegalArgumentException("Invalid service name: " + serviceName);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		driver.manage().window().maximize();
		return driver;
	}


	protected int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);

	}


	public void sleepInMilisecond(long miliSecond) {
		try {
			Thread.sleep(miliSecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}