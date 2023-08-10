package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;
import pageUIs.MyProductReviewsPageUI;

public class MyProductReviewsPageObj extends BasePage {
	private WebDriver driver;

	public MyProductReviewsPageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Verify Review title")
	public String getReviewTitle() {
		waitForElementVisible(MyProductReviewsPageUI.REVIEW_TITLE);
		return getElementText(MyProductReviewsPageUI.REVIEW_TITLE);
	}
	@Step("Verify Review text")
	public String getReviewText() {
		waitForElementVisible(MyProductReviewsPageUI.REVIEW_TEXT);
		return getElementText(MyProductReviewsPageUI.REVIEW_TEXT);
	}
	@Step("Verify Product name")
	public String getProductName() {
		waitForElementVisible(MyProductReviewsPageUI.REVIEW_PRODUCT_NAME);
		return getElementText(MyProductReviewsPageUI.REVIEW_PRODUCT_NAME);
	}
}
