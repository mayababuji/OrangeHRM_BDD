package stepDefinitions;

import java.util.Map;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Epic;
import pageObjects.AddEmployeePage;
import pageObjects.SearchEmployeePage;
import utilities.ExcelReader;
import utilities.LoggerLoad;
import utilities.LoginHelper;
import webDriverManager.DriverFactory;

public class SearchEmployeeSteps {
	SearchEmployeePage searchEmpPage = new SearchEmployeePage(DriverFactory.getDriver());
	LoginHelper loginHelper = new LoginHelper(DriverFactory.getDriver());
	AddEmployeePage addEmpPage = new AddEmployeePage(DriverFactory.getDriver());
	ExcelReader excelReader = new ExcelReader();
	
	@Given("User navigates to PIM after login")
	public void user_navigates_to_pim_after_login() {
	    loginHelper.loginWithTestData();
	    addEmpPage.clickOnPIM();
	}
	@When("User  enter the new employee details from sheet {string}  proceeding to search")
	public void user_enter_the_new_employee_details_from_sheet_proceeding_to_search(String employeeDetailSheet) {
	    Map<String,String> empDetails = excelReader.getemployeeDetails(employeeDetailSheet);
	    String firstName = empDetails.get("FirstName");
	    String lastName = empDetails.get("LastName");
	    String fullName = firstName +" "+  lastName;
	    searchEmpPage.enterEmployeeName(fullName);
	    LoggerLoad.info("User enters  the full name of employee "+fullName+" to be searched");
	    searchEmpPage.clickOnSearch();
	    LoggerLoad.info("User successfully click on search");
	    
	}
	@Then("User retrives {string} message")
	public void user_retrives_message(String expectedSearchResult) {
	    String actualSearchResult = searchEmpPage.getSearchResults();
	    Assert.assertTrue(actualSearchResult.contains(expectedSearchResult), "Employee name was not found!");
	    LoggerLoad.info("User recieves the success message"+actualSearchResult+" after searching with employee name");
	}

}
