package commonTest;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageObjects.HomePageObj;
import pageObjects.RegisterPageObj;
import utils.DataFaker;
import utils.Environment;
import utils.Log;

import java.util.Objects;

import static org.testng.Assert.assertEquals;

public class RegisterOnly extends BaseTest {
	public static String email, password;
	Environment environment;
	private HomePageObj homePage;
	private RegisterPageObj registerPage;
	private String firstName, lastName;
	private DataFaker dataFaker;

	@BeforeTest
	@Parameters({"service", "browser_name", "browser_version", "os", "os_version"})
	public void Register_User(@Optional("local") String serviceName, @Optional("Chrome") String browserName, @Optional("latest") String browserVersion, @Optional("Windows") String osName, @Optional("10") String osVersion) {
		Log.info("Run on service: " + serviceName);
		Log.info("Run on browser: " + browserName);

		String environmentName = System.getProperty("environment");
		ConfigFactory.setProperty("env", Objects.requireNonNullElse(environmentName, "dev"));
		Log.info("Run on environment: " + environmentName);

		environment = ConfigFactory.create(Environment.class);
		String appUrl = environment.appUrl();
		Log.info("Run on url: " + appUrl);

		driver = getBrowserDriver(serviceName, browserName, browserVersion, appUrl, osName, osVersion);

		dataFaker = DataFaker.getDataFaker();
		firstName = dataFaker.getFirstName();
		lastName = dataFaker.getLastName();
		email = dataFaker.getEmail();
		password = dataFaker.getPassword();

		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToLinkAtHeader("Register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		registerPage.inputToTextboxByLabel("First name", firstName);
		registerPage.inputToTextboxByLabel("Last name", lastName);
		registerPage.inputToTextboxByLabel("Last name", lastName);
		registerPage.inputToTextboxByLabel("Email", email);
		registerPage.inputToTextboxByLabel("Password", password);
		registerPage.inputToTextboxByLabel("Confirm password", password);
		registerPage.clickToButton("Register");
		assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		driver.quit();
	}


}
