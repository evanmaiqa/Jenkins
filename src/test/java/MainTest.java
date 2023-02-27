import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainTest {
    private static final String osName = System.getProperty("os.name").toLowerCase();
    private static List<String> browsersList = new ArrayList<>();
    private static WebDriver driver;

    private static String getBrowserName() {
        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
        return caps.getBrowserName() + ";" + caps.getVersion() + ";" + caps.getPlatform().toString().toLowerCase();
    }

    private static void setReport() throws IOException {
        FileWriter fileWriter = new FileWriter("report.csv", true);
        fileWriter.write(getBrowserName() + ";" + driver.manage().window().getSize() + "\n");
        fileWriter.close();
    }

    @Test
    public void firstTest() throws IOException {
        if (!new File("report.csv").createNewFile()) {
            FileWriter fileWriter = new FileWriter("report.csv", false);
            fileWriter.write("");
            fileWriter.close();
        }

        browsersList.add("chrome");
        browsersList.add("firefox");
        browsersList.add("msedge");
        if (osName.contains("mac")) {
            browsersList.add("safari");
        }

        for (String browser : browsersList) {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "msedge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
            }
            driver.manage().window().setSize(new Dimension(1920, 1080));
            setReport();
            driver.quit();
        }
    }
}
