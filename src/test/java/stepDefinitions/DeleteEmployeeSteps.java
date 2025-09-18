package stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AddEmployeePage;
import pageObjects.DeleteEmployeePage;
import utilities.LoggerLoad;
import webDriverManager.DriverFactory;

public class DeleteEmployeeSteps {
	DeleteEmployeePage deleteEmpPage = new DeleteEmployeePage(DriverFactory.getDriver());
	AddEmployeePage addEmpPage = new AddEmployeePage(DriverFactory.getDriver());
	@When("User selects the employee to be deleted and deletes")
	public void user_selects_the_employee_to_be_deleted_and_deletes() {
		deleteEmpPage.clickCheckBox();
	    LoggerLoad.info("User selects the employee to be deleted");
		deleteEmpPage.deleteEmployee();
		 LoggerLoad.info("User selects delete the employee");
	    deleteEmpPage.confirmDeleteEmployee();
	    LoggerLoad.info("User confirms delete the employee");
	}

	@Then("User should get a {string} message")
	public void user_should_get_a_message(String expectedDeleteMessage) {
		String actualDeleteMessage = addEmpPage.getSucessMessage();
		Assert.assertTrue(actualDeleteMessage.contains(expectedDeleteMessage), "Employee was not been deleted!");
		  LoggerLoad.info("User recieves the success message"+actualDeleteMessage+" after deleting the employee");
	    
	}

}
