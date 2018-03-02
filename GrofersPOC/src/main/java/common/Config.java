package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.asserts.SoftAssert;

public class Config {

	public WebDriver driver=null;
	public SoftAssert softAssert=null;
	public Config() {
		softAssert=new SoftAssert();
		System.setProperty("webdriver.gecko.driver", "/home/shishir/Downloads/geckodriver");
		driver=new FirefoxDriver();
		log("Navigating to Grofers ");
		driver.navigate().to("https://grofers.com/");
		driver.manage().window().maximize();
	}
	
	
	public void log(String comment){
		System.out.println(comment);
	}
}
