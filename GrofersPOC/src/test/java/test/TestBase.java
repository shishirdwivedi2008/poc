package test;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import common.Config;

public class TestBase {

	@DataProvider(name = "GetTestConfig")
	public Object[][] GetTestConfig(Method method) {
		Config testConf = new Config();
		return new Object[][] { { testConf } };
	}


}
