package user;

import commonTest.LoginSuccess;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.Cookie;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.CustomerInfoPageObj;
import pageObjects.HomePageObj;
import pageObjects.LoginPageObj;
import utils.DataFaker;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class MyAccount extends BaseTest {
	private Set<Cookie> loginCookies = LoginSuccess.loginCookies;
	public HomePageObj homePage;
	private CustomerInfoPageObj customerInfoPage;
	public LoginPageObj loginPage;
	private String firstName, lastName, day, month, year, email, companyName;

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

		homePage = PageGeneratorManager.getHomePage(driver);
		loginPage = homePage.clickToLoginLink();
		loginPage.setCookies(loginCookies);
		loginPage.refreshCurrentPage();
		loginPage.sleepInSecond(10);

	}

	@Test
	public void MyAccount_01_CustomerInfo(){
		customerInfoPage = homePage.clickToMyaccountLink();
		customerInfoPage.selectGenderByLabel("Female");
		customerInfoPage.inputToFirstnameTextbox(firstName);
		customerInfoPage.inputToLastnameTextbox(lastName);
		customerInfoPage.selectDayofbirth(day);
		customerInfoPage.selectMonthofbirth(month);
		customerInfoPage.selectYearofbirth(year);
		customerInfoPage.inputToEmailTextbox(email);
		customerInfoPage.inputToCompanynameTextbox(companyName);
		customerInfoPage.clickToSaveButton();
		assertEquals(customerInfoPage.getUpdatedSuccessMessage(), "The customer info has been updated successfully.");
	}

	@Test
	public void MyAccount_02_Addresses(){

	}

	@Test
	public void MyAccount_03_ChangePassword(){

	}

	@Test
	public void MyAccount_04_MyProductReviews(){

	}
}
