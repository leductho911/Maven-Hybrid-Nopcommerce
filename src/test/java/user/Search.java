package user;

import commonTest.RegisterAndLogin;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.Cookie;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HomePageObj;
import pageObjects.SearchPageObj;

import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Search extends BaseTest {
	private HomePageObj homePage;
	private SearchPageObj searchPage;
	private Set<Cookie> loginCookies;


	@BeforeClass
	public void innitBeforeClass() {
		loginCookies = RegisterAndLogin.loginCookies;
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.setCookies(loginCookies);
		homePage.refreshCurrentPage();
	}

	@Test
	public void Search_01_EmptyData() {
		homePage.clickToLinkAtFooter("Search");
		searchPage = PageGeneratorManager.getSearchPage(driver);
		searchPage.clickToSearchButtonAtSearchPage();
		// searchPage.clickOKButtonOnAlert();
		assertEquals(searchPage.getMessageAtSearchPage(), "Search term minimum length is 3 characters");

	}

	@Test
	public void Search_02_NotExistData() {
		searchPage.clickToLinkAtFooter("Search");
		searchPage.inputToTextboxByLabel("Search keyword", "Macbook Pro 2050");
		searchPage.clickToSearchButtonAtSearchPage();
		assertEquals(searchPage.getMessageAtSearchPage(), "No products were found that matched your criteria.");

	}

	@Test
	public void Search_03_Like_ProductName() {
		String searchKeyword = "Lenovo";
		searchPage.clickToLinkAtFooter("Search");
		searchPage.inputToTextboxByLabel("Search keyword", searchKeyword);
		searchPage.clickToSearchButtonAtSearchPage();
		assertEquals(searchPage.getSearchResultSize(), 2);
		assertTrue(searchPage.isSearchResultContainsTheSearchKeyword(searchKeyword));

	}

	@Test
	public void Search_04_Equal_ProductName() {
		String searchKeyword = "Thinkpad X1 Carbon";
		searchPage.clickToLinkAtFooter("Search");
		searchPage.inputToTextboxByLabel("Search keyword", searchKeyword);
		searchPage.clickToSearchButtonAtSearchPage();
		assertEquals(searchPage.getSearchResultSize(), 1);
		assertTrue(searchPage.isSearchResultContainsTheSearchKeyword(searchKeyword));

	}

	@Test
	public void Search_05_Advanced_ParentCategory() {
		searchPage.clickToLinkAtFooter("Search");
		searchPage.inputToTextboxByLabel("Search keyword", "Apple Macbook Pro");
		searchPage.checkToCheckboxByLabel("Advanced search");
		searchPage.selectItemInDropdownByLabel("Category", "Computers");
		searchPage.clickToSearchButtonAtSearchPage();
		assertEquals(searchPage.getMessageAtSearchPage(), "No products were found that matched your criteria.");
	}

	@Test
	public void Search_06_Advanced_SubCategory() {
		String searchKeyword = "Apple Macbook Pro";
		searchPage.clickToLinkAtFooter("Search");
		searchPage.inputToTextboxByLabel("Search keyword", searchKeyword);
		searchPage.checkToCheckboxByLabel("Advanced search");
		searchPage.selectItemInDropdownByLabel("Category", "Computers");
		searchPage.checkToCheckboxByLabel("Automatically search sub categories");
		searchPage.clickToSearchButtonAtSearchPage();
		assertEquals(searchPage.getSearchResultSize(), 1);
		assertTrue(searchPage.isSearchResultContainsTheSearchKeyword(searchKeyword));

	}

	@Test
	public void Search_07_Advanced_Incorrect_Manufacturer() {
		searchPage.clickToLinkAtFooter("Search");
		searchPage.inputToTextboxByLabel("Search keyword", "Apple Macbook Pro");
		searchPage.checkToCheckboxByLabel("Advanced search");
		searchPage.selectItemInDropdownByLabel("Category", "Computers");
		searchPage.checkToCheckboxByLabel("Automatically search sub categories");
		searchPage.selectItemInDropdownByLabel("Manufacturer", "HP");
		searchPage.clickToSearchButtonAtSearchPage();
		assertEquals(searchPage.getMessageAtSearchPage(), "No products were found that matched your criteria.");
	}

	@Test
	public void Search_08_Advanced_Correct_Manufacturer() {
		String searchKeyword = "Apple Macbook Pro";
		searchPage.clickToLinkAtFooter("Search");
		searchPage.inputToTextboxByLabel("Search keyword", searchKeyword);
		searchPage.checkToCheckboxByLabel("Advanced search");
		searchPage.selectItemInDropdownByLabel("Category", "Computers");
		searchPage.checkToCheckboxByLabel("Automatically search sub categories");
		searchPage.selectItemInDropdownByLabel("Manufacturer", "Apple");
		searchPage.clickToSearchButtonAtSearchPage();
		assertEquals(searchPage.getSearchResultSize(), 1);
		assertTrue(searchPage.isSearchResultContainsTheSearchKeyword(searchKeyword));


	}

}
