import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainTest {
    private static final String[] browsersArr = new String[]{"chrome", "firefox", "msedge"};
    private static WebDriver driver;

    private static String getBrowserName() {
        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
        return caps.getBrowserName() + " " + caps.getVersion() + " " + caps.getPlatform().toString().toLowerCase();
    }

    private static void setReport() throws IOException {
        FileWriter fileWriter = new FileWriter("report.txt", true);
        fileWriter.write(getBrowserName() + "\n");
        fileWriter.close();
    }


    @Test
    public void firstTest() throws IOException {
        if (!new File("report.txt").createNewFile()) {
            FileWriter fileWriter = new FileWriter("report.txt", false);
            fileWriter.write("");
//            fileWriter.close();
        }
        for (String browser : browsersArr) {
            switch (browser) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
                    driver = new FirefoxDriver();
                    break;
                case "msedge":
                    System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
                    driver = new EdgeDriver();
                    break;
            }
            setReport();

            driver.quit();
        }

    }
}
