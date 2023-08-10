package commonTest;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Cookie;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageObjects.HomePageObj;
import pageObjects.LoginPageObj;
import pageObjects.RegisterPageObj;
import utils.DataFaker;
import utils.Environment;

import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RegisterAndLogin extends BaseTest {
	private HomePageObj homePage;
	private RegisterPageObj registerPage;

	private LoginPageObj loginPage;
	public static Set<Cookie> loginCookies;
	private DataFaker dataFaker;

	public static String email, password, firstName, lastName;
	@BeforeTest
	@Parameters("browser")
	public void Register_And_Login (String browserName){
		String environmentName = System.getProperty("environment");
		if (environmentName != null) {
			ConfigFactory.setProperty("env", environmentName);
		} else {
			throw new IllegalStateException("'environment' system property is not set.");
		}

		Environment environment = ConfigFactory.create(Environment.class);
		driver = getBrowserDriver(browserName, environment.appUrl());

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
