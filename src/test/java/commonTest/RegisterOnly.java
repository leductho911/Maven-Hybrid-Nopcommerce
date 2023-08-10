package commonTest;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.Parameters;
import pageObjects.HomePageObj;
import pageObjects.RegisterPageObj;
import utils.DataFaker;
import utils.Environment;

import static org.testng.Assert.assertEquals;

public class RegisterOnly extends BaseTest {
	private HomePageObj homePage;
	private RegisterPageObj registerPage;
	public static String email, password;
	private String firstName, lastName;
	private DataFaker dataFaker;

	//@BeforeTest
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
