package pageObjects;

import pageUIs.RegisterPageUI;
import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class RegisterPageObj extends BasePage {
	private WebDriver driver;

	public RegisterPageObj(WebDriver driver) {
		this.driver = driver;
	}


	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}

	public void isErrorMessageDisplayed() {
		isElementDisplayed(driver, RegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
	}
}
