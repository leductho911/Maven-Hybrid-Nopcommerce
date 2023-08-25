package user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HomePageObj;
import pageObjects.NotebooksPageObj;

import static org.testng.Assert.assertTrue;

public class Notebooks extends BaseTest {
	private HomePageObj homePage;
	private NotebooksPageObj notebooksPage;


	@BeforeClass
	public void innitBeforeClass() {
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.hoverMouseToMenu("Computers ");
		homePage.clickToLinkAtMenu("Notebooks ");
		notebooksPage = PageGeneratorManager.getNotebooksPage(driver);
	}

	@Test
	public void Notebooks_01_SortWithName_AtoZ() {
		notebooksPage.selectSortBy("Name: A to Z");
		assertTrue(notebooksPage.isProductSortByNameAsc());
	}

	@Test
	public void Notebooks_02_SortWithName_ZtoA() {
		notebooksPage.selectSortBy("Name: Z to A");
		assertTrue(notebooksPage.isProductSortByNameDesc());
	}

	@Test
	public void Notebooks_03_SortWithPrice_LowtoHigh() {
		notebooksPage.selectSortBy("Price: Low to High");
		assertTrue(notebooksPage.isProductSortByPriceAsc());
	}

	@Test
	public void Notebooks_04_SortWithPrice_HightoLow() {
		notebooksPage.selectSortBy("Price: High to Low");
		assertTrue(notebooksPage.isProductSortByPriceDesc());
	}

	@Test
	public void Notebooks_05_SortDisplayed_3PerPage() {
		notebooksPage.selectDisplayPerPage(3);
		assertTrue(notebooksPage.isMaxProductsOnPage(3));
		assertTrue(notebooksPage.isNextButtonAppearAtPaging());
		notebooksPage.clickToNextButtonAtPaging();
		assertTrue(notebooksPage.isPreviousButtonAppearAtPaging());
	}

	@Test
	public void Notebooks_06_SortDisplayed_6PerPage() {
		notebooksPage.selectDisplayPerPage(6);
		assertTrue(notebooksPage.isMaxProductsOnPage(6));
		assertTrue(notebooksPage.isPagingNotDisplayed());
	}

	@Test
	public void Notebooks_07_SortDisplayed_9PerPage() {
		notebooksPage.selectDisplayPerPage(9);
		assertTrue(notebooksPage.isMaxProductsOnPage(9));
		assertTrue(notebooksPage.isPagingNotDisplayed());
	}


}
