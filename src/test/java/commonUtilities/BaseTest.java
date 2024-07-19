package commonUtilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObjects.A_LoginPage_158_159;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

public class BaseTest {

    protected WebDriver driver;
    protected A_LoginPage_158_159 loginPage;

    public String getGlobalConfig(String key) throws IOException {
        FileInputStream fis = new FileInputStream("/Users/aish/Documents/POMSelenium/src/test/java/resouces/GlobalConfig.properties");
        Properties properties = new Properties();
        properties.load(fis);
        return properties.getProperty(key);
    }

    public WebDriver initializeDriver() throws IOException {
        switch (getGlobalConfig("browser")) {
            case "Chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
            }
            case "Edge" -> {
                System.setProperty("webdriver.edge.driver", "C://EdgeDriver.exe");
                driver = new EdgeDriver();
            }
            case "Firefox" -> {
                System.setProperty("webdriver.gecko.driver", "");
                driver = new FirefoxDriver();
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public A_LoginPage_158_159 launchApp() throws IOException {
        driver = initializeDriver();
        loginPage= new A_LoginPage_158_159(driver);
        loginPage.navigateToLoginPage();
        return loginPage;
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown()
    {
        driver.quit();
    }

    public List<HashMap<String, String>> convertJsonToHashmap() throws IOException {
        String jsonAsString = new String(Files.readAllBytes(Path.of("/Users/aish/Documents/POMSelenium/src/test/java/Input/Input.json")));
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> list = mapper.readValue(jsonAsString, new TypeReference<List<HashMap<String,String>>>() {
        });
        return list;
    }

    public String takeScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String pathOfSS = "/Users/aish/Documents/POMSelenium/test-output/Screenshot"+testCaseName+getTimeStamp()+".png";
        File destination = new File(pathOfSS);
        FileUtils.copyFile(source,destination);
        return pathOfSS;
    }

    public String getTimeStamp()
    {
        Locale locale = new Locale("English","IN");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss",locale);
        return LocalDateTime.now().format(formatter);
    }

}
