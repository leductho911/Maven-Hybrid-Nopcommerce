package commons;

import exception.BrowserNotSupport;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;
import reportConfig.ScreenRecorderUtil;
import utils.Environment;
import utils.Log;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;
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

	@BeforeSuite
	public void initBeforeSuite() {
		deleteAllureReport();
	}

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		String environmentName = System.getProperty("environment");
		if (environmentName != null) {
			ConfigFactory.setProperty("env", environmentName);
		} else {
			throw new IllegalStateException("'environment' system property is not set.");
		}

		environment = ConfigFactory.create(Environment.class);
		driver = getBrowserDriver(browserName, environment.appUrl());

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		if (driver != null) {
			driver.quit();
		}
	}

	@BeforeMethod
	public void beforeMethod(Method method) throws Exception {
		ScreenRecorderUtil.startRecord(method.getName());
	}

	@AfterMethod
	public void afterMethod() throws Exception {
		sleepInMilisecond(500);
		ScreenRecorderUtil.stopRecord();
	}

	protected WebDriver getBrowserDriver(String browserName, String appUrl) {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());

		switch (browser) {
			case FIREFOX:
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case CHROME:
				WebDriverManager.chromedriver().setup();
				ChromeOptions options2 = new ChromeOptions();
				options2.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
				driver = new ChromeDriver(options2);
				break;
			case EDGE:
				WebDriverManager.edgedriver().arch64().setup();
				driver = new EdgeDriver();
				break;
			case IE:
				WebDriverManager.iedriver().arch64().setup();
				driver = new EdgeDriver();
				break;
			case SAFARI:
				driver = new SafariDriver();
				break;
			case CHROME_HEADLESS:
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("headless");
				options.addArguments("window-size=1920x1080");
				driver = new ChromeDriver(options);
				break;
			case FIREFOX_HEADLESS:
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions options1 = new FirefoxOptions();
				options1.addArguments("--headless");
				options1.addArguments("window-size=1920x1080");
				driver = new FirefoxDriver(options1);
				break;
			default:
				throw new BrowserNotSupport(browserName);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		driver.get(appUrl);
		driver.manage().window().maximize();
		return driver;
	}

	protected int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);

	}

	public void deleteAllureReport() {
		try {
			String pathFolderDownload = GlobalConstants.PROJECT_PATH + "/allure-results";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
	}

	public void sleepInMilisecond(long miliSecond) {
		try {
			Thread.sleep(miliSecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
