package pageUIs;

public class BasePageUI {

	public static final String NOTICICATION_BAR_MESSAGE = "css=#bar-notification .content";
	public static final String NOTICICATION_BAR_CLOSE_BUTTON = "css=#bar-notification span[title='Close']";
	public static final String NOTICICATION_BAR_LINK = "xpath=//div[@id='bar-notification']//p[@class='content']/a[text()='%s']";

	public static final String BUTTON = "xpath=//button[contains(string(),'%s')]";

	public static final String FOOTER_LINKS = "xpath=//div[@class='footer']//a[text()='%s']";
	public static final String HEADER_LINKS = "xpath=//div[@class='header-links']//a[contains(string(), '%s')]";
	public static final String LEFT_SIDEBAR_PAGE_LINKS = "xpath=//div[@class='listbox']//a[text()='%s']";

	public static final String LABEL_INPUT = "xpath=//label[text()='%s:']/following-sibling::input";
	public static final String LABEL_TEXTAREA = "xpath=//label[text()='%s:']/following-sibling::textarea";
	public static final String LABEL_CHECKBOX = "xpath=//label[text()='%s']/preceding-sibling::input[@type='checkbox']";
	public static final String LABEL_RADIO = "xpath=//label[text()='%s']/preceding-sibling::input[@type='radio']";

	public static final String LABEL_DROPDOWN = "xpath=//label[text()='%s:']/following-sibling::select";
	public static final String MENU_LINK_DYNAMIC = "xpath=//div[@class='header-menu']//a[text()='%s']";
	public static final String WEB_LOGO = "css=.header-logo a";

	public static final String PRODUCT_DETAIL_BY_PRODUCTNAME_DYNAMIC = "xpath=//h2[@class='product-title']/a[text()='%s']";

	public static final String PAGE_TITLE = "xpath=//div[@class='page-title']/h1[text()='%s']";
	public static final String TEXTVALUE_LINK_DYNAMIC = "xpath=//a[text()='%s']";

	public static final String PAGE_BODY = "css=.page-body";
	public static final String PRODUCT_NAME = "xpath=//h2[@class='product-title']/a";

	public static final String PRODUCT_BUTTONS_DYNAMIC = "xpath=//a[text()='%s']/parent::h2/following-sibling::div[@class='add-info']//button[text()='%s']";


	public static final String PLACEHOLDER_TEXTBOX_DYNAMIC = "xpath=//input[@placeholder='%s']";

	public static final String ORDER_NUMBER = "xpath=//strong[contains(text(),'Order Number: ') or contains(text(),'Order number: ')]";

}
