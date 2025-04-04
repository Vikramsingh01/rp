package com.rp.qa.tests;
import com.aventstack.extentreports.Status;
import com.rp.qa.driver.DriverManager;
import com.rp.qa.pageobjects.LoginExamplePage;
import com.rp.qa.pageobjects.RegisterDeepikaPage;
import com.rp.qa.reportmanager.Report;
import org.junit.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.rp.qa.utilities.TestListener.class)


public class RegisterDeepikaTest extends BaseTest{

    @Test(priority = 1)
    public void testRegisterValidInput() throws Exception {
        RegisterDeepikaPage reg1= new RegisterDeepikaPage(DriverManager.getDriver());
        reg1.register( "Deepika","Kaushik","9462684655","dkaushik@rightpoint.com","dk","123456@","123456@");
        Assert.assertEquals("https://demo.guru99.com/test/newtours/register_sucess.php", reg1.getPageCurrentUrl());
        Report.log(Status.PASS, "Register Successfully");
        Assert.assertEquals("Thank you for registering. You may now sign-in using the user name and password you've just entered.",reg1.registeractual_message);
        Report.log(Status.PASS, "Register text message is found");
    }
    @Test(priority = 2)
    public void testLoginValidInput() throws Exception {
        RegisterDeepikaPage obj1= new RegisterDeepikaPage(DriverManager.getDriver());
        obj1.registeruserlogin("dk","123456@");
        Assert.assertEquals(obj1.getPageCurrentUrl(), "https://demo.guru99.com/test/newtours/login_sucess.php");
        Report.log(Status.PASS, "Login successful");
        Assert.assertEquals("Thank you for Loggin.",obj1.loginactual_message);
        Report.log(Status.PASS, "login Successfully text message is found");
    }

    @Test(priority = 3)
    public void testRegisterInvalidInput() throws Exception{
        RegisterDeepikaPage reg2= new RegisterDeepikaPage(DriverManager.getDriver());
        reg2.register( "selenium","testing","9462684655","dkaushik12@rightpont","dk","123456","1234567");
        Assert.assertEquals("https://demo.guru99.com/test/newtours/register_sucess.php", reg2.getPageCurrentUrl());
        Report.log(Status.PASS, "Login successful");




    }





}
