package pageObjects;

import pageUIs.HomePageUI;
import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;

public class HomePageObj extends BasePage {
	private WebDriver driver;

	public HomePageObj(WebDriver driver) {
		this.driver = driver;
	}


	public RegisterPageObj clickToRegisterLink() {
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
	}
}
