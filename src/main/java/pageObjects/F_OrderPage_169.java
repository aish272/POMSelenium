package pageObjects;

import commonMethods.GenericUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class F_OrderPage_169 extends GenericUtility {
    WebDriver driver;

    public F_OrderPage_169(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css="tr td:nth-child(3)")
    private List<WebElement> productsInOrderList;

    public Boolean returnBooleanForElementPresenceInOrders(String productName) {
        return productsInOrderList.stream().anyMatch(cartProduct-> cartProduct.getText().equals(productName));
    }
}
