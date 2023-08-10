package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class CompareProductsPageObj extends BasePage {
	private WebDriver driver;

	public CompareProductsPageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}


}
