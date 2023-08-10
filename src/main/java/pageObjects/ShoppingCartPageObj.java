package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.ShoppingCartPageUI;

import java.util.List;

public class ShoppingCartPageObj extends BasePage {
	private WebDriver driver;

	public ShoppingCartPageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}


	@Step("Verify product {0} is displayed at Shopping Cart page")
	public boolean isProductDisplayedAtShoppingCartPage(String productName) {
		List<WebElement> listWebElement = getListWebElement(ShoppingCartPageUI.PRODUCT_NAME);
		return listWebElement.stream()
				.anyMatch(element -> element.getText().toLowerCase().contains(productName.toLowerCase()));
	}


	@Step("Click to Edit button of product '{0}'")
	public ProductDetailPageObj clickToEditProductInShoppingCart(String productName) {
		waitForElementClickable(ShoppingCartPageUI.EDIT_BUTTON_BY_PRODUCTNAME, productName);
		clickToElement(ShoppingCartPageUI.EDIT_BUTTON_BY_PRODUCTNAME, productName);
		return PageGeneratorManager.getProductDetailPage(driver);
	}

	@Step("Verify product total: {0} ")
	public float getTotalPriceByProductName(String productName) {
		waitForElementVisible(ShoppingCartPageUI.PRODUCT_TOTAL, productName);
		return convertStringToFloat(getElementText(ShoppingCartPageUI.PRODUCT_TOTAL, productName));
	}

	@Step("Click to Remove button of product: {0} ")
	public void clickToRemoveProductButton(String productName) {
		waitForElementClickable(ShoppingCartPageUI.REMOVE_BUTTON, productName);
		clickToElement(ShoppingCartPageUI.REMOVE_BUTTON, productName);
	}

	@Step("Update quantity of product {0} to : {1}")
	public void inputQtyOfAProduct(String productName, int quantity) {
		waitForElementVisible(ShoppingCartPageUI.PRODUCT_QTY, productName);
		sendKeysToElement(ShoppingCartPageUI.PRODUCT_QTY, String.valueOf(quantity), productName);
	}

	@Step("Select 'Gift wrapping' dropdown: {0}")
	public void selectGiftWrapping(String value) {
		waitForElementVisible(ShoppingCartPageUI.GIFT_WRAPPING_DROPDOWN);
		selectItemInDefaultDropdown(ShoppingCartPageUI.GIFT_WRAPPING_DROPDOWN, value);
	}
}
