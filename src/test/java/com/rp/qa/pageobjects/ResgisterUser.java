package com.rp.qa.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.Status;
import com.rp.qa.configurations.Configuration;
import com.rp.qa.reportmanager.Report;

public class ResgisterUser {

    WebDriver driver;
    public By registerBtn= By.xpath("//td[@class='mouseOut']/a[@href='register.php']");
    public By FirstName=By.xpath("//input[@name='firstName']");
    public By LastName= By.xpath("//input[@name='lastName']");
    public By Phone=By.xpath("//input[@name='phone']");
    public By email=By.xpath("//input[@name='userName']");
    public By address=By.xpath("//input[@name='address1']");
    public By cityField=By.xpath("//input[@name='city']");
    public By stateField=By.xpath("//input[@name='state']");
    public By postal=By.xpath("//input[@name='postalCode']");
    public By CountryField=By.xpath("//select[@name='country']");
    public By selectCountry=By.xpath("//option[@value='ANGOLA']");
    public By userNamr=By.xpath("//input[@id='email']");
    public By password=By.xpath("//input[@name='password']");
    public By confirmPswd=By.xpath("//input[@name='confirmPassword']");
    public By submit=By.xpath("//input[@name='submit']");

    public ResgisterUser(WebDriver driver) {
        this.driver = driver;
    }
  public String register(String firstNm, String lastNm, String phNumber, String email_id, String Add, String City, String State, String Postal, String Password) throws InterruptedException {
        driver.get(Configuration.BASE_URL);
        driver.findElement(registerBtn).click();
        driver.navigate();
        driver.findElement(FirstName).sendKeys(firstNm);
        driver.findElement(LastName).sendKeys(lastNm);
        driver.findElement(Phone).sendKeys(phNumber);

        driver.findElement(email).sendKeys(email_id);

      driver.findElement(address).sendKeys(Add);

        driver.findElement(cityField).sendKeys(City);
        driver.findElement(stateField).sendKeys(State);
        driver.findElement(postal).sendKeys(Postal);
        driver.findElement(CountryField).click();
        driver.findElement(selectCountry).click();
        driver.findElement(userNamr).sendKeys(email_id);
        driver.findElement(password).sendKeys(Password);
        driver.findElement(confirmPswd).sendKeys(Password);
        driver.findElement(submit).click();
        driver.navigate();
        String url=driver.getCurrentUrl();
        return url;
  }


}
