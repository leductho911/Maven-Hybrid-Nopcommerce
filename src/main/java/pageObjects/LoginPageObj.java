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
	@Step("Click to Login Button")
	public HomePageObj clickToLoginButton() {
		waitForElementClickable(LoginPageUI.LOGIN_BUTTON);
		clickToElement(LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getHomePage(driver);
	}
	@Step("Input to Email textbox : {0}")
	public void inputToEmailTextbox(String email) {
		waitForElementClickable(LoginPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(LoginPageUI.EMAIL_TEXTBOX, email);
	}
	@Step("Input to Password textbox: {0}")
	public void inputToPasswordTextbox(String password) {
		waitForElementClickable(LoginPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(LoginPageUI.PASSWORD_TEXTBOX, password);
	}
	@Step("Verify error message at Login page")
	public String getErrorMessageAtLoginPage() {
		waitForElementVisible(LoginPageUI.LOGIN_ERROR_MESSAGE);
		return getElementText(LoginPageUI.LOGIN_ERROR_MESSAGE);
	}
}
