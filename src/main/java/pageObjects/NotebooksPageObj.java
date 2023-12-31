package pageObjects;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.NotebooksPageUI;

public class NotebooksPageObj extends BasePage {
	private WebDriver driver;

	public NotebooksPageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Select Sort by: {0}")
	public void selectSortBy(String value) {
		waitForElementVisible(NotebooksPageUI.SORT_BY_DROPDOWN);
		selectItemInDefaultDropdown(NotebooksPageUI.SORT_BY_DROPDOWN, value);
	}

	@Step("Verify product name is sorted from A to Z")
	public boolean isProductSortByNameAsc() {
		waitForElementInvisible(NotebooksPageUI.PRODUCT_LOADING_ICON);
		waitForElementVisible(NotebooksPageUI.PRODUCT_TITLES);
		return isDataSortByNameAsc(NotebooksPageUI.PRODUCT_TITLES);
	}

	@Step("Verify product name is sorted from Z to A")
	public boolean isProductSortByNameDesc() {
		waitForElementInvisible(NotebooksPageUI.PRODUCT_LOADING_ICON);
		waitForElementVisible(NotebooksPageUI.PRODUCT_TITLES);
		return isDataSortByNameDesc(NotebooksPageUI.PRODUCT_TITLES);
	}

	@Step("Verify product price is sorted: Low to High")
	public boolean isProductSortByPriceAsc() {
		waitForElementInvisible(NotebooksPageUI.PRODUCT_LOADING_ICON);
		waitForElementVisible(NotebooksPageUI.PRODUCT_PRICES);
		return isDataFloatSortedAsc(NotebooksPageUI.PRODUCT_PRICES);
	}

	@Step("Verify product price is sorted: High to Low")
	public boolean isProductSortByPriceDesc() {
		waitForElementInvisible(NotebooksPageUI.PRODUCT_LOADING_ICON);
		waitForElementVisible(NotebooksPageUI.PRODUCT_PRICES);
		return isDataFloatSortedDesc(NotebooksPageUI.PRODUCT_PRICES);
	}

	@Step("Verify number product per page is equal or less than {0}")
	public boolean isMaxProductsOnPage(int maxProductsAllowed) {
		waitForElementInvisible(NotebooksPageUI.PRODUCT_LOADING_ICON);
		waitForElementVisible(NotebooksPageUI.PRODUCT_TITLES);
		return getElementSize(NotebooksPageUI.PRODUCT_TITLES) <= maxProductsAllowed;
	}

	@Step("Select display: {0}")
	public void selectDisplayPerPage(int number) {
		waitForElementVisible(NotebooksPageUI.DISPLAY_DROPDOWN);
		selectItemInDefaultDropdown(NotebooksPageUI.DISPLAY_DROPDOWN, String.valueOf(number));
	}

	@Step("Verify Next icon appears at Paging")
	public boolean isNextButtonAppearAtPaging() {
		waitForElementVisible(NotebooksPageUI.PAGING_NEXT);
		return isElementDisplayed(NotebooksPageUI.PAGING_NEXT);
	}

	@Step("Click to Next icon at Paging")
	public void clickToNextButtonAtPaging() {
		waitForElementClickable(NotebooksPageUI.PAGING_NEXT);
		clickToElement(NotebooksPageUI.PAGING_NEXT);
	}

	@Step("Verify Previous icon appears at Paging")
	public boolean isPreviousButtonAppearAtPaging() {
		waitForElementVisible(NotebooksPageUI.PAGING_PREVIOUS);
		return isElementDisplayed(NotebooksPageUI.PAGING_PREVIOUS);
	}

	@Step("Verify Paging is not displayed")
	public boolean isPagingNotDisplayed() {
		waitForElementNotDisplayed(NotebooksPageUI.PAGING);
		return isElementNotDisplayed(NotebooksPageUI.PAGING);
	}
}
