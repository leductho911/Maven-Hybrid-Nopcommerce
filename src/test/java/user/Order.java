package user;

import commonTest.RegisterAndLogin;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.Cookie;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.*;
import utils.DataFaker;

import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Order extends BaseTest {
	private Set<Cookie> loginCookies;
	private HomePageObj homePage;
	private ProductDetailPageObj productDetailPage;
	private DesktopsPageObj desktopsPage;
	private ShoppingCartPageObj shoppingCartPage;
	private SearchPageObj searchPage;
	private CheckoutPageObj checkoutPage;
	private CustomerInfoPageObj customerInfoPage;
	private OrdersPageObj ordersPage;
	private String productName;
	private DataFaker dataFaker;
	private String firstName, lastName, email, country, city, address1, zipcode, phoneNumber;

	@BeforeClass
	public void initBeforeClass() {
		loginCookies = RegisterAndLogin.loginCookies;
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.setCookies(loginCookies);
		homePage.refreshCurrentPage();
		productDetailPage = homePage.openProductDetailByProductName("HTC One M8 Android L 5.0 Lollipop");
		productName = "Build your own computer";
		dataFaker = DataFaker.getDataFaker();
		firstName = dataFaker.getFirstName();
		lastName = dataFaker.getLastName();
		email = dataFaker.getEmail();
		country = dataFaker.getCountry();
		city = dataFaker.getCity();
		address1 = dataFaker.getAddress();
		zipcode = dataFaker.getZip();
		phoneNumber = dataFaker.getPhone();
	}

	@Test
	public void TC_01_AddProductToCart() {
		productDetailPage.hoverMouseToMenu("Computers ");
		productDetailPage.clickToLinkAtMenu("Desktops ");
		desktopsPage = PageGeneratorManager.getDesktopsPage(driver);
		productDetailPage = desktopsPage.openProductDetailByProductName(productName);
		productDetailPage.selectProcessor("2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]");
		productDetailPage.selectRam("8GB [+$60.00]");
		productDetailPage.checkToRadioButtonByLabel("400 GB [+$100.00]");
		productDetailPage.checkToRadioButtonByLabel("Vista Premium [+$60.00]");
		productDetailPage.checkToCheckboxByLabel("Microsoft Office [+$50.00]");
		productDetailPage.checkToCheckboxByLabel("Acrobat Reader [+$10.00]");
		productDetailPage.checkToCheckboxByLabel("Total Commander [+$5.00]");
		productDetailPage.clickToButton("Add to cart");
		assertEquals(productDetailPage.getMessageAppearAtNotificationBar(), "The product has been added to your shopping cart");
		productDetailPage.clickToCloseButtonAtNotificationBar();
		productDetailPage.clickToLinkAtHeader("Shopping cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);
		assertTrue(shoppingCartPage.isProductDisplayedAtShoppingCartPage(productName));
	}

	@Test
	public void TC_02_EditProductInShoppingCart() {
		productDetailPage = shoppingCartPage.clickToEditProductInShoppingCart(productName);
		productDetailPage.selectProcessor("2.2 GHz Intel Pentium Dual-Core E2200");
		productDetailPage.selectRam("4GB [+$20.00]");
		productDetailPage.checkToRadioButtonByLabel("320 GB");
		productDetailPage.checkToRadioButtonByLabel("Vista Home [+$50.00]");
		productDetailPage.checkToCheckboxByLabel("Microsoft Office [+$50.00]");
		productDetailPage.uncheckToCheckboxByLabel("Acrobat Reader [+$10.00]");
		productDetailPage.uncheckToCheckboxByLabel("Total Commander [+$5.00]");
		productDetailPage.inputToTextboxByLabel("Qty", "2");
		assertEquals(productDetailPage.getProductPrice(), 1320.00);
		productDetailPage.clickToButton("Update");
		assertEquals(productDetailPage.getMessageAppearAtNotificationBar(), "The product has been added to your shopping cart");
		productDetailPage.clickToCloseButtonAtNotificationBar();
		productDetailPage.clickToLinkAtHeader("Shopping cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);
		assertEquals(shoppingCartPage.getTotalPriceByProductName(productName), 2640.00);
	}

	@Test
	public void TC_03_RemoveProductFromCart() {
		shoppingCartPage.clickToRemoveProductButton(productName);
		assertTrue(shoppingCartPage.getPageBody().contains("Your Shopping Cart is empty!"));
	}

	@Test
	public void TC_04_UpdateShoppingCart() {
		String productName = "Lenovo IdeaCentre 600 All-in-One PC";
		shoppingCartPage.inputToTextboxByPlaceholder("Search store", productName);
		shoppingCartPage.clickToButton("Search");
		searchPage = PageGeneratorManager.getSearchPage(driver);
		searchPage.clickToButtonOfAProduct(productName, "Add to cart");
		searchPage.clickToLinkAtNotificationBar("shopping cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);
		shoppingCartPage.inputQtyOfAProduct(productName, 5);
		shoppingCartPage.clickToButton("Update shopping cart");
		assertEquals(shoppingCartPage.getTotalPriceByProductName(productName), 2500.00);
		shoppingCartPage.clickToRemoveProductButton(productName);
	}

	@Test
	public void TC_05_Checkout_Order_PaymentCheque() {
		String productName = "Apple MacBook Pro 13-inch";
		shoppingCartPage.inputToTextboxByPlaceholder("Search store", productName);
		shoppingCartPage.clickToButton("Search");
		searchPage = PageGeneratorManager.getSearchPage(driver);
		searchPage.clickToButtonOfAProduct(productName, "Add to cart");
		productDetailPage = PageGeneratorManager.getProductDetailPage(driver);
		productDetailPage.clickToButton("Add to cart");
		productDetailPage.clickToLinkAtNotificationBar("shopping cart");
		shoppingCartPage = PageGeneratorManager.getShoppingCartPage(driver);
		shoppingCartPage.selectGiftWrapping("No");
		shoppingCartPage.checkToCheckboxByLabel("I agree with the terms of service and I adhere to them unconditionally");
		shoppingCartPage.clickToButton("Checkout");
		checkoutPage = PageGeneratorManager.getCheckoutPage(driver);
		checkoutPage.checkToCheckboxByLabel("Ship to the same address");
		checkoutPage.inputToTextboxByLabel("First name", firstName);
		checkoutPage.inputToTextboxByLabel("Last name", lastName);
		checkoutPage.inputToTextboxByLabel("Email", email);
		checkoutPage.selectItemInDropdownByLabel("Country", country);
		checkoutPage.inputToTextboxByLabel("City", city);
		checkoutPage.inputToTextboxByLabel("Address 1", address1);
		checkoutPage.inputToTextboxByLabel("Zip / postal code", zipcode);
		checkoutPage.inputToTextboxByLabel("Phone number", phoneNumber);
		checkoutPage.clickToButton("Continue");
		checkoutPage.checkToRadioButtonByLabel("Ground ($0.00)");
		checkoutPage.clickToButtonBySection("Shipping method", "Continue");
		checkoutPage.checkToRadioButtonByLabel("Check / Money Order");
		checkoutPage.clickToButtonBySection("Payment method", "Continue");
		checkoutPage.clickToButtonBySection("Payment information", "Continue");
		checkoutPage.clickToButton("Confirm");
		assertTrue(checkoutPage.isPageDisplayed("Thank you"));
		assertTrue(checkoutPage.getPageBody().contains("Your order has been successfully processed!"));
		int orderNumber = checkoutPage.getOrderNumber();
		checkoutPage.clickToLinkAtHeader("My account");
		customerInfoPage = PageGeneratorManager.getCustomerInfoPage(driver);
		customerInfoPage.openPageByPageName("Orders");
		ordersPage = PageGeneratorManager.getOrdersPage(driver);
		assertEquals(ordersPage.getOrderNumber(), orderNumber);
		
	}


}
