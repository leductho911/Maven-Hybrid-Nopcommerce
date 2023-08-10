package pageObjects;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.ProductReviewPageUI;

public class ProductReviewPageObj extends BasePage {
	private WebDriver driver;

	public ProductReviewPageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Verify result message")
	public String getResultMessage() {
		waitForElementVisible(ProductReviewPageUI.RESULT_MESSAGE);
		return getElementText(ProductReviewPageUI.RESULT_MESSAGE);
	}
	@Step("Input to Review title : {0}")
	public void inputToReviewTitle(String title) {
		waitForElementVisible(ProductReviewPageUI.REVIEW_TITLE);
		sendKeysToElement(ProductReviewPageUI.REVIEW_TITLE, title);
	}

	@Step("Input to Review text : {0}")
	public void inputToReviewText(String text) {
		waitForElementVisible(ProductReviewPageUI.REVIEW_TEXT);
		sendKeysToElement(ProductReviewPageUI.REVIEW_TEXT, text);
	}


}
