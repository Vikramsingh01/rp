package com.rp.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.rp.qa.driver.DriverManager;
import com.rp.qa.pageobjects.LoginExamplePage;
import com.rp.qa.reportmanager.Report;

@Listeners(com.rp.qa.utilities.TestListener.class)

public class LoginExampleTest extends BaseTest {

	@Test
	public void testLoginValidInput() throws Exception {
		LoginExamplePage obj1 = new LoginExamplePage(DriverManager.getDriver());
		obj1.login("rptest","rptest123");
		Report.log(Status.PASS, "Login successful");
		Assert.assertEquals(obj1.getPageCurrentUrl(), "https://demo.guru99.com/test/newtours/login_sucess.php");
	}

	@Test
	public void testLoginInvalidInput() throws Exception {
		LoginExamplePage obj2 = new LoginExamplePage(DriverManager.getDriver());
		obj2.login("selenium", "qa@123");
		Report.log(Status.FAIL, "Login unsuccessful");
		Assert.assertEquals(obj2.getPageCurrentUrl(), "https://demo.guru99.com/test/newtours/login_sucess.php");
	}

}
