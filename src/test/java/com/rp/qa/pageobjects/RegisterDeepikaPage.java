package com.rp.qa.pageobjects;
import com.aventstack.extentreports.Status;
import com.rp.qa.configurations.Configuration;
import com.rp.qa.reportmanager.Report;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class RegisterDeepikaPage {

    WebDriver driver;

    public static String registeractual_message;
    public static String loginactual_message;

    public By textFristname = By.name("firstName");
    public By textLastname = By.name("lastName");
    public By textPhoneno = By.name("phone");
    public By textEmail = By.name("userName");
    public By txtUserName = By.name("userName");
    public By txtPassword = By.name("password");
    public By txtconfirmpassword =By.name("confirmPassword");
    public By btnSubmit = By.name("submit");
    public By txtregistermessage=By.xpath("//font[contains(text(),'Thank you for registering.')]");
    public By txtloginmessage=By.xpath("//b[contains(text(),'Thank you ')]");


    public RegisterDeepikaPage(WebDriver driver){
        this.driver = driver;

    }

    public void register( String Firstname, String Lastname, String Phone, String Email, String Username, String Password, String ConfirmPassword)throws Exception{
        driver.get(Configuration.BASE_URL);
        driver.findElement(By.linkText("REGISTER")).click();
        Report.log(Status.PASS, "Navigated to the Register page");
        driver.findElement(textFristname).sendKeys(Firstname);
        driver.findElement(textLastname ).sendKeys(Lastname);
        driver.findElement(textPhoneno).sendKeys(Phone);
        driver.findElement(textEmail).sendKeys(Email);
        driver.findElement(txtUserName).sendKeys(Username);
        driver.findElement(txtPassword).sendKeys(Password);
        driver.findElement(txtconfirmpassword).sendKeys(ConfirmPassword);
        driver.findElement(btnSubmit).click();
        registeractual_message=driver.findElement(txtregistermessage).getText();



    }
    public void registeruserlogin(String Username, String Password ) throws Exception{
        driver.get(Configuration.BASE_URL);
        driver.findElement(txtUserName).sendKeys(Username);
        driver.findElement(txtPassword).sendKeys(Password);
        driver.findElement(btnSubmit).click();
        loginactual_message=driver.findElement(txtloginmessage).getText();
    }
    public String getPageCurrentUrl() throws Exception {
        return driver.getCurrentUrl();
    }


}
