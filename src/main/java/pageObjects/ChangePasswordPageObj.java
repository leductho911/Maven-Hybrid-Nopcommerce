package pageObjects;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.ChangePasswordPageUI;

public class ChangePasswordPageObj extends BasePage {
	private WebDriver driver;

	public ChangePasswordPageObj(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
