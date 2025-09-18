package hooks;

import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import webDriverManager.DriverFactory;
import utilities.LoggerLoad;
import utilities.ConfigReader;
import utilities.ExcelReader;
import testRunner.TestRunner;

public class Hooks {

    private DriverFactory driverFactory;
    private static ExcelReader excelReader = new ExcelReader();
 // ===========================
    // @Before Hook
    // Runs before every scenario
    // ===========================
    @Before
    public void before(Scenario scenario) {
    	// Priority of browser resolution:
        // 1. System property from Maven (-Dbrowser=chrome) [First try to get from Maven command line -Dbrowser=chrome]
    	
    	 String browser = System.getProperty("browser"); // if passed from Maven
    	// 2.  If not set, read from TestNG XML <parameter name="browser" value="..."/>
    	    if (browser == null) {
    	        browser = org.testng.Reporter.getCurrentTestResult()
    	                     .getTestContext()
    	                     .getCurrentXmlTest()
    	                     .getParameter("browser");
    	    }
    	    // 3. If still null, Default to Chrome
    	    if (browser == null) {
    	    	
    	        browser = ConfigReader.getBrowser(); // default
    	    }

    	    LoggerLoad.info("Launching browser: " + browser);
    	 // Create driver for this thread
        driverFactory = new DriverFactory();
        driverFactory.initialiseBrowser(browser);
        // Open Application URL (from config.properties)
        String url = ConfigReader.getApplicationUrl("applicationurl");
        DriverFactory.getDriver().get(url);
        // Load Excel test data once per scenario
        ExcelReader.openExcel(ConfigReader.getExcelFilePath());
        LoggerLoad.info("Navigated to: " + url);
    }
    
    @Before
    public void beforeScenario(Scenario scenario) {
        for (String tag : scenario.getSourceTagNames()) {
            if (tag.startsWith("@Epic")) {
                Allure.label("epic", tag.replace("@Epic", ""));  
            } else if (tag.startsWith("@Feature")) {
                Allure.label("feature", tag.replace("@Feature", ""));  
            } else if (tag.startsWith("@Story")) {
                Allure.label("story", tag.replace("@Story", ""));  
            }
        }
    }
    // Get WebDriver instance for current thread
    public static WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
    // Get ExcelReader instance
    public static ExcelReader getExcelReader() {
        return excelReader;
    }
    // ===========================
    // @AfterStep Hook
    // Runs after every step
    // Captures screenshot if step fails
    // ===========================
    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            LoggerLoad.error("Step failed, capturing screenshot...");
            final byte[] screenshot = ((TakesScreenshot) getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            // Attach screenshot to Cucumber + Allure reports
            scenario.attach(screenshot, "image/png", "Failure Screenshot");
            Allure.addAttachment("Failure Screenshot",
                    new ByteArrayInputStream(screenshot));
        }
    }
    // ===========================
    // @After Hook
    // Runs after every scenario
    // - Closes browser
    // - Releases Excel file
    // ===========================
    @After
    public void after() {
        LoggerLoad.info("Closing browser...");
        if (driverFactory != null) {
           driverFactory.closeDriver();// Quit and remove ThreadLocal driver
        }
        ExcelReader.closeExcel();
    }
}
