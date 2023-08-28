package serviceFactory;

import commons.BrowserList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class GridFactory {
	private WebDriver driver;

	public WebDriver createDriver(String browserName, String osName, String browserVersion, String appUrl) {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());


		URL remoteAddress;
		try {
			remoteAddress = new URL("http://localhost:4444");
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
			case EDGE:
				EdgeOptions edgeOptions = new EdgeOptions();
				driver = new RemoteWebDriver(remoteAddress, edgeOptions);
				break;
			default:
				throw new RuntimeException("Please input valid browser name value!");
		}
		driver.get(appUrl);
		return driver;
	}


}