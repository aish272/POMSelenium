package tests;

import commonUtilities.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.B_ProductCatalogue_159To161;
import pageObjects.C_CartPage_163;

public class ErrorValidationTest extends BaseTest {

    @Test(groups = {"loginPageError"})
    public void loginErrorValidation(){

        loginPage.login("alisharose@gmail.com", "Roserose");
        String confirmationMsg = loginPage.getErrorMsg();
        Assert.assertEquals(confirmationMsg,"Incorrect email or password.");
    }
    @Test()
    public void prodErrorValidation() {
        String productName = "ZARA COAT 3";
        B_ProductCatalogue_159To161 productCatalogue = loginPage.login("alisharose@gmail.com", "Roserose7");

        productCatalogue.addToCart(productName);
        C_CartPage_163 cartPage = productCatalogue.navigateCartPage();

        boolean result = cartPage.returnBooleanForElementPresenceInCart(productName+"90");
        Assert.assertFalse(result);


    }
}
