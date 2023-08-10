package pageObjects;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.ShoppingCartPageUI;

import java.util.List;

public class DesktopsPageObj extends BasePage {
	private WebDriver driver;

	public DesktopsPageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}



}
