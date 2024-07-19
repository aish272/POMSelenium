package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class EndToEndEcommerceOrderPlace_Sec18 {


    @Test()
    public void addProdToCartAndPlaceOrder()
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        try {

            String productName = "ZARA COAT 3";
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get("https://rahulshettyacademy.com/client");
            driver.findElement(By.id("userEmail")).sendKeys("alisharose@gmail.com");
            driver.findElement(By.id("userPassword")).sendKeys("Roserose7");
            driver.findElement(By.id("login")).click();
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
            List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
            WebElement targetProduct = products.stream().filter(product-> product.findElement(By.cssSelector("b")).
                    getText().equals(productName)).findFirst().orElse(null);
            targetProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
            wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
            driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
            List<WebElement> productsInCart = driver.findElements(By.cssSelector(".cartSection h3"));
            boolean result = productsInCart.stream().anyMatch(cartProduct-> cartProduct.getText().equals(productName));
            Assert.assertTrue(result);
            driver.findElement(By.cssSelector(".totalRow button")).click();
            Actions actions = new Actions(driver);
            actions.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"India").build().perform();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
            driver.findElement(By.xpath("//span[normalize-space(text())='India']")).click();
            driver.findElement(By.cssSelector(".action__submit")).click();
            String confirmationMsg  =  driver.findElement(By.cssSelector(".hero-primary")).getText();
            Assert.assertTrue(confirmationMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        }
        finally {
            driver.quit();
        }

    }
}
