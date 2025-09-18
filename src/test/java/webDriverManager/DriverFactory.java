package webDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.time.Duration;

public class DriverFactory {

    // ThreadLocal to keep separate driver per thread
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public WebDriver initialiseBrowser(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                tlDriver.set(new ChromeDriver());
                break;
            case "firefox":
                tlDriver.set(new FirefoxDriver());
                break;
            case "edge":
                tlDriver.set(new EdgeDriver());
                break;
            case "safari":
                tlDriver.set(new SafariDriver());
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browserName);
        }
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return getDriver();
    }

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public void closeDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}
