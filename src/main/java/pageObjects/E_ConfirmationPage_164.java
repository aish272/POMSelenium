package pageObjects;

import commonMethods.GenericUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class E_ConfirmationPage_164 extends GenericUtility {
    WebDriver driver;
    Actions actions;

    public E_ConfirmationPage_164(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
        actions = new Actions(driver);
    }
    @FindBy(css=".hero-primary")
    private WebElement confirmationMsg;

    public String getConfirmationMsg()
    {
        return confirmationMsg.getText();
    }


}
