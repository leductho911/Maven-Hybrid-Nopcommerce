package pageUIs;

public class ShoppingCartPageUI {

	public static final String PRODUCT_NAME = "xpath=//td[@class='product']/a";
	public static final String EDIT_BUTTON_BY_PRODUCTNAME = "xpath=//a[text()='%s']/following-sibling::div[@class='edit-item']/a";
	public static final String PRODUCT_TOTAL = "xpath=//a[text()='%s']/parent::td/following-sibling::td[@class='subtotal']/span[@class='product-subtotal']";
	public static final String REMOVE_BUTTON = "xpath=//a[text()='%s']/parent::td/following-sibling::td[@class='remove-from-cart']//button[@class='remove-btn']";
	public static final String PRODUCT_QTY = "xpath=//a[text()='%s']/parent::td/following-sibling::td[@class='quantity']//input[@class='qty-input']";
	public static final String GIFT_WRAPPING_DROPDOWN = "xpath=//select[@id='checkout_attribute_1']";
}
