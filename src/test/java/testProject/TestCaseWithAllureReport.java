package testProject;

import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import pageObjects.HomePageObj;
import pageObjects.RegisterPageObj;

public class TestCaseWithAllureReport extends BaseTest {
	private HomePageObj homePage;
	private RegisterPageObj registerPage;


	@Description("Register to system")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void User_01_Register() {

		homePage = PageGeneratorManager.getHomePage(driver);

		registerPage = homePage.clickToRegisterLink();

		registerPage.clickToRegisterButton();

		//Assert.assertFalse(registerPage.isFirstNameErrorMessageDisplayed());

		//Assert.assertTrue(registerPage.isFirstNameErrorMessageDisplayed());
	}

}
