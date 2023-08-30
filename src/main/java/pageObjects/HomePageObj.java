package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class HomePageObj extends BasePage {
	private WebDriver driver;

	public HomePageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
}
