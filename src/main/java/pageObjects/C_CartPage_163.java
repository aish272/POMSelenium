package pageObjects;

import commonMethods.GenericUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class C_CartPage_163 extends GenericUtility {
    WebDriver driver;

    public C_CartPage_163(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css=".cartSection h3")
    private List<WebElement> productsInCart;

    @FindBy(css=".totalRow button")
    private WebElement goToCheckoutPage;

    public Boolean returnBooleanForElementPresenceInCart(String productName) {
        return productsInCart.stream().anyMatch(cartProduct-> cartProduct.getText().equals(productName));
    }

    public D_CheckoutPage_164 navigateToCheckoutPage()
    {
        goToCheckoutPage.click();
        return new D_CheckoutPage_164(driver);
    }
}
