package pageObjects;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.AddressesPageUI;

public class AddressesPageObj extends BasePage {

	public AddressesPageObj(WebDriver driver) {
		super(driver);
	}

	@Step("Verify Address Name")
	public String getAddressName() {
		waitForElementVisible(AddressesPageUI.ADDRESS_NAME);
		return getElementText(AddressesPageUI.ADDRESS_NAME);
	}

	@Step("Verify Address Email")
	public String getAddressEmail() {
		waitForElementVisible(AddressesPageUI.ADDRESS_EMAIL);
		return getElementText(AddressesPageUI.ADDRESS_EMAIL);
	}

	@Step("Verify Address Phone")
	public String getAddressPhone() {
		waitForElementVisible(AddressesPageUI.ADDRESS_PHONE);
		return getElementText(AddressesPageUI.ADDRESS_PHONE);
	}

	@Step("Verify Address Fax")
	public String getAddressFax() {
		waitForElementVisible(AddressesPageUI.ADDRESS_FAX);
		return getElementText(AddressesPageUI.ADDRESS_FAX);
	}

	@Step("Verify Address Company")
	public String getAddressCompany() {
		waitForElementVisible(AddressesPageUI.ADDRESS_COMPANY);
		return getElementText(AddressesPageUI.ADDRESS_COMPANY);
	}

	@Step("Verify Address 1")
	public String getAddress1() {
		waitForElementVisible(AddressesPageUI.ADDRESS_ADDRESS1);
		return getElementText(AddressesPageUI.ADDRESS_ADDRESS1);
	}

	@Step("Verify Address 2")
	public String getAddress2() {
		waitForElementVisible(AddressesPageUI.ADDRESS_ADDRESS2);
		return getElementText(AddressesPageUI.ADDRESS_ADDRESS2);
	}

	@Step("Verify Address : City, State, Zip")
	public String getAddressCityStateZip() {
		waitForElementVisible(AddressesPageUI.ADDRESS_CITY_STATE_ZIP);
		return getElementText(AddressesPageUI.ADDRESS_CITY_STATE_ZIP);
	}

	@Step("Verify Address Country")
	public String getAddressCountry() {
		waitForElementVisible(AddressesPageUI.ADDRESS_COUNTRY);
		return getElementText(AddressesPageUI.ADDRESS_COUNTRY);
	}
}
