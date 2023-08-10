package commonTest;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Parameters;
import pageObjects.HomePageObj;
import pageObjects.LoginPageObj;
import utils.Environment;

import java.util.Set;

import static org.testng.Assert.assertTrue;

public class LoginOnly extends BaseTest {
	private HomePageObj homePage;
	private LoginPageObj loginPage;
	public static Set<Cookie> loginCookies;

	//@BeforeTest
	@Parameters("browser")
	public void LoginToSystemOnly(String browserName){
		String environmentName = System.getProperty("environment");
		if (environmentName != null) {
			ConfigFactory.setProperty("env", environmentName);
		} else {
			throw new IllegalStateException("'environment' system property is not set.");
		}

		Environment environment = ConfigFactory.create(Environment.class);
		driver = getBrowserDriver(browserName, environment.appUrl());

		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToLinkAtHeader("Log in");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.inputToTextboxByLabel("Email", RegisterOnly.email);
		loginPage.inputToTextboxByLabel("Password", RegisterOnly.password);
		loginPage.clickToButton("Log in");
		homePage = PageGeneratorManager.getHomePage(driver);
		assertTrue(homePage.isHomepageDisplayed());

		loginCookies = homePage.getAllCookies();

		driver.quit();
	}



}
