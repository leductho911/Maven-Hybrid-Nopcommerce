package pageObjects;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.CustomerInfoPageUI;

public class CustomerInfoPageObj extends BasePage {
	private WebDriver driver;

	public CustomerInfoPageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	@Step("Select gender: {0}")
	public void selectGenderByLabel(String gender) {
		waitForElementClickable(CustomerInfoPageUI.GENDER_RADIO_BUTTON, gender);
		checkToDefaultCheckboxOrRadio(CustomerInfoPageUI.GENDER_RADIO_BUTTON, gender);
	}
	@Step("Input to Firstname textbox: {0}")
	public void inputToFirstnameTextbox(String firstName) {
		waitForElementClickable(CustomerInfoPageUI.FIRSTNAME_TEXTBOX);
		sendKeysToElement(CustomerInfoPageUI.FIRSTNAME_TEXTBOX, firstName);

	}
	@Step("Input to Lastname textbox: {0}")
	public void inputToLastnameTextbox(String lastName) {
		waitForElementClickable(CustomerInfoPageUI.LASTNAME_TEXTBOX);
		sendKeysToElement(CustomerInfoPageUI.LASTNAME_TEXTBOX, lastName);
	}
	@Step("Select day of birth: {0}")
	public void selectDayofbirth(String day) {
		waitForElementClickable(CustomerInfoPageUI.DATE_DROPDOWN);
		selectItemInDefaultDropdown(CustomerInfoPageUI.DATE_DROPDOWN, day);
	}
	@Step("Select month of birth: {0}")
	public void selectMonthofbirth(String month) {
		waitForElementClickable(CustomerInfoPageUI.MONTH_DROPDOWN);
		selectItemInDefaultDropdown(CustomerInfoPageUI.MONTH_DROPDOWN, month);
	}
	@Step("Select year of birth: {0}")
	public void selectYearofbirth(String year) {
		waitForElementClickable(CustomerInfoPageUI.YEAR_DROPDOWN);
		selectItemInDefaultDropdown(CustomerInfoPageUI.YEAR_DROPDOWN, year);
	}
	@Step("Input to Email textbox: {0}")
	public void inputToEmailTextbox(String email) {
		waitForElementClickable(CustomerInfoPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(CustomerInfoPageUI.EMAIL_TEXTBOX, email);
	}
	@Step("Input to Company name textbox: {0}")
	public void inputToCompanynameTextbox(String companyName) {
		waitForElementClickable(CustomerInfoPageUI.COMPANY_NAME_TEXTBOX);
		sendKeysToElement(CustomerInfoPageUI.COMPANY_NAME_TEXTBOX, companyName);
	}
	@Step("Click to Save button")
	public void clickToSaveButton() {
		waitForElementClickable(CustomerInfoPageUI.SAVE_BUTTON);
		clickToElement(CustomerInfoPageUI.SAVE_BUTTON);
	}
	@Step("Verify Updated success message appear")
	public String getUpdatedSuccessMessage() {
		waitForElementVisible(CustomerInfoPageUI.UPDATED_SUCCESS_MESSAGE);
		return getElementText(CustomerInfoPageUI.UPDATED_SUCCESS_MESSAGE);
	}
}
