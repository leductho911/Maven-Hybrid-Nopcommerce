package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class ChangePasswordPageObj extends BasePage {
	private WebDriver driver;

	public ChangePasswordPageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
}
