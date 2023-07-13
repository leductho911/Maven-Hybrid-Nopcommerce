package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class LoginPageObj extends BasePage {
	private WebDriver driver;

	public LoginPageObj(WebDriver driver) {
		this.driver = driver;
	}


}
