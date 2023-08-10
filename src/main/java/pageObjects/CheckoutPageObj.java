package pageObjects;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.CheckoutPageUI;

public class CheckoutPageObj extends BasePage {
	private WebDriver driver;

	public CheckoutPageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}


	@Step("Click to button {1} at section {0}")
	public void clickToButtonBySection(String section, String button) {
		waitForElementClickable(CheckoutPageUI.SECTION_BUTTON_DYNAMIC, section, button);
		clickToElement(CheckoutPageUI.SECTION_BUTTON_DYNAMIC, section, button);
	}
}
