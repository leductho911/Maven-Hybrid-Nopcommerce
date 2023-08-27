package serviceFactory;

import commons.BrowserList;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class GridFactory {
	private WebDriver driver;

	public WebDriver createDriver(String browserName, String osName, String browserVersion, String ipAddress, String port, String appUrl) {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());

		URL remoteAddress;
		try {
			remoteAddress = new URL(String.format("http://%s:%s/wd/hub", ipAddress, port));
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}

		switch (browser) {
			case FIREFOX:
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions firefoxOptions = new FirefoxOptions();
//				firefoxOptions.setCapability("browserVersion", browserVersion);
				firefoxOptions.setCapability("platformName", osName);
				firefoxOptions.setCapability("se:name", "My simple test");
				firefoxOptions.setCapability("se:sampleMetadata", "Sample metadata value");
				driver = new RemoteWebDriver(remoteAddress, firefoxOptions);
				break;
			case CHROME:
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
//				chromeOptions.setCapability("browserVersion", browserVersion);
				chromeOptions.setCapability("platformName", osName);
				chromeOptions.setCapability("se:name", "My simple test");
				chromeOptions.setCapability("se:sampleMetadata", "Sample metadata value");
				driver = new RemoteWebDriver(remoteAddress, chromeOptions);
				break;
			default:
				throw new RuntimeException("Please input valid browser name value!");
		}

		driver.get(appUrl);
		return driver;
	}


}