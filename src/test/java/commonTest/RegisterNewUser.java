package commonTest;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageObjects.HomePageObj;
import pageObjects.RegisterPageObj;
import utils.DataFaker;
import utils.Environment;

import static org.testng.Assert.assertEquals;

public class RegisterNewUser extends BaseTest {
	private HomePageObj homePage;
	private RegisterPageObj registerPage;
	public static String email, password;
	private String firstName, lastName;
	private DataFaker dataFaker;

	@BeforeTest
	@Parameters("browser")
	public void Register_User(String browserName){
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

		driver.quit();
	}



}
