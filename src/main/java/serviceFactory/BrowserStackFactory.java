package serviceFactory;

import commons.GlobalConstants;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class BrowserStackFactory {
	private WebDriver driver;

	public WebDriver createDriver(String browserName, String browserVersion, String appUrl, String osName, String osVersion) {
		MutableCapabilities capabilities = new MutableCapabilities();
		capabilities.setCapability("browserName", browserName);
		HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
		browserstackOptions.put("os", osName);
		browserstackOptions.put("osVersion", osVersion);
		browserstackOptions.put("browserVersion", browserVersion);
		browserstackOptions.put("projectName", "Nopcommerce");
		browserstackOptions.put("sessionName", "Run on " + osName + " " + osVersion + " and " + browserName + " version " + browserVersion);
		browserstackOptions.put("seleniumVersion", "4.9.1");
		browserstackOptions.put("buildName", "dev_0.1.7");
		capabilities.setCapability("bstack:options", browserstackOptions);

		try {
			driver = new RemoteWebDriver(new URL(GlobalConstants.BROWSER_STACK_URL), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.get(appUrl);
		return driver;
	}

}
