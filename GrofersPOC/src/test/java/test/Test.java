package test;

import common.Config;
import pageobject.AddToCart;
import pageobject.DashBoardPage;
import pageobject.LoginPage;

public class Test extends TestBase {
	@org.testng.annotations.Test(dataProvider = "GetTestConfig")
	public void test(Config config) {
		String [] product={"Tata Agni Tea (Pouch)"};
		DashBoardPage dashboard = new LoginPage(config).selectCity().clickToLogin().enterPhoneAndOTP().clickOTPNext()
				.enterProductToSeaerch(product[0]);

		dashboard.VerfiyThatSearchResult(product[0]);
		dashboard.verifyImageIsNotBroken();
		AddToCart cart = dashboard.selectOneProduct().addToCart().clickToOpenCart();
		cart.verifyProduct(product);
	}
}
