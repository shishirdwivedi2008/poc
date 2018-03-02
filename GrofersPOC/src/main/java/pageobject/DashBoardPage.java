package pageobject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Config;
import common.Executor;

public class DashBoardPage {

	Config config;
	@FindBy(xpath = "//div[@data-test-id='search-box']/input[1]")
	private WebElement searchBox;
	@FindBy(xpath = "//button[contains(@class,'search__btn')]")
	private WebElement btnSearch;
	@FindBy(xpath="//img[contains(@alt,'Tata Agni Tea')]")
	private WebElement product;
	@FindBy(xpath="(//h1[@title='Tata Agni Tea (Pouch)']//following::button[@data-test-id='add-button'])[1]")
	private WebElement addToCart;
	
	List<String>src=new ArrayList<String>();
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
		config.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		searchBox.sendKeys(Keys.ENTER);
		//btnSearch.click();
		config.log("Waiting for 20 Seconds");
		
		//Deliberately waiting to 20 second hence not using implcit wait.
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		return new DashBoardPage(config);
	}
	
	
	public void VerfiyThatSearchResult(String SearchProduct){
		List<String> title=new ArrayList<String>();
		
		List<WebElement> element=config.driver.findElements(By.xpath("//img"));
		for(WebElement ele:element){
			title.add(ele.getAttribute("alt"));
			if(ele.getAttribute("src").contains("http"))
				src.add(ele.getAttribute("src"));
			else{
				src.add("http://"+ele.getAttribute("src"));
			}
		}
		for(int i=0;i<title.size();i++){
			if(title.get(i).contains(SearchProduct)){
				config.log("Procut Matched with search key");
				config.softAssert.assertTrue(true, "Product matched with searched Product");
			}else{
				config.log("Product didn't match with searched key");
				config.softAssert.assertTrue(false, "Prodcut didn't matched");
			}
		}
	}
	
	/**
	 * Method to verify Whether Images are broken or not by hitting cdn url. 
	 * Creating 10 thread and executing all the links on it.
	 * @param list
	 * @author shishir
	 */
	public void verifyImageIsNotBroken(){
		ExecutorService executor=Executors.newFixedThreadPool(10);
		int loop=src.size()/10;
		int extra=src.size()%10;
		int i=0;
		int k=0;
		while (i<loop){
			for(int j=0;j<10;j++){
				executor.execute(new Executor(config, src.get(k)));
				k++;
			}
			i++;
		}
		for(int j=0;j<extra;j++){
			executor.execute(new Executor(config, src.get(k)));
			k++;
		}
		
		executor.shutdown();
	}
	
	/**
	 * Method will select first Product
	 * @return {@link DashBoardPage}
	 * @author shishir
	 */
	public DashBoardPage selectOneProduct(){
		product.click();
		try {
			config.log("Sleeping for 10 second to load data ");
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new DashBoardPage(config);
	}
	
	/**
	 * Method to add to cart
	 * @return
	 */
	public AddToCart addToCart(){
		config.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		addToCart.click();
		config.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		config.log("pressing ESC to close cart div");
		config.driver.findElement(By.xpath("//div[@class='pdp']")).sendKeys(Keys.ESCAPE);
		return new AddToCart(config);
	}
}
