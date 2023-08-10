package pageUIs;

public class WishListPageUI {
	public static final String PRODUCT_NAME = "xpath=//div[@class='page wishlist-page']//td[@class='product']/a";
	public static final String SHARE_LINK = "xpath=//span[@class='share-label']/following-sibling::a";

	public static final String PAGE_TITLE = "css=.page-title";
	public static final String ADDTOCART_CHECKBOX_DYNAMIC = "xpath=//a[text()='%s']/parent::td/preceding-sibling::td[@class='add-to-cart']/input[@type='checkbox']";
	public static final String REMOVE_BUTTON = "xpath=//td[contains(string(),'%s')]/following-sibling::td[@class='remove-from-cart']/button";

}
