package stepDefinitions;

import java.util.Map;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AddEmployeeAsAdmin;
import pageObjects.AddEmployeePage;
import utilities.ExcelReader;
import utilities.LoggerLoad;
import webDriverManager.DriverFactory;

public class AddEmployeeAsAdminSteps {
	AddEmployeeAsAdmin addEmpAsAdminPage = new AddEmployeeAsAdmin(DriverFactory.getDriver());
	AddEmployeePage addEmpPage = new AddEmployeePage(DriverFactory.getDriver());
	ExcelReader excelReader = new ExcelReader();
	
	@Given("User navigates to Admin module")
	public void user_naviagates_to_admin() {
		addEmpAsAdminPage.clickOnAdmin();
		LoggerLoad.info("User succesfully clicked on admin tab");
	}

	@When("User assigns admin role by entering employee name, user role, status, username, and password")
	public void user_selects_add_to_enter_employee_name_user_role_status_username_and_password() {
		addEmpAsAdminPage.clickOnAdd();
		Map <String,String> empDetails = excelReader.getemployeeDetails("employee");
		String firstName = empDetails.get("FirstName");
		String lastName = empDetails.get("LastName");
		String password = empDetails.get("Password");
		String fullName = firstName + " " + lastName;
		
		addEmpAsAdminPage.selectUserRole();
		LoggerLoad.info("User succesfully selects on admin user role");
		addEmpAsAdminPage.selectUserStatus();
		LoggerLoad.info("User succesfully selects on enabled user status");
		addEmpAsAdminPage.enterEmployeeName(fullName);
		LoggerLoad.info("User succesfully enters employee name"+fullName);
		addEmpAsAdminPage.enterUsername(firstName);
		LoggerLoad.info("User succesfully enters employee username"+firstName);
		addEmpAsAdminPage.enterPassword(password);
		addEmpAsAdminPage.enterConfirmPassword(password);
		LoggerLoad.info("User succesfully enters password");
		addEmpAsAdminPage.clickOnSave();
		LoggerLoad.info("User succesfully clicks on save");
	
	}

	@Then("User saves the entry and should see the message {string}")
	public void user_saves_the_entry_to_get_the(String expectedSuccessMessage) {
		String actualSuccessMessage = addEmpPage.getSucessMessage();
	    Assert.assertTrue(actualSuccessMessage.contains(actualSuccessMessage),"Success message for save employee details not found");
	    LoggerLoad.info("User recieves the success message"+actualSuccessMessage+" after clicks on save");
	}



	

}
