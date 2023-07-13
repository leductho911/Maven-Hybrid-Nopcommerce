import commons.BaseTest;
import commons.PageGeneratorManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePageObj;
import pageObjects.RegisterPageObj;
import ultilities.Environment;

public class TestCase extends BaseTest {
	private WebDriver driver;
	private HomePageObj homePage;
	private RegisterPageObj registerPage;
	Environment environment;

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

		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void testName() {
		registerPage = homePage.clickToRegisterLink();
		registerPage.clickToRegisterButton();
		registerPage.isErrorMessageDisplayed();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
