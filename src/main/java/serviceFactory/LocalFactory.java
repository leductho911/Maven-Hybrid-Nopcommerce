package serviceFactory;

import commons.BrowserList;
import exception.BrowserNotSupport;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class LocalFactory {
	private WebDriver driver;

	public WebDriver createDriver(String browserName, String appUrl) {
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

		driver.get(appUrl);
		return driver;
	}

}
