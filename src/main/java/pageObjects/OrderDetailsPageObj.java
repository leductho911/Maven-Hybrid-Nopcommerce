package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class OrderDetailsPageObj extends BasePage {
	private WebDriver driver;

	public OrderDetailsPageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
