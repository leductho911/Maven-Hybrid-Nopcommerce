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

	@Step("Verify Gender is {0}")
	public boolean isGenderSelected(String gender) {
		waitForElementVisible(CustomerInfoPageUI.GENDER_RADIO_BUTTON, gender);
		return isElementSelected(CustomerInfoPageUI.GENDER_RADIO_BUTTON, gender);
	}

	@Step("Verify Firstname value")
	public String getFirstnameTextboxValue() {
		waitForElementVisible(CustomerInfoPageUI.FIRSTNAME_TEXTBOX);
		return getElementAttribute("value", CustomerInfoPageUI.FIRSTNAME_TEXTBOX);
	}

	@Step("Verify Lastname value")
	public String getLastnameTextboxValue() {
		waitForElementVisible(CustomerInfoPageUI.LASTNAME_TEXTBOX);
		return getElementAttribute("value", CustomerInfoPageUI.LASTNAME_TEXTBOX);
	}

	@Step("Verify Day of birth value")
	public String getDayOfBirth() {
		waitForElementVisible(CustomerInfoPageUI.DATE_DROPDOWN);
		return getSelectedItemDefaultDropdown(CustomerInfoPageUI.DATE_DROPDOWN);
	}

	@Step("Verify Month of birth value")
	public String getMonthOfBirth() {
		waitForElementVisible(CustomerInfoPageUI.MONTH_DROPDOWN);
		return getSelectedItemDefaultDropdown(CustomerInfoPageUI.MONTH_DROPDOWN);
	}

	@Step("Verify Year of birth value")
	public String getYearOfBirth() {
		waitForElementVisible(CustomerInfoPageUI.YEAR_DROPDOWN);
		return getSelectedItemDefaultDropdown(CustomerInfoPageUI.YEAR_DROPDOWN);
	}

	@Step("Verify Company name value")
	public String getCompanyName() {
		waitForElementVisible(CustomerInfoPageUI.COMPANY_NAME_TEXTBOX);
		return getElementAttribute("value", CustomerInfoPageUI.COMPANY_NAME_TEXTBOX);
	}
}
