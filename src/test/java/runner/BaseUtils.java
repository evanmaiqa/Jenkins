package runner;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BaseUtils {
    static {
        WebDriverManager.chromedriver().setup();
    }
    static WebDriver createDriver() {
        WebDriver driver = new ChromeDriver();

        return driver;
    }
}
