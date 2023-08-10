package user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HomePageObj;
import pageObjects.RegisterPageObj;
import utils.DataFaker;
import utils.DataUtil;

import static org.testng.Assert.assertEquals;

public class Register extends BaseTest {
	private HomePageObj homePage;
	private RegisterPageObj registerPage;
	private String firstName, lastName, email, password, confirmPassword;
	private DataFaker dataFaker;

	@BeforeClass
	public void initBeforeClass() {
		dataFaker = DataFaker.getDataFaker();
		firstName = dataFaker.getFirstName();
		lastName = dataFaker.getLastName();
		email = dataFaker.getEmail();
		password = dataFaker.getPassword();
		confirmPassword = dataFaker.getPassword();
	}


	@Test
	public void Register_01_RegisterWithEmptyData() {
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToLinkAtHeader("Register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		registerPage.clickToButton("Register");
		assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(), "First name is required.");
		assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "Last name is required.");
		assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
	}
	@Test(dataProviderClass = DataUtil.class, dataProvider = "invalidUserDataProvider")
	public void Register_02_RegisterWithInvalidEmail(String data) {
		String[] dataInfo = data.split(",");
		registerPage.clickToLinkAtHeader("Register");
		registerPage.inputToTextboxByLabel("Email", dataInfo[2]);
		registerPage.clickToButton("Register");
		assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}
	@Description("Register to system")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void Register_03_RegisterSuccess() {
		registerPage.clickToLinkAtHeader("Register");
		registerPage.inputToTextboxByLabel("First name", firstName);
		registerPage.inputToTextboxByLabel("Last name", lastName);
		registerPage.inputToTextboxByLabel("Email", email);
		registerPage.inputToTextboxByLabel("Password", password);
		registerPage.inputToTextboxByLabel("Confirm password", password);
		registerPage.clickToButton("Register");
		assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

	}

	@Test
	public void Register_04_RegisterFailExistingAccount() {
		registerPage.clickToLinkAtHeader("Register");
		registerPage.inputToTextboxByLabel("First name", firstName);
		registerPage.inputToTextboxByLabel("Last name", lastName);
		registerPage.inputToTextboxByLabel("Email", email);
		registerPage.inputToTextboxByLabel("Password", password);
		registerPage.inputToTextboxByLabel("Confirm password", password);
		registerPage.clickToButton("Register");
		assertEquals(registerPage.getRegisterFailMessage(), "The specified email already exists");
	}

	@Test
	public void Register_05_RegisterWithPasswordLess6Char() {
		String randomString = randomString(5);
		registerPage.clickToLinkAtHeader("Register");
		registerPage.inputToTextboxByLabel("First name", firstName);
		registerPage.inputToTextboxByLabel("Last name", lastName);
		registerPage.inputToTextboxByLabel("Email", email);
		registerPage.inputToTextboxByLabel("Password", randomString);
		registerPage.inputToTextboxByLabel("Confirm password", randomString);
		registerPage.clickToButton("Register");
		assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\n" + "must have at least 6 characters");
	}

	@Test
	public void Register_06_RegisterWithUnmatchedConfirmPassword() {
		registerPage.clickToLinkAtHeader("Register");
		registerPage.inputToTextboxByLabel("First name", firstName);
		registerPage.inputToTextboxByLabel("Last name", lastName);
		registerPage.inputToTextboxByLabel("Email", email);
		registerPage.inputToTextboxByLabel("Password", password);
		registerPage.inputToTextboxByLabel("Confirm password", password);
		registerPage.clickToButton("Register");
		assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");
	}


}
