package user;

import commonTest.RegisterNewUser;
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
		loginPage = homePage.clickToLoginLink();
		loginPage.clickToLoginButton();
		assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
	}

	@Test (dataProviderClass = DataUtil.class, dataProvider = "invalidUserDataProvider")
	public void  Login_02_LoginWithInvalidEmail(String data){
		String[] dataInfo = data.split(",");
		loginPage.clickToLoginLink();
		loginPage.inputToEmailTextbox(dataInfo[2]);
		loginPage.clickToLoginButton();
		assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void  Login_03_LoginWithUnregisteredEmail(){
		loginPage.clickToLoginLink();
		loginPage.inputToEmailTextbox(DataFaker.getDataFaker().getEmail());
		loginPage.inputToPasswordTextbox(DataFaker.getDataFaker().getPassword());
		loginPage.clickToLoginButton();
		assertEquals(loginPage.getErrorMessageAtLoginPage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found");

	}

	@Test
	public void  Login_04_LoginWithRegisteredEmailEmptyPassword(){
		loginPage.clickToLoginLink();
		loginPage.inputToEmailTextbox(RegisterNewUser.email);
		loginPage.clickToLoginButton();
		assertEquals(loginPage.getErrorMessageAtLoginPage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");

	}

	@Test
	public void  Login_05_LoginWithRegisteredEmailWrongPassword(){
		loginPage.clickToLoginLink();
		loginPage.inputToEmailTextbox(RegisterNewUser.email);
		loginPage.inputToPasswordTextbox(DataFaker.getDataFaker().getPassword());
		loginPage.clickToLoginButton();
		assertEquals(loginPage.getErrorMessageAtLoginPage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");

	}

	@Description("Login to system")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void  Login_06_LoginSuccess(){
		loginPage.clickToLoginLink();
		loginPage.inputToEmailTextbox(RegisterNewUser.email);
		loginPage.inputToPasswordTextbox(RegisterNewUser.password);
		loginPage.clickToLoginButton();
		assertTrue(loginPage.isHomepageDisplayed());
	}

}
