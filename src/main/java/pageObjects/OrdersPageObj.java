package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class OrdersPageObj extends BasePage {
	private WebDriver driver;

	public OrdersPageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
