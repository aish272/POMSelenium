package tests;

import commonUtilities.BaseTest;
import commonUtilities.RetryListeners;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.*;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class POMBasedEndToEndOrderPlace extends BaseTest {
    String productName;

    @Test(dataProvider = "inputData",groups = {"Purchase"}, retryAnalyzer = RetryListeners.class)
    public void placeOrder(HashMap<String,String> input) {
        B_ProductCatalogue_159To161 productCatalogue = loginPage.login(input.get("email"), input.get("pwd"));
        productName = input.get("product");
        productCatalogue.addToCart(productName);
        C_CartPage_163 cartPage = productCatalogue.navigateCartPage();

        boolean result = cartPage.returnBooleanForElementPresenceInCart(productName);
        Assert.assertTrue(result);
        D_CheckoutPage_164 checkoutPage = cartPage.navigateToCheckoutPage();

        checkoutPage.addCountry("India");
        E_ConfirmationPage_164 confirmationPage = checkoutPage.clickOnSubmitNavigateToConfirmationPage();
        String confirmationMsg = confirmationPage.getConfirmationMsg();
        Assert.assertTrue(confirmationMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));


    }
    @Test(dependsOnMethods = {"placeOrder"})
    public void checkProdInOrderPage() {

        B_ProductCatalogue_159To161 productCatalogue = loginPage.login("alisharose@gmail.com", "Roserose7");
        F_OrderPage_169 orderPage = productCatalogue.navigateOrderPage();

        productName = "ZARA COAT 3";
        boolean result = orderPage.returnBooleanForElementPresenceInOrders(productName);
        Assert.assertTrue(result);

    }
    @DataProvider(name = "inputData")
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> maps = convertJsonToHashmap();
        Object [][] inputData = new Object[maps.size()][1];
        for(int i =0;i<maps.size();i++)
        {
            inputData[i][0] = maps.get(i);
        }
        return inputData;
    }
}
