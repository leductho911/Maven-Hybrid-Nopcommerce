package serviceFactory;

import commons.BrowserList;
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

		URL remoteAddress = null;
		try {
			remoteAddress = new URL(String.format("http://%s:%s", ipAddress, port));
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
		switch (browser) {
			case FIREFOX:
				FirefoxOptions firefoxOptions = new FirefoxOptions();
//				firefoxOptions.setCapability("browserVersion", browserVersion);
//				firefoxOptions.setCapability("platformName", "linux");
//				firefoxOptions.setCapability("browserVersion", "116.0");
//				firefoxOptions.setCapability("browserName", browserName);
				driver = new RemoteWebDriver(remoteAddress, firefoxOptions);
				break;
			case CHROME:
				ChromeOptions chromeOptions = new ChromeOptions();
//				chromeOptions.setCapability("browserVersion", browserVersion);
//				chromeOptions.setCapability("platformName", "linux");
//				chromeOptions.setCapability("browserVersion", "115.0");
//				chromeOptions.setCapability("browserName", browserName);
				driver = new RemoteWebDriver(remoteAddress, chromeOptions);
				break;
			default:
				throw new RuntimeException("Please input valid browser name value!");
		}
		driver.get(appUrl);
		return driver;
	}


}