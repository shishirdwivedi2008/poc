package pageobject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Config;

public class AddToCart {

	Config config;

	@FindBy(xpath = "//div[@data-test-id='cart-icon']")
	private WebElement cartIcon;

	@FindBy(xpath = "")
	private WebElement cartItemName;

	public AddToCart(Config config) {
		this.config = config;
		PageFactory.initElements(config.driver, this);
	}

	/**
	 * Method to open Cart
	 * 
	 * @return
	 */
	public AddToCart clickToOpenCart() {
		config.log("Clicking to open cart");
		cartIcon.click();
		return new AddToCart(config);
	}

	public void verifyProduct(String [] productName) {
		List<String> name=new ArrayList<String>();
		List<WebElement> item_name = config.driver
				.findElements(By.xpath("//div[@class='cart-items']//div[@class='product-name']"));
		
		for(WebElement ele:item_name){
			name.add(ele.getText().trim());
		}
		
		for(int i=0;i<productName.length;i++){
			if(name.contains(productName[i])){
				config.log("Item matched in cart ");
				config.softAssert.assertTrue(true, "Iteam matched in cart");
			}else{
				config.log("Item not matched in cart");
				config.softAssert.assertTrue(false, "Item not matched");
			}
		}
	}
}
