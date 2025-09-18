package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddEmployeePage {
	   private WebDriver driver;
	    private WebDriverWait wait;

	    // Locators
	    private By addButtonLoc = By.xpath("//button[normalize-space()='Add']");
	    private By PIMButtonLoc = By.xpath("//span[normalize-space()='PIM']");
	    private By FirstNameLoc = By.xpath("//input[@class='oxd-input oxd-input--active orangehrm-firstname']");
	    private By LastNameLoc = By.xpath("//input[@class='oxd-input oxd-input--active orangehrm-lastname']");
	    private By SaveButtonLoc = By.xpath("//button[normalize-space()='Save']");
	    private By successToastLoc = By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']");
	
	    
	    // Constructor
	    public AddEmployeePage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }
	    
	    //Actions
	    public void clickOnPIM() {
	    	WebElement PIMElem = wait.until(ExpectedConditions.elementToBeClickable(PIMButtonLoc));
	    	PIMElem.click();
	    	
	    }
	    public void clickOnAdd() {
	    	WebElement addElem = wait.until(ExpectedConditions.elementToBeClickable(addButtonLoc));
	    	addElem.click();
	    	
	    }
	    public void enterEmployeeDetails(String firstName,String lastName) {
	    	WebElement firstNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(FirstNameLoc));
	    	firstNameElem.sendKeys(firstName);
	    	WebElement lastNameElem = wait.until(ExpectedConditions.visibilityOfElementLocated(LastNameLoc));
	    	lastNameElem.sendKeys(lastName);
	    	
	    }
	    
	    public void clickOnSave() {
	    	WebElement saveElem = wait.until(ExpectedConditions.elementToBeClickable(SaveButtonLoc));
	    	saveElem.click();
	    }

	    public String getSucessMessage() {
	    	WebElement saveSuccessMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(successToastLoc));
	    	
	    	return saveSuccessMessage.getText();
	    	
	    	
	    }
}
