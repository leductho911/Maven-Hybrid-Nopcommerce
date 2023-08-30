package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class DesktopsPageObj extends BasePage {
	private WebDriver driver;

	public DesktopsPageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
}
