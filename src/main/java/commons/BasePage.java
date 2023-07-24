package commons;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.CustomerInfoPageObj;
import pageObjects.LoginPageObj;
import pageObjects.RegisterPageObj;
import pageUIs.BasePageUI;
import pageUIs.HomePageUI;


public class BasePage {

	private WebDriver driver;

	private final long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private final long shortTimeout = GlobalConstants.SHORT_TIMEOUT;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}


	public void openPageUrl(String pageUrl) {
		driver.get(pageUrl);
	}


	public Set<Cookie> getAllCookies() {
		return this.driver.manage().getCookies();
	}

	public void setCookies(Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(1);
	}

	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
		sleepInSecond(1);
	}


	protected String getPageTitle() {
		return driver.getTitle();
	}

	protected String getPageUrl() {
		return driver.getCurrentUrl();
	}

	protected String getPageSourceCode() {
		return driver.getPageSource();
	}

	protected void backToPage() {
		driver.navigate().back();
	}

	protected void forwardToPage() {
		driver.navigate().forward();
	}

	public void refreshCurrentPage() {
		driver.navigate().refresh();
	}

	protected Alert waitAlertPresence() {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	protected void acceptAlert() {
		waitAlertPresence().accept();
	}

	protected void cancelAlert() {
		waitAlertPresence().dismiss();
	}

	protected void getTextAlert() {
		waitAlertPresence().getText();
	}

	protected void sendkeyToAlert(String textValue) {
		waitAlertPresence().sendKeys(textValue);
	}

	protected void switchToWindowByID(String pageID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			if (!id.equals(pageID)) {

				driver.switchTo().window(id);
			}
		}
	}

	protected void switchToWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);

			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	protected void closeAllTabWithoutParent(String currentWindowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(currentWindowID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(currentWindowID);
		}
	}

	private By getByLocator(String locatorType) {
		By by = null;

		if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
			by = By.id(locatorType.substring(3));

		} else if (locatorType.startsWith("class=") || locatorType.startsWith("CLASS=")
				|| locatorType.startsWith("Class=")) {
			by = By.className(locatorType.substring(6));

		} else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=")
				|| locatorType.startsWith("Name=")) {
			by = By.name(locatorType.substring(5));

		} else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
			by = By.cssSelector(locatorType.substring(4));

		} else if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=")
				|| locatorType.startsWith("Xpath=")) {
			by = By.xpath(locatorType.substring(6));

		} else {
			throw new RuntimeException("Locator type is not correct");
		}
		return by;
	}

	public String getDynamicXpath(String locatorType, String... dynamicValues) {
		if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=")) {
			locatorType = String.format(locatorType, (Object[]) dynamicValues);
		}
		return locatorType;
	}

	public WebElement getWebElement(String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	public List<WebElement> getListWebElement(String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

	public List<WebElement> getListWebElement(String locatorType, String... dynamicValues) {
		return driver.findElements(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
	}

	protected void clickToElement(String locatorType) {
		getWebElement(locatorType).click();
	}

	protected void clickToElement(String locatorType, String... dynamicValues) {
		getWebElement(getDynamicXpath(locatorType, dynamicValues)).click();
	}

	protected void sendKeysToElement(String locatorType, String textValue) {
		WebElement element = getWebElement(locatorType);
		element.clear();
		element.sendKeys(textValue);
	}

	protected void sendKeysToElement(String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}

	protected void selectItemInDefaultDropdown(String locatorType, String visibleText) {
		Select select = new Select(getWebElement(locatorType));
		select.selectByVisibleText(visibleText);
	}

	protected void selectItemInDefaultDropdown(String locatorType, String visibleText,
	                                           String... dynamicValues) {
		Select select = new Select(getWebElement(getDynamicXpath(locatorType, dynamicValues)));
		select.selectByVisibleText(visibleText);
	}

	protected String getSelectedItemDefaultDropdown(String locatorType) {
		Select select = new Select(getWebElement(locatorType));
		return select.getFirstSelectedOption().getText();
	}

	protected String getSelectedItemDefaultDropdown(String locatorType, String... dynamicValues) {
		Select select = new Select(getWebElement(getDynamicXpath(locatorType, dynamicValues)));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(String locatorType) {
		Select select = new Select(getWebElement(locatorType));
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(String parentXpath, String allItemsXPath,
	                                       String expectedTextItem) {
		getWebElement(parentXpath).click();
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));

		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(allItemsXPath)));

		for (WebElement tempItem : allItems) {
			if (tempItem.getText().equals(expectedTextItem)) {
				tempItem.click();
				break;
			}
		}
	}

	public String getElementAttribute(String attributeName, String locatorType) {
		return getWebElement(locatorType).getAttribute(attributeName);
	}

	public String getElementAttribute(String attributeName, String locatorType,
	                                  String... dynamicValues) {
		return getWebElement(getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
	}

	public String getElementText(String locatorType) {
		return getWebElement(locatorType).getText();
	}

	public String getElementText(String locatorType, String... dynamicValues) {
		return getWebElement(getDynamicXpath(locatorType, dynamicValues)).getText();
	}

	public String getElementCssValue(String locatorType, String propertyName) {
		return getWebElement(locatorType).getCssValue(propertyName);
	}

	public String getElementCssValue(String locatorType, String propertyName,
	                                 String... dynamicValues) {
		return getWebElement(getDynamicXpath(locatorType, dynamicValues)).getCssValue(propertyName);
	}

	protected String getHexaColorByRgbaColor(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public int getElementSize(String locatorType) {
		return getListWebElement(locatorType).size();
	}

	public int getElementSize(String locatorType, String... dynamicValues) {
		return getListWebElement(getDynamicXpath(locatorType, dynamicValues)).size();
	}

	protected void checkToDefaultCheckboxOrRadio(String locatorType) {
		WebElement element = getWebElement(locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void checkToDefaultCheckboxOrRadio(String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(getDynamicXpath(locatorType, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToDefaultCheckbox(String locatorType) {
		WebElement element = getWebElement(locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToDefaultCheckbox(String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(getDynamicXpath(locatorType, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}

	protected void pressKeyToElement(String locatorType, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(getDynamicXpath(locatorType, dynamicValues)), key).perform();
	}

	protected void scrollToBottomPage() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void highlightElement(String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	protected void highlightElement(String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(getDynamicXpath(locatorType, dynamicValues));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	protected void clickToElementByJS(String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(locatorType));
	}

	protected void clickToElementByJS(String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();",
				getWebElement(getDynamicXpath(locatorType, dynamicValues)));
	}

	protected void scrollToElement(String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(locatorType));
	}

	protected void removeAttributeInDOM( String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getWebElement(locatorType));
	}


	protected String getElementValidationMessage(String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
				getWebElement(locatorType));
	}

	protected boolean isImageLoaded(String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(locatorType));
	}



	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



	protected void waitForElementVisible(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}

	protected void waitForElementVisible(String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		explicitWait.until(ExpectedConditions
				.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	protected void waitForAllElementsVisible(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}

	protected void waitForAllElementsVisible(String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		explicitWait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}


	public void overrideImplicitTimeout(long timeOut) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
	}

	/*
	 * Wait for element undisplayed in DOM or not in DOM and override implicit timeout
	 */
	public void waitForElementUndisplayed(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		overrideImplicitTimeout(shortTimeout);
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideImplicitTimeout(longTimeout);
	}

	public void waitForElementInvisible(String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}


	public void waitForAllElementsInvisible(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(locatorType)));
	}

	public void waitForAllElementsInvisible(String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		explicitWait.until(ExpectedConditions
				.invisibilityOfAllElements(getListWebElement(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitForElementClickable(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}

	public void waitForElementClickable(String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		explicitWait.until(
				ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitForAlertPresence(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout), Duration.ofMillis(500));
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public boolean isElementDisplayed(String locatorType) {
		return getWebElement(locatorType).isDisplayed();
	}



	public boolean isDataSortedAsc(String locator) {
		List<WebElement> elementLists = driver.findElements(By.xpath(locator));
		List<String> names = elementLists.stream().map(WebElement::getText).collect(Collectors.toList());
		List<String> sortedNames = new ArrayList<>(names);
		Collections.sort(sortedNames);

		return names.equals(sortedNames);
	}

	@Step("Click to Register Link")
	public RegisterPageObj clickToRegisterLink() {
		waitForElementClickable(BasePageUI.REGISTER_LINK);
		clickToElement(BasePageUI.REGISTER_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
	}



	@Step("Click to Login Link")
	public LoginPageObj clickToLoginLink() {
		waitForElementClickable(BasePageUI.LOGIN_LINK);
		clickToElement(BasePageUI.LOGIN_LINK);
		return PageGeneratorManager.getLoginPage(driver);
	}
	@Step("Verify Homepage is displayed")
	public boolean isHomepageDisplayed() {
		waitForElementVisible(HomePageUI.WELCOME_TEXT);
		return isElementDisplayed(HomePageUI.WELCOME_TEXT);
	}

	public CustomerInfoPageObj clickToMyaccountLink() {
		waitForElementClickable(BasePageUI.MY_ACCOUNT_LINK);
		clickToElement(BasePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getCustomerInfoPage(driver);
	}
}

