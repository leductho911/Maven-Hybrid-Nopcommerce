package user;

import commonTest.RegisterAndLogin;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.Cookie;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.*;

import java.util.Set;

import static org.testng.Assert.*;

public class Wishlist_Compare_RecentView extends BaseTest {
	private Set<Cookie> loginCookies;
	private HomePageObj homePage;
	private ProductDetailPageObj productDetailPage;
	private WishListPageObj wishListPage;
	private ShoppingCartPageObj shoppingCartPage;
	private CompareProductsPageObj compareProductsPage;
	private DesktopsPageObj desktopsPage;
	private NotebooksPageObj notebooksPage;
	private RecentlyViewedProductsPageObj recentlyViewedProductsPage;
	private String productName;


	@BeforeClass
	public void initBeforeClass() {
		loginCookies = RegisterAndLogin.loginCookies;
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.setCookies(loginCookies);
		homePage.refreshCurrentPage();
		productName = "HTC One M8 Android L 5.0 Lollipop";

	}

	@Test
	public void TC_01_Wishlist_AddToWishList() {
		productDetailPage = homePage.openProductDetailByProductName(productName);
		productDetailPage.clickToButton("Add to wishlist");
		assertEquals(productDetailPage.getMessageAppearAtNotificationBar(), "The product has been added to your wishlist");
		productDetailPage.clickToCloseButtonAtNotificationBar();
		productDetailPage.clickToLinkAtHeader("Wishlist");
		wishListPage = PageGeneratorManager.getWishlistPage(driver);
		assertTrue(wishListPage.isProductDisplayedAtWishlistPage(productName));
		wishListPage.clickToShareLinkAtWishList();
		assertEquals(wishListPage.getWishListPageTitle(), "Wishlist of " + RegisterAndLogin.firstName + " " + RegisterAndLogin.lastName);
	}

	@Test
	public void TC_02_Wishlist_AddToCart() {
		homePage = wishListPage.clickToLogo();
		homePage.clickToLinkAtHeader("Wishlist");
		wishListPage = PageGeneratorManager.getWishlistPage(driver);
		wishListPage.checkToAddToCartCheckbox(productName);
		wishListPage.clickToButton("Add to cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);
		assertTrue(shoppingCartPage.isProductDisplayedAtShoppingCartPage(productName));
		shoppingCartPage.clickToLinkAtHeader("Wishlist");
		wishListPage = PageGeneratorManager.getWishlistPage(driver);
		assertTrue(wishListPage.isProductNotDisplayedAtWishlistPage(productName));

	}


	@Test
	public void TC_03_Wishlist_RemoveProduct() {
		String productName = "Apple MacBook Pro 13-inch";
		homePage = wishListPage.clickToLogo();
		productDetailPage = homePage.openProductDetailByProductName(productName);
		productDetailPage.clickToButton("Add to wishlist");
		productDetailPage.clickToCloseButtonAtNotificationBar();
		productDetailPage.clickToLinkAtHeader("Wishlist");
		wishListPage = PageGeneratorManager.getWishlistPage(driver);
		wishListPage.clickToRemoveButtonByProductName(productName);
		assertTrue(wishListPage.getPageBody().contains("The wishlist is empty!"));
		assertTrue(wishListPage.isProductNotDisplayedAtWishlistPage(productName));
	}

	@Test
	public void TC_04_AddProductToCompare() {
		homePage = wishListPage.clickToLogo();
		homePage.clickToButtonOfAProduct("Build your own computer", "Add to compare list");
		assertEquals(homePage.getMessageAppearAtNotificationBar(), "The product has been added to your product comparison");
		homePage.clickToButtonOfAProduct("Apple MacBook Pro 13-inch", "Add to compare list");
		assertEquals(homePage.getMessageAppearAtNotificationBar(), "The product has been added to your product comparison");
		homePage.clickToLinkAtNotificationBar("product comparison");
		compareProductsPage = PageGeneratorManager.getCompareProductsPage(driver);
		assertTrue(compareProductsPage.isPageDisplayed("Compare products"));
		compareProductsPage.clickToLink("Clear list");
		assertTrue(compareProductsPage.getPageBody().contains("You have no items to compare."));
	}

	@Test
	public void TC_05_RecentlyViewedProducts() {
		compareProductsPage.hoverMouseToMenu("Computers ");
		compareProductsPage.clickToLinkAtMenu("Desktops ");
		desktopsPage = PageGeneratorManager.getDesktopsPage(driver);
		productDetailPage = desktopsPage.openProductDetailByProductName("Build your own computer");
		productDetailPage.clickToBrowserBackButton();
		desktopsPage = PageGeneratorManager.getDesktopsPage(driver);
		productDetailPage = desktopsPage.openProductDetailByProductName("Digital Storm VANQUISH 3 Custom Performance PC");
		productDetailPage.clickToBrowserBackButton();
		desktopsPage = PageGeneratorManager.getDesktopsPage(driver);
		productDetailPage = desktopsPage.openProductDetailByProductName("Lenovo IdeaCentre 600 All-in-One PC");
		productDetailPage.hoverMouseToMenu("Computers ");
		productDetailPage.clickToLinkAtMenu("Notebooks ");
		notebooksPage = PageGeneratorManager.getNotebooksPage(driver);
		productDetailPage = notebooksPage.openProductDetailByProductName("HP Spectre XT Pro UltraBook");
		productDetailPage.clickToBrowserBackButton();
		notebooksPage = PageGeneratorManager.getNotebooksPage(driver);
		productDetailPage = notebooksPage.openProductDetailByProductName("Samsung Series 9 NP900X4C Premium Ultrabook");
		productDetailPage.clickToLinkAtFooter("Recently viewed products");
		recentlyViewedProductsPage = PageGeneratorManager.getRecentlyViewedProductsPage(driver);
		assertEquals(recentlyViewedProductsPage.getProductList().size(), 3);
		assertTrue(recentlyViewedProductsPage.getProductList().contains("Lenovo IdeaCentre 600 All-in-One PC"));
		assertTrue(recentlyViewedProductsPage.getProductList().contains("HP Spectre XT Pro UltraBook"));
		assertTrue(recentlyViewedProductsPage.getProductList().contains("Samsung Series 9 NP900X4C Premium Ultrabook"));
		assertFalse(recentlyViewedProductsPage.getProductList().contains("Build your own computer"));
		assertFalse(recentlyViewedProductsPage.getProductList().contains("Digital Storm VANQUISH 3 Custom Performance PC"));
	}
}
