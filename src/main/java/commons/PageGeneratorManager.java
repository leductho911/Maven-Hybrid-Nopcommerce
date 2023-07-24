package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.CustomerInfoPageObj;
import pageObjects.HomePageObj;
import pageObjects.LoginPageObj;
import pageObjects.RegisterPageObj;


public class PageGeneratorManager {
	public static HomePageObj getHomePage(WebDriver driver) {
		return new HomePageObj(driver);
	}

	public static RegisterPageObj getRegisterPage(WebDriver driver) {
		return new RegisterPageObj(driver);
	}

	public static LoginPageObj getLoginPage(WebDriver driver) {
		return new LoginPageObj(driver);
	}

	public static CustomerInfoPageObj getCustomerInfoPage(WebDriver driver) {
		return new CustomerInfoPageObj(driver);
	}
}
