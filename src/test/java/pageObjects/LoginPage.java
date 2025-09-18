package pageObjects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.tagName("button");
    private By errorMessage = By.cssSelector(".oxd-alert-content-text"); // OrangeHRM invalid login banner
    private By invalidLoginMsgLoc = By.xpath("//p[normalize-space()='Invalid credentials']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Actions
    public void enterUsername(String uname) {
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        username.clear();
        username.sendKeys(uname);
    }

    public void enterPassword(String pwd) {
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        password.clear();
        password.sendKeys(pwd);
    }

    public void clickLogin() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginBtn.click();
    }

    public void enterCredentials(String uname, String pwd) {
        enterUsername(uname);
        enterPassword(pwd);
    }

    // Assertions Helpers
    public boolean isLoginButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isDisplayed();
    }

    public String getErrorMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
        } catch (Exception e) {
            return null;
        }
    }
    public String getInvalidLoginMessage() {
    	WebElement invalidLoginMsgElem = wait.until(ExpectedConditions.visibilityOfElementLocated(invalidLoginMsgLoc));
    	String actualInvalidLoginErrorMessage = invalidLoginMsgElem.getText();
    	return actualInvalidLoginErrorMessage;
    			
    }
}
