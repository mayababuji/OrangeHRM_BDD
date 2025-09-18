package stepDefinitions;


import io.cucumber.java.en.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import org.testng.Assert;
import pageObjects.LoginPage;
import utilities.ExcelReader;
import webDriverManager.DriverFactory;
import utilities.LoggerLoad;

import java.util.Map;

public class LoginSteps {
    LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    Map<String, String> testData;
	ExcelReader reader = new ExcelReader();

    @Given("user launches OrangeHRM")
    public void user_launches_OrangeHRM() {
       // Hooks.getDriver().get(Hooks.getDriver().getCurrentUrl()); 
        // OR simply rely on Hooks' @Before navigation (better!)
    }

    @When("user logs in with test case {string} from sheet {string}")
    public void user_logs_in_with_test_case_from_sheet(String testCase, String sheetName) {
		testData = reader.getTestData(sheetName, testCase);

        if (testData == null) {
            throw new RuntimeException("Test data not found for test case: " + testCase);
        }

        loginPage.enterCredentials(testData.get("Username"), testData.get("Password"));
        loginPage.clickLogin();
    	LoggerLoad.info("User successfully clicks on login");
    }

    @Then("dashboard should be displayed")
    public void dashboard_should_be_displayed() {
        
    	String currenturl = DriverFactory.getDriver().getCurrentUrl();
    	Assert.assertTrue(currenturl.contains("dashboard"),"Dashboard is not displayed:"+currenturl);
    	LoggerLoad.info("Dashboard is succesfully displayed and the current url is "+currenturl);
      
    }
    
    @When("user enters username {string} and password {string}")
    public void user_enters_username_and_password(String username, String password) {
    	  loginPage.enterCredentials(username,password);
    	  loginPage.clickLogin();
    }
    @Then("error message should be displayed {string}")
    public void error_message_should_be_displayed(String expectedErrorMsg) {
        String actualInvalidLoginErrorMsg = loginPage.getErrorMessage();
        Assert.assertTrue(actualInvalidLoginErrorMsg.contains(expectedErrorMsg), "Invalid login error message is not displayed");
        
    }
}
