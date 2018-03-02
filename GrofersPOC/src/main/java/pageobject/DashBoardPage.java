package pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Config;

public class DashBoardPage {

	Config config;
	@FindBy(xpath = "//div[@data-test-id='search-box']/input[1]")
	private WebElement searchBox;
	@FindBy(xpath = "//button[contains(@class,'search__btn')]")
	private WebElement btnSearch;

	public DashBoardPage(Config config) {
		this.config = config;
		PageFactory.initElements(config.driver, this);
	}
	

	/**
	 * This method will perform search operation 
	 * @param product name of product which you want to search
	 * @return {@link DashBoardPage}
	 * @author shishir
	 */
	public DashBoardPage enterProductToSeaerch(String product) {
		searchBox.sendKeys(product);
		config.log("Searching for Product:" + product);
		btnSearch.click();
		return new DashBoardPage(config);
	}
}
