package com.rp.qa.tests;

import org.testng.annotations.Test;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import com.aventstack.extentreports.Status;
import com.rp.qa.configurations.Configuration;
import com.rp.qa.driver.DriverManager;
import com.rp.qa.pageobjects.LoginExamplePage;
import com.rp.qa.reportmanager.Report;
import com.rp.qa.utilities.JsonFileReader;

@Listeners(com.rp.qa.utilities.TestListener.class)

public class JsonExampleTest extends BaseTest {

	@Test(dataProvider = "userData")
	public void testJsonLogin(String username, String password) throws Exception {
		LoginExamplePage obj = new LoginExamplePage(DriverManager.getDriver());

		obj.login(username, password);
		Assert.assertEquals(obj.getPageCurrentUrl(), "https://demo.guru99.com/test/newtours/login_sucess.php");
		Report.log(Status.PASS, "Login Successful");
	}

	@DataProvider
	public Object[][] userData() throws Exception {
		JsonFileReader jsonReader = new JsonFileReader();
		JSONArray usersList = jsonReader.readJson(Configuration.TEST_RESOURCE_PATH + "/login.json", "users");
		Object[][] dataObj = new Object[usersList.size()][2];

		for (int i = 0; i < dataObj.length; i++) {
			JSONObject user = (JSONObject) usersList.get(i);
			dataObj[i][0] = user.get("username");
			dataObj[i][1] = user.get("password");
		}
		return dataObj;
	}
}
