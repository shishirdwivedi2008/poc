package pageobject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Config;

public class LoginPage {

	Config  config=null;
	
	@FindBy(xpath="//div[@class='location__selected']")
	private WebElement selectCity;
	@FindBy(xpath="//div[@data-test-id='my-account-header']")
	private WebElement btnClickLogin;
	@FindBy(xpath="//input[@data-test-id='phone-no-text-box']")
	private WebElement txtPhoneNumber;
	@FindBy(xpath="//button[@data-test-id='otp-next-button']")
	private WebElement otpnxt;
	public LoginPage(Config config) {
		this.config=config;
		config.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		PageFactory.initElements(this.config.driver, this);
	}
	/**
	 * Method to select City
	 * @return {@link LoginPage}
	 * @author shishir
	 */
	public LoginPage selectCity(){
		selectCity.click();
		config.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		config.log("City Selected");
		config.driver.manage().window().maximize();
		config.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return new LoginPage(config);
	}
	
	/**
	 * Method to click login link
	 * @return {@link LoginPage}
	 * @author shishir
	 */
	
	public LoginPage clickToLogin(){
		btnClickLogin.click();
		config.log("Login Button clicked");
		try{
		config.driver.findElement(By.xpath("//button[@data-test-id='login-or-sign-up']")).click();
		}catch (Exception e) {
			btnClickLogin.click();
			config.driver.findElement(By.xpath("//button[@data-test-id='login-or-sign-up']")).click();
		}
		config.log("Login and Signup button Clicked");
		config.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		return new LoginPage(config);
	}
	/**
	 * Method to enter Phone number and OTP
	 * @return {@link LoginPage}
	 * @author shishir
	 */
	public LoginPage enterPhoneAndOTP(){
		config.log("Entering Phone number");
		txtPhoneNumber.sendKeys("9205789599");
		config.log("Clicking nxt for OTP");
		WebElement nxt=config.driver.findElement(By.xpath("//button[@data-test-id='login-next-button']"));
		nxt.click();
		config.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		String [] otp={"4","1","7","2"};
		int i=0;
		List<WebElement> element=config.driver.findElements(By.xpath("//input[@data-test-id='otp-text-box']"));
		for(WebElement ele:element){
			ele.sendKeys(otp[i]);
			i++;
		}
		return new LoginPage(config);
				
	}
	/**
	 * Method to click next after entering OTP
	 * @return {@link DashBoardPage}
	 * @author shishir
	 */
	public DashBoardPage clickOTPNext(){
		otpnxt.click();
		config.log("Waiting for 5 second for login Successful frame to disappear");
		config.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return new DashBoardPage(config);
	}
	
}
