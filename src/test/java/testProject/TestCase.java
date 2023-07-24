package testProject;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.testng.annotations.Test;
import pageObjects.HomePageObj;
import pageObjects.RegisterPageObj;
import utils.Log;

public class TestCase extends BaseTest {
	private HomePageObj homePage;
	private RegisterPageObj registerPage;

	@Test
	public void testName() {
		homePage = PageGeneratorManager.getHomePage(driver);
		Log.info("open Homepage");
		registerPage = homePage.clickToRegisterLink();
		registerPage.clickToRegisterButton();
		// Assert.assertTrue(registerPage.isFirstNameErrorMessageDisplayed());
	}

}
