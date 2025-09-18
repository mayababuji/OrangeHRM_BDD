package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "src/test/resources/features/",        // Path to feature files
        glue = {"stepDefinitions", "hooks"},            // Package locations for steps & hooks
      //tags = "@AddNewEmployeeTest",
        //tags= "@SearchEmployeeByName",
       // tags="@InvalidLoginTest",
        //tags = "not @DeleteEmployee",
       // tags = "@DeleteEmployee",
       //tags="@AddEmployeeAsAdmin",
       //tags="@LoginNewEmployee",
        plugin = {
                "pretty",                               // Console output formatting
                "html:target/cucumber-reports/report.html", // HTML report
                "json:target/cucumber-reports/report.json",// JSON report
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        		"com.aventstack.chaintest.plugins.ChainTestCucumberListener:"//chanintest plugin
        
        },
        monochrome = true   // Removes unreadable ANSI characters in console
)
public class TestRunner extends AbstractTestNGCucumberTests {
	// Cucumber scenarios are provided to TestNG via this method
    // parallel = true  -> scenarios run in parallel (multi-threaded)
    // parallel = false -> scenarios run sequentially (one after another)

    @Override
    @DataProvider(parallel = false)  // Change to false for  sequential execution true for parallel
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
