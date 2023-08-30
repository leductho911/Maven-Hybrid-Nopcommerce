package pageObjects;

import commons.BasePage;
import commons.GlobalConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.WishListPageUI;

import java.util.List;

public class WishListPageObj extends BasePage {
	private WebDriver driver;

	public WishListPageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Click to Share link at WishList Page")
	public void clickToShareLinkAtWishList() {
		waitForElementClickable(WishListPageUI.SHARE_LINK);
		clickToElement(WishListPageUI.SHARE_LINK);
	}

	@Step("Verify title of Wishlist Page")
	public String getWishListPageTitle() {
		waitForAllElementsVisible(WishListPageUI.PAGE_TITLE);
		return getElementText(WishListPageUI.PAGE_TITLE);
	}


	@Step("Check to 'Add to cart' checkbox of product: {0}")
	public void checkToAddToCartCheckbox(String productName) {
		waitForElementClickable(WishListPageUI.ADDTOCART_CHECKBOX_DYNAMIC, productName);
		checkToDefaultCheckboxOrRadio(WishListPageUI.ADDTOCART_CHECKBOX_DYNAMIC, productName);
	}

	@Step("Verify product {0} is displayed at Wishlist page")
	public boolean isProductDisplayedAtWishlistPage(String productName) {
		waitForAllElementsVisible(WishListPageUI.PRODUCT_NAME);
		List<WebElement> listWebElement = getListWebElement(WishListPageUI.PRODUCT_NAME);
		return listWebElement.stream()
				.anyMatch(element -> element.getText().toLowerCase().contains(productName.toLowerCase()));
	}

	@Step("Verify product '{0}' is not displayed at Wishlist page")
	public boolean isProductNotDisplayedAtWishlistPage(String productName) {
		overrideImplicitTimeout(GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> listWebElement = getListWebElement(WishListPageUI.PRODUCT_NAME);
		overrideImplicitTimeout(GlobalConstants.LONG_TIMEOUT);
		return listWebElement.stream()
				.noneMatch(element -> element.getText().toLowerCase().contains(productName.toLowerCase()));
	}

	@Step("Click to Remove button of Product {0}")
	public void clickToRemoveButtonByProductName(String productName) {
		clickToElement(WishListPageUI.REMOVE_BUTTON, productName);
	}
}
