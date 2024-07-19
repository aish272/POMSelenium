package pageObjects;

import commonMethods.GenericUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class D_CheckoutPage_164 extends GenericUtility {
    WebDriver driver;
    Actions actions;

    public D_CheckoutPage_164(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
        actions = new Actions(driver);
    }
    @FindBy(css="[placeholder='Select Country']")
    private WebElement countryTextField;
    @FindBy(xpath="//span[normalize-space(text())='India']")
    private WebElement countryOption;
    @FindBy(css=".action__submit")
    private WebElement submitBtn;

    private By countryOptionList = By.cssSelector(".ta-results");

    public void addCountry(String countryName)
    {
        actions.sendKeys(countryTextField,countryName).build().perform();
        waitForElementToAppear(countryOptionList);
        countryOption.click();
    }

    public E_ConfirmationPage_164 clickOnSubmitNavigateToConfirmationPage()
    {
        submitBtn.click();
        return new E_ConfirmationPage_164(driver);
    }
}
