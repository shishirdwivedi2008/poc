package test;

import common.Config;
import pageobject.LoginPage;

public class Test extends TestBase {
	@org.testng.annotations.Test(dataProvider="GetTestConfig")
	public void test(Config config){
		new LoginPage(config).selectCity().clickToLogin().enterPhoneAndOTP().clickOTPNext();
	}
}
