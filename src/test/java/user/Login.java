package user;

import commonTest.RegisterOnly;
import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import pageObjects.HomePageObj;
import pageObjects.LoginPageObj;
import utils.DataFaker;
import utils.DataUtil;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Login extends BaseTest {
	private HomePageObj homePage;
	private LoginPageObj loginPage;

	@Test
	public void  Login_01_LoginWithEmptyData(){
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToLinkAtHeader("Log in");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.clickToButton("Login");
		assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
	}

	@Test (dataProviderClass = DataUtil.class, dataProvider = "invalidUserDataProvider")
	public void  Login_02_LoginWithInvalidEmail(String data){
		String[] dataInfo = data.split(",");
		loginPage.clickToLinkAtHeader("Log in");
		loginPage.inputToTextboxByLabel("Email", dataInfo[2]);
		loginPage.clickToButton("Login");
		assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void  Login_03_LoginWithUnregisteredEmail(){
		loginPage.clickToLinkAtHeader("Log in");
		loginPage.inputToTextboxByLabel("Email", DataFaker.getDataFaker().getEmail());
		loginPage.inputToTextboxByLabel("Password", DataFaker.getDataFaker().getPassword());
		loginPage.clickToButton("Login");
		assertEquals(loginPage.getErrorMessageAtLoginPage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found");

	}

	@Test
	public void  Login_04_LoginWithRegisteredEmailEmptyPassword(){
		loginPage.clickToLinkAtHeader("Log in");
		loginPage.inputToTextboxByLabel("Email", RegisterOnly.email);
		loginPage.clickToButton("Login");
		assertEquals(loginPage.getErrorMessageAtLoginPage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");

	}

	@Test
	public void  Login_05_LoginWithRegisteredEmailWrongPassword(){
		loginPage.clickToLinkAtHeader("Log in");
		loginPage.inputToTextboxByLabel("Email", RegisterOnly.email);
		loginPage.inputToTextboxByLabel("Password", DataFaker.getDataFaker().getPassword());
		loginPage.clickToButton("Login");
		assertEquals(loginPage.getErrorMessageAtLoginPage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");

	}

	@Description("Login to system")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void  Login_06_LoginSuccess(){
		loginPage.clickToLinkAtHeader("Log in");
		loginPage.inputToTextboxByLabel("Email", RegisterOnly.email);
		loginPage.inputToTextboxByLabel("Password", RegisterOnly.password);
		loginPage.clickToButton("Login");
		assertTrue(loginPage.isHomepageDisplayed());
	}

}
