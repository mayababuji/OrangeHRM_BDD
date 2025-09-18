package utilities;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import pageObjects.LoginPage;

public class LoginHelper {
    private LoginPage loginPage;
    private ExcelReader reader;

    public LoginHelper(WebDriver driver) {
        this.loginPage = new LoginPage(driver);
        this.reader = new ExcelReader();
    }

    public void loginWithTestData() {
        Map<String, String> testData = reader.getTestData("Login", "ValidCredentials");
      
        loginPage.enterCredentials(testData.get("Username"), testData.get("Password"));
        loginPage.clickLogin();
    }
}
