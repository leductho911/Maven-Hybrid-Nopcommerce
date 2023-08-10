package pageObjects;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.SearchPageUI;

import java.util.List;

public class SearchPageObj extends BasePage {
	private WebDriver driver;

	public SearchPageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Verify message at Search Page")
	public String getMessageAtSearchPage() {
		waitForElementVisible(SearchPageUI.SEARCH_MESSAGE);
		return getElementText(SearchPageUI.SEARCH_MESSAGE);
	}

	@Step("Click to Search button at Search Page")
	public void clickToSearchButtonAtSearchPage() {
		waitForElementClickable(SearchPageUI.SEARCH_BUTTON);
		clickToElement(SearchPageUI.SEARCH_BUTTON);
	}

	@Step("Verify Search result: verify number of search result")
	public int getSearchResultSize() {
		waitForElementVisible(SearchPageUI.RESULT_PRODUCTS);
		return getElementSize(SearchPageUI.RESULT_PRODUCTS);
	}


	@Step("Verify Search result: verify search result has keyword: {0}")
	public boolean isSearchResultContainsTheSearchKeyword(String searchKeyword) {
		List<WebElement> searchResults = getListWebElement(SearchPageUI.RESULT_PRODUCTNAME);
		return searchResults.stream()
				.anyMatch(result -> result.getText().toLowerCase().contains(searchKeyword.toLowerCase()));
	}

}
