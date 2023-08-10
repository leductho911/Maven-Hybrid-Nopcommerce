package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class RecentlyViewedProductsPageObj extends BasePage {
	private WebDriver driver;

	public RecentlyViewedProductsPageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}


}
