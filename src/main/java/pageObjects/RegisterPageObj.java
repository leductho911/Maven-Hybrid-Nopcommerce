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


	@Step("Click to Register button")
	public void clickToRegisterButton() {
		waitForElementClickable(RegisterPageUI.REGISTER_BUTTON);
		clickToElement(RegisterPageUI.REGISTER_BUTTON);
	}



	@Step("Input to Email Textbox: {0}")
	public void inputToEmailTextbox(String email) {
		waitForElementClickable(RegisterPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(RegisterPageUI.EMAIL_TEXTBOX, email);
	}

	@Step("Input to Firstname Textbox: {0}")
	public void inputToFirstnameTextbox(String firstName) {
		waitForElementClickable(RegisterPageUI.FIRSTNAME_TEXTBOX);
		sendKeysToElement(RegisterPageUI.FIRSTNAME_TEXTBOX, firstName);
	}

	@Step("Input to Lastname Textbox: {0}")
	public void inputToLastnameTextbox(String lastName){
		waitForElementClickable(RegisterPageUI.LASTNAME_TEXTBOX);
		sendKeysToElement(RegisterPageUI.LASTNAME_TEXTBOX,lastName);
	}

	@Step("Input to Password Textbox: {0}")
	public void inputToPasswordTextbox(String password){
		waitForElementClickable(RegisterPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(RegisterPageUI.PASSWORD_TEXTBOX,password);
	}

	@Step("Input to Confirm Password Textbox: {0}")
	public void inputToConfirmPasswordTextbox(String password){
		waitForElementClickable(RegisterPageUI.CONFIRMPASSWORD_TEXTBOX);
		sendKeysToElement(RegisterPageUI.CONFIRMPASSWORD_TEXTBOX,password);
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
