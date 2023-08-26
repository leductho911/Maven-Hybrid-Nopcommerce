package commonTest;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Cookie;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import pageObjects.HomePageObj;
import pageObjects.LoginPageObj;
import pageObjects.RegisterPageObj;
import utils.DataFaker;
import utils.Environment;

import java.util.Objects;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RegisterAndLogin extends BaseTest {
	public static Set<Cookie> loginCookies;
	public static String email, password, firstName, lastName;
	Environment environment;
	private HomePageObj homePage;
	private RegisterPageObj registerPage;
	private LoginPageObj loginPage;
	private DataFaker dataFaker;

	@BeforeTest
	public void Register_And_Login(@Optional("local") String serviceName, @Optional("116") String browserVersion, @Optional("Windows") String osName, @Optional("10") String osVersion) {
		String environmentName = System.getProperty("environment");
		ConfigFactory.setProperty("env", Objects.requireNonNullElse(environmentName, "dev"));


		String browser = System.getProperty("browser");
		if (browser == null) {
			browser = "chrome";
		}

		environment = ConfigFactory.create(Environment.class);
		String appUrl = environment.appUrl();
		driver = getBrowserDriver(serviceName, browser, browserVersion, appUrl, osName, osVersion);

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
		registerPage.inputToTextboxByLabel("Email", email);
		registerPage.inputToTextboxByLabel("Password", password);
		registerPage.inputToTextboxByLabel("Confirm password", password);
		registerPage.clickToButton("Register");

		assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		registerPage.clickToLinkAtHeader("Log in");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.inputToTextboxByLabel("Email", email);
		loginPage.inputToTextboxByLabel("Password", password);
		loginPage.clickToButton("Log in");
		homePage = PageGeneratorManager.getHomePage(driver);

		assertTrue(homePage.isHomepageDisplayed());

		loginCookies = homePage.getAllCookies();

		driver.quit();
	}


}
