package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class A_LoginPage_158_159 {
    WebDriver driver;

    public A_LoginPage_158_159(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id="userEmail")
    private WebElement userNameElement;
    @FindBy(id="userPassword")
    private WebElement passwordElement;
    @FindBy(id="login")
    private WebElement submitButton;
    @FindBy(css="[class*='flyInOut']")
    private WebElement errorMsg;

    public void navigateToLoginPage()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }
    public B_ProductCatalogue_159To161 login(String userName, String pwd) {
        userNameElement.sendKeys(userName);
        passwordElement.sendKeys(pwd);
        submitButton.click();
        return new B_ProductCatalogue_159To161(driver);
    }

    public String getErrorMsg()
    {
        return errorMsg.getText().trim();
    }
}
