package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeleteEmployeePage {
  WebDriver driver;
  WebDriverWait wait;
  
  //Constructor
  public DeleteEmployeePage(WebDriver driver) {
	  this.driver= driver;
	  this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
  }
  //Locators
  private By employeeCheckBoxLoc = By.xpath("(//div[@class='oxd-table-card']//i[contains(@class,'oxd-icon bi-check')])[2]");
  
private By employeeDeleteButtonLoc = By.xpath("//i[@class='oxd-icon bi-trash-fill oxd-button-icon']");

  private By confirmDeleteButtonLoc = By.xpath("//button[normalize-space()='Yes, Delete']");
  
  public void clickCheckBox() {
	  WebElement employeeCheckBoxElem = wait.until(ExpectedConditions.presenceOfElementLocated(employeeCheckBoxLoc));
	  employeeCheckBoxElem.click();
  }
  public void deleteEmployee() {
	  WebElement employeeDeleteButtonElem = wait.until(ExpectedConditions.elementToBeClickable(employeeDeleteButtonLoc));
	  employeeDeleteButtonElem.click();
  }
  public void confirmDeleteEmployee() {
	  WebElement confirmDeleteButtonElem = wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButtonLoc));
	  confirmDeleteButtonElem.click();

	  wait.until(ExpectedConditions.invisibilityOf(confirmDeleteButtonElem));
	  
  }
  
}
