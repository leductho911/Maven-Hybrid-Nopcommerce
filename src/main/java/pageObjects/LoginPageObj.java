package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginPageUI;

public class LoginPageObj extends BasePage {
	private WebDriver driver;

	public LoginPageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	@Step("Verify error message at Email textbox")
	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(LoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(LoginPageUI.EMAIL_ERROR_MESSAGE);
	}


	@Step("Verify error message at Login page")
	public String getErrorMessageAtLoginPage() {
		waitForElementVisible(LoginPageUI.LOGIN_ERROR_MESSAGE);
		return getElementText(LoginPageUI.LOGIN_ERROR_MESSAGE);
	}
}
