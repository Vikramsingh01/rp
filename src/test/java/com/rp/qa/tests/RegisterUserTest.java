package com.rp.qa.tests;
import com.rp.qa.configurations.Configuration;
import com.rp.qa.pageobjects.ResgisterUser;
import com.rp.qa.utilities.JsonFileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.rp.qa.driver.DriverManager;
import com.rp.qa.pageobjects.LoginExamplePage;
import com.rp.qa.reportmanager.Report;

@Listeners(com.rp.qa.utilities.TestListener.class)


public class RegisterUserTest extends BaseTest{

    @Test (dataProvider = "userData")
    public void testRegisterUser(String FirstName,String LastName, String Phone, String email, String address, String city, String state, String postal, String password) throws Exception {

        ResgisterUser obj= new ResgisterUser(DriverManager.getDriver());

        String url=obj.register(FirstName,LastName,Phone,email,address,city,state,postal,password);
        Assert.assertEquals(url, "https://demo.guru99.com/test/newtours/register_sucess.php");
        Report.log(Status.PASS, "Registration successful");
    }
    @DataProvider
    public Object[][] userData() throws Exception {
        JsonFileReader jsonReader = new JsonFileReader();
        JSONArray usersList = jsonReader.readJson(Configuration.TEST_RESOURCE_PATH + "/register.json", "users");
        Object[][] dataObj = new Object[usersList.size()][9];

        for (int i = 0; i < dataObj.length; i++) {
            JSONObject user = (JSONObject) usersList.get(i);
            dataObj[i][0] = user.get("FirstName");
            dataObj[i][1] = user.get("LastName");
            dataObj[i][2] = user.get("Phone");
            dataObj[i][3] = user.get("Email");
            dataObj[i][4] = user.get("Address");
            dataObj[i][5] = user.get("City");
            dataObj[i][6] = user.get("State");
            dataObj[i][7] = user.get("Postal");
            dataObj[i][8] = user.get("Password");

        }
        return dataObj;
    }

}
