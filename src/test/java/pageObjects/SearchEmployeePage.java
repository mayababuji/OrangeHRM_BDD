package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchEmployeePage {
	private WebDriver driver;
	private WebDriverWait wait;
	
	//Constructor
	public SearchEmployeePage(WebDriver driver){
		this.driver=driver;
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		
	}
	//Locators
	private By employeeNameLoc = By.xpath("//label[normalize-space()='Employee Name']/following::input[1]");
	private By searchEmpLoc = By.xpath("//button[normalize-space()='Search']");
	private By searchResultLoc = By.xpath("//span[normalize-space()='(1) Record Found']");
	public void enterEmployeeName(String name) {
		WebElement employeeNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeNameLoc));
		employeeNameElem.sendKeys(name);
	}
	public void clickOnSearch() {
		WebElement searchEmpElem = wait.until(ExpectedConditions.visibilityOfElementLocated(searchEmpLoc));
		searchEmpElem.click();
	}
	
	public  String getSearchResults() {
		WebElement searchResultElem = wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultLoc));
		String actualResult = searchResultElem.getText();
		return actualResult;
	}

}
