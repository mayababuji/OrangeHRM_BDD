package stepDefinitions;

import java.util.Map;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Epic;
import pageObjects.AddEmployeePage;
import utilities.ExcelReader;
import utilities.LoggerLoad;
import utilities.LoginHelper;
import webDriverManager.DriverFactory;

public class AddEmployeeSteps {
	AddEmployeePage addEmpPage = new AddEmployeePage(DriverFactory.getDriver());
	LoginHelper loginHelper = new LoginHelper(DriverFactory.getDriver());
	ExcelReader excelReader = new ExcelReader();
	
	@Given("User navigates to PIM and clicks in add after login")
	public void user_navigates_to_pim_and_clicks_in_add_after_login() {
		loginHelper.loginWithTestData();
		addEmpPage.clickOnPIM();
		addEmpPage.clickOnAdd();
	}

	@When("User  enter the new employee details from sheet {string} and  saves the entries")
	public void user_enter_the_new_employee_details_from_sheet_and_saves_the_entries(String employeeDetailSheet) {
		Map<String,String> empDetails = excelReader.getemployeeDetails(employeeDetailSheet);
		String firstName = empDetails.get("FirstName");
		String lastName = empDetails.get("LastName");
		addEmpPage.enterEmployeeDetails(firstName, lastName);
		LoggerLoad.info("User succesfully enters the firstname"+firstName+"and the lastename"+ lastName);
		addEmpPage.clickOnSave();
		LoggerLoad.info("User succesfully clicks on save");

	}

	@Then("User recieves a new employee added {string} message")
	public void user_recieves_a_new_employee_added_message(String expectedSuccessMessage) {
	    String actualSuccessMessage = addEmpPage.getSucessMessage();
	    Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage),"Success message for save employee details not found");
	    LoggerLoad.info("User recieves the success message"+actualSuccessMessage+" after clicks on save");
	}




}
