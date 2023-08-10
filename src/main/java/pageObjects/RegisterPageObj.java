package pageObjects;

//import io.qameta.allure.Step;
//import org.testng.Assert;
import io.qameta.allure.Step;
import pageUIs.RegisterPageUI;
import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class RegisterPageObj extends BasePage {
	private WebDriver driver;

	public RegisterPageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}



	@Step("Verify Email error message")
	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(RegisterPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(RegisterPageUI.EMAIL_ERROR_MESSAGE);
	}

	@Step("Verify Firstname error message")
	public String getErrorMessageAtFirstnameTextbox() {
		waitForElementVisible(RegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
		return getElementText(RegisterPageUI.FIRSTNAME_ERROR_MESSAGE);
	}
	@Step("Verify Lastname error message")
	public String getErrorMessageAtLastnameTextbox() {
		waitForElementVisible(RegisterPageUI.LASTNAME_ERROR_MESSAGE);
		return getElementText(RegisterPageUI.LASTNAME_ERROR_MESSAGE);
	}


	@Step("Verify Password error message")
	public String getErrorMessageAtPasswordTextbox() {
		waitForElementVisible(RegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return getElementText(RegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}
	@Step("Verify Confirm Email error message")
	public String getErrorMessageAtConfirmPasswordTextbox() {
		waitForElementVisible(RegisterPageUI.CONFIRMPASSWORD_ERROR_MESSAGE);
		return getElementText(RegisterPageUI.CONFIRMPASSWORD_ERROR_MESSAGE);
	}
	@Step("Verify Register success message")
	public String getRegisterSuccessMessage() {
		waitForElementVisible(RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}
	@Step("Verify Register fail message")
	public String getRegisterFailMessage() {
		waitForElementVisible(RegisterPageUI.REGISTER_FAIL_MESSAGE);
		return getElementText(RegisterPageUI.REGISTER_FAIL_MESSAGE);
	}
}
