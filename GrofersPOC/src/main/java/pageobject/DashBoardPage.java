package pageobject;

import org.openqa.selenium.support.PageFactory;

import common.Config;

public class DashBoardPage {

	Config config;
	
	public DashBoardPage(Config config) {
		this.config=config;
		PageFactory.initElements(config.driver, this);
	}
}
