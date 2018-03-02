package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Config {

	public WebDriver driver=null;
	
	public Config() {
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
