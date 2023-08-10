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

public class MyAccount extends BaseTest {
	private Set<Cookie> loginCookies;
	private HomePageObj homePage;
	private AddressesPageObj addressesPage;
	private ChangePasswordPageObj changePasswordPage;
	private CustomerInfoPageObj customerInfoPage;
	private LoginPageObj loginPage;
	private ProductDetailPageObj productDetailPage;
	private ProductReviewPageObj productReviewPage;
	private MyProductReviewsPageObj myProductReviews;
	private String newPassword, firstName, lastName, day, month, year, email, companyName, city, address1, address2, zip, phone, fax, gender, country;

	@BeforeClass
	public void initBeforeClass(){
		DataFaker dataFaker = DataFaker.getDataFaker();
		firstName = dataFaker.getFirstName();
		lastName = dataFaker.getLastName();
		day  = "10";
		month = "August";
		year = "1990";
		email = dataFaker.getEmail();
		companyName = dataFaker.getCompanyName();
		loginCookies = RegisterAndLogin.loginCookies;
		city = dataFaker.getCity();
		address1 = dataFaker.getAddress();
		address2 = dataFaker.getAddress();
		zip = dataFaker.getZip();
		phone = dataFaker.getPhone();
		fax = dataFaker.getPhone();
		gender = dataFaker.getGender();
		country = dataFaker.getCountry();
		newPassword = dataFaker.getPassword();

		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.setCookies(loginCookies);
		homePage.refreshCurrentPage();

	}

	@Test
	public void MyAccount_01_CustomerInfo(){

		homePage.clickToLinkAtHeader("My account");
		customerInfoPage = PageGeneratorManager.getCustomerInfoPage(driver);
		customerInfoPage.selectGenderByLabel(gender);
		customerInfoPage.inputToTextboxByLabel("First name", firstName);
		customerInfoPage.inputToTextboxByLabel("Last name", lastName);
		customerInfoPage.selectDayofbirth(day);
		customerInfoPage.selectMonthofbirth(month);
		customerInfoPage.selectYearofbirth(year);
		customerInfoPage.inputToTextboxByLabel("Email", email);
		customerInfoPage.inputToTextboxByLabel("Company name", companyName);
		customerInfoPage.clickToButton("Save");

		assertEquals(customerInfoPage.getMessageAppearAtNotificationBar(), "The customer info has been updated successfully.");
		assertTrue(customerInfoPage.isGenderSelected(gender));
		assertEquals(customerInfoPage.getFirstnameTextboxValue(), firstName);
		assertEquals(customerInfoPage.getLastnameTextboxValue(), lastName);
		assertEquals(customerInfoPage.getDayOfBirth(), day);
		assertEquals(customerInfoPage.getMonthOfBirth(), month);
		assertEquals(customerInfoPage.getYearOfBirth(), year);
		assertEquals(customerInfoPage.getEmail(), email);
		assertEquals(customerInfoPage.getCompanyName(), companyName);

	}

	@Test
	public void MyAccount_02_Addresses(){
		customerInfoPage.openPageByPageName("Addresses");
		addressesPage = PageGeneratorManager.getAddressesPage(driver);
		addressesPage.clickToButton("Add new");
		addressesPage.inputToTextboxByLabel("First name", firstName);
		addressesPage.inputToTextboxByLabel("Last name", lastName);
		addressesPage.inputToTextboxByLabel("Email", email);
		addressesPage.inputToTextboxByLabel("Company", companyName);
		addressesPage.selectItemInDropdownByLabel("Country", country);
		addressesPage.inputToTextboxByLabel("City", city);
		addressesPage.inputToTextboxByLabel("Address 1", address1);
		addressesPage.inputToTextboxByLabel("Address 2", address2);
		addressesPage.inputToTextboxByLabel("Zip / postal code", zip);
		addressesPage.inputToTextboxByLabel("Phone number", phone);
		addressesPage.inputToTextboxByLabel("Fax number", fax);
		addressesPage.clickToButton("Save");

		assertEquals(addressesPage.getMessageAppearAtNotificationBar(), "The new address has been added successfully.");
		assertEquals(addressesPage.getAddressName(), firstName + " " + lastName);
		assertTrue(addressesPage.getAddressEmail().contains(email));
		assertTrue(addressesPage.getAddressPhone().contains(phone));
		assertTrue(addressesPage.getAddressFax().contains(fax));
		assertEquals(addressesPage.getAddressCompany(), companyName);
		assertEquals(addressesPage.getAddress1(), address1);
		assertEquals(addressesPage.getAddress2(), address2);
		assertTrue(addressesPage.getAddressCityStateZip().contains(city));
		assertTrue(addressesPage.getAddressCityStateZip().contains(zip));
		assertEquals(addressesPage.getAddressCountry(), country);
	}

	@Test
	public void MyAccount_03_ChangePassword(){
		customerInfoPage.openPageByPageName("Change password");
		changePasswordPage = PageGeneratorManager.getChangePasswordPage(driver);

		changePasswordPage.inputToTextboxByLabel("Old password", RegisterAndLogin.password);
		changePasswordPage.inputToTextboxByLabel("New password", newPassword);
		changePasswordPage.inputToTextboxByLabel("Confirm password", newPassword);
		changePasswordPage.clickToButton("Change password");

		assertEquals(changePasswordPage.getMessageAppearAtNotificationBar(), "Password was changed");

		changePasswordPage.clickToCloseButtonAtNotificationBar();
		changePasswordPage.clickToLinkAtHeader("Log out");
		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToLinkAtHeader("Log in");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.inputToTextboxByLabel("Email", email);
		loginPage.inputToTextboxByLabel("Password", RegisterAndLogin.password);
		loginPage.clickToButton("Log in");

		assertEquals(loginPage.getErrorMessageAtLoginPage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");

		loginPage.inputToTextboxByLabel("Password", newPassword);
		loginPage.clickToButton("Log in");
		homePage = PageGeneratorManager.getHomePage(driver);

		assertTrue(homePage.isHomepageDisplayed());

	}

	@Test
	public void MyAccount_04_MyProductReviews(){
		String productName = "Build your own computer";
		productDetailPage = homePage.openProductDetailByProductName(productName);
		productReviewPage = productDetailPage.clickToProductReviewLink();
		productReviewPage.inputToTextboxByLabel("Review title","This is a review title");
		productReviewPage.inputToTextareaByLabel("Review text","This is a review text");
		productReviewPage.clickToButton("Submit review");

		assertTrue(productReviewPage.getResultMessage().contains("Product review is successfully added."));

		productReviewPage.clickToLinkAtHeader("My account");
		customerInfoPage = PageGeneratorManager.getCustomerInfoPage(driver);
		customerInfoPage.openPageByPageName("My product reviews");
		myProductReviews = PageGeneratorManager.getMyProductReviewsPage(driver);

		assertEquals(myProductReviews.getReviewTitle(), "This is a review title");
		assertEquals(myProductReviews.getReviewText(), "This is a review text");
		assertEquals(myProductReviews.getProductName(), "Build your own computer");
	}
}
