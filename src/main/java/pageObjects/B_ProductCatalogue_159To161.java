package pageObjects;

import commonMethods.GenericUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class B_ProductCatalogue_159To161 extends GenericUtility {
    WebDriver driver;

    public B_ProductCatalogue_159To161(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css=".ng-animating")
    private WebElement spinner;
    @FindBy(css="[routerlink*='cart']")
    private WebElement goToCartPage;
    @FindBy(css="[routerlink*='myorders']")
    private WebElement goToOrderPage;

    private By products = By.cssSelector(".mb-3");
    private By productText = By.cssSelector("b");
    private By addToCartBtn = By.cssSelector(".card-body button:last-of-type");
    private By toastMsg = By.cssSelector("#toast-container");


    public List<WebElement> getProducts() {
        return driver.findElements(products);
    }

    public WebElement getTargetProduct(String productName) {
        return getProducts().stream().filter(product-> product.findElement(productText).
                getText().equals(productName)).findFirst().orElse(null);
    }

    public void addToCart(String productName)
    {
        getTargetProduct(productName).findElement(addToCartBtn).click();
        waitForElementToAppear(toastMsg);
        waitForElementToDisappear(spinner);
    }

    public C_CartPage_163 navigateCartPage()
    {
        goToCartPage.click();
        return new C_CartPage_163(driver);
    }
    public F_OrderPage_169 navigateOrderPage()
    {
        goToOrderPage.click();
        return new F_OrderPage_169(driver);
    }
}
