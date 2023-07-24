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

public class LoginSuccess extends BaseTest {
	private HomePageObj homePage;
	private RegisterPageObj registerPage;

	private LoginPageObj loginPage;
	public static Set<Cookie> loginCookies;
	private DataFaker dataFaker;

	private String email, password, firstName, lastName;
	@BeforeTest
	@Parameters("browser")
	public void Login (String browserName){
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
		registerPage = homePage.clickToRegisterLink();
		registerPage.clickToRegisterLink();
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.clickToRegisterButton();
		assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		loginPage = registerPage.clickToLoginLink();
		loginPage.inputToEmailTextbox(email);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickToLoginButton();
		assertTrue(homePage.isHomepageDisplayed());

		loginCookies = homePage.getAllCookies();

		driver.quit();
	}



}
