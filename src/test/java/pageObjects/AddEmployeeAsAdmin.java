package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddEmployeeAsAdmin {
	
	WebDriver driver;
	WebDriverWait wait;
	
	//Constructor 
	public AddEmployeeAsAdmin(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	}
	//Locators
	private By adminLoc = By.xpath("//span[normalize-space()='Admin']");
	private By addLoc = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']");
	private By employeeNameLoc = By.xpath("//input[@placeholder='Type for hints...']");
	private By empUsernameLoc = By.xpath("//div[@class='oxd-form-row']//input[contains(@class,'oxd-input')]");
	private By passwordLoc = By.xpath("(//input[@type='password'])[1]");
	private By confirmPasswordLoc = By.xpath("(//input[@type='password'])[2]");
	private By saveButtonLoc = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']");
	private By userRoleLoc = By.xpath("(//div[normalize-space()='-- Select --']/following::div[@class='oxd-select-text--after'])[1]");
	//private By userRoleLoc = By.xpath("(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[1]");
	//private By userRoleLoc = By.xpath("//div[@class='oxd-grid-2 orangehrm-full-width-grid']//div[1]//div[1]//div[2]//div[1]//div[1]//div[2]//i[1]");
	private By adminDropDownLoc = By.xpath("//div[@role='option']//span[normalize-space()='Admin']");
	//private By statusEnabledLoc = By.xpath("(//div[normalize-space()='-- Select --']/following::div[@class='oxd-select-text--after'])[2]");
	//private By statusEnabledLoc = By.xpath("(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[2]");
	//private By statusEnabledLoc = By.xpath("//div[3]//div[1]//div[2]//div[1]//div[1]//div[1]");
	
	private By statusEnabledLoc = By.xpath("//div[contains(text(),'-- Select --')]");
	
	public void clickOnAdmin() {
		WebElement adminElem = wait.until(ExpectedConditions.elementToBeClickable(adminLoc));
		adminElem.click();
	}
	
	public void clickOnAdd() {
		WebElement addElem = wait.until(ExpectedConditions.elementToBeClickable(addLoc));
		addElem.click();
	}
	public void enterEmployeeName(String empName) {
		WebElement employeeNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeNameLoc));
		employeeNameElem.sendKeys(empName);
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}


        employeeNameElem.sendKeys(Keys.ARROW_DOWN);
        employeeNameElem.sendKeys(Keys.ENTER);
	}
	
	public void enterUsername(String empUserName) {
		
		WebElement empUsernameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(empUsernameLoc));
		empUsernameElem.sendKeys(empUserName);
		
	}
	public void enterPassword(String password) {
		WebElement passwordElem = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLoc));
		passwordElem.sendKeys(password);
	}
	public void enterConfirmPassword(String password) {
		WebElement confrimPasswordElem = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordLoc));
		confrimPasswordElem.sendKeys(password);
	}
	public void clickOnSave() {
		WebElement saveButtonElem = wait.until(ExpectedConditions.elementToBeClickable(saveButtonLoc));
		saveButtonElem.click();
	}
	public void selectUserRole() {
		WebElement userRoleElem = wait.until(ExpectedConditions.elementToBeClickable(userRoleLoc));
		userRoleElem.click();
		WebElement adminDropDownElem = wait.until(ExpectedConditions.elementToBeClickable(adminDropDownLoc));
		adminDropDownElem.click();
//		userRoleElem.sendKeys(Keys.ARROW_DOWN);
//		userRoleElem.sendKeys(Keys.ENTER);
	}
	public void selectUserStatus() {
		WebElement statusEnabledElem = wait.until(ExpectedConditions.elementToBeClickable(statusEnabledLoc));
		statusEnabledElem.click();
		
		statusEnabledElem.sendKeys(Keys.ARROW_DOWN);
		statusEnabledElem.sendKeys(Keys.ENTER);
	}

}
