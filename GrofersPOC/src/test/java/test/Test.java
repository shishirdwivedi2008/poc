package test;

import common.Config;
import pageobject.DashBoardPage;
import pageobject.LoginPage;

public class Test extends TestBase {
	@org.testng.annotations.Test(dataProvider = "GetTestConfig")
	public void test(Config config) {
		DashBoardPage dashboard = new LoginPage(config).selectCity().clickToLogin().enterPhoneAndOTP().clickOTPNext()
				.enterProductToSeaerch("Tea");
		
		dashboard.VerfiyThatSearchResult("tea");
		dashboard.verifyImageIsNotBroken();
	}
}
