import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainTest {
    private static final String osName = System.getProperty("os.name").toLowerCase();
    private static final String[] browsersArr = new String[]{"chrome", "firefox", "msedge", "safari"};
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

        if (osName.contains("mac os x")) {
            for (String browser : browsersArr) {
                switch (browser) {
                    case "chrome":
                        System.setProperty("webdriver.chrome.driver", "chromedriver");
                        ChromeOptions optionsChrome = new ChromeOptions();
                        optionsChrome.addArguments("--headless");
                        driver = new ChromeDriver(optionsChrome);
                        break;
                    case "firefox":
                        System.setProperty("webdriver.gecko.driver", "geckodriver");
                        FirefoxOptions optionsFirefox = new FirefoxOptions();
                        optionsFirefox.setHeadless(true);
                        driver = new FirefoxDriver(optionsFirefox);
                        break;
                    case "msedge":
                        System.setProperty("webdriver.edge.driver", "msedgedriver");
                        EdgeOptions optionsEdge = new EdgeOptions();
                        optionsEdge.setCapability("UseChromium", true);
//                        optionsEdge.addArguments("headless");
                        driver = new EdgeDriver(optionsEdge);
                        break;
                    case "safari":
                        driver = new SafariDriver();
                        break;
                }
                driver.manage().window().setSize(new Dimension(1920, 1080));
                setReport();
                driver.quit();
            }
        } else if (osName.contains("windows")) {
            for (String browser : browsersArr) {
                switch (browser) {
                    case "chrome":
                        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
                        ChromeOptions optionsChrome = new ChromeOptions();
                        optionsChrome.addArguments("--headless");
                        driver = new ChromeDriver(optionsChrome);
                        break;
                    case "firefox":
                        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
                        FirefoxOptions optionsFirefox = new FirefoxOptions();
                        optionsFirefox.setHeadless(true);
                        driver = new FirefoxDriver(optionsFirefox);
                        break;
                    case "msedge":
                        System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
                        EdgeOptions optionsEdge = new EdgeOptions();
                        optionsEdge.setCapability("UseChromium", true);
                        //           optionsEdge.
                        driver = new EdgeDriver(optionsEdge);
                        break;
                }
                driver.manage().window().setSize(new Dimension(1920, 1080));
                setReport();
                driver.quit();
            }
        } else {
            System.out.println("NULL");
        }

    }
}
