package stepDefinitions;

import java.util.Map;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import utilities.ExcelReader;
import utilities.LoggerLoad;
import webDriverManager.DriverFactory;

public class LoginAsNewEmployeeSteps {
	LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	ExcelReader excelReader = new ExcelReader();
	
	@Given("User enters valid credentials from excel sheet {string}")
	public void user_enters_valid_credentials_from_excel_sheet(String sheetname) {
	    Map<String,String> empDetails = excelReader.getemployeeDetails(sheetname);
	    String userName = empDetails.get("FirstName");
	    String password = empDetails.get("Password");
	    loginPage.enterCredentials(userName, password);
	   
	}

	@When("User logs on with valid credentials")
	public void user_logs_on_with_valid_credentials() {
		 loginPage.clickLogin();
		 LoggerLoad.info("User successfully clicks on login");
	}

	@Then("User should land in dashboard page")
	public void user_should_land_in_dashboard_page() {
	 	String currenturl = DriverFactory.getDriver().getCurrentUrl();
    	Assert.assertTrue(currenturl.contains("dashboard"),"Dashboard is not displayed:"+currenturl);
    	LoggerLoad.info("Dashboard is succesfully displayed and the current url is "+currenturl);
    	
	   
	}



}
