package swag.labs.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import swag.labs.utils.DriverInit;
import swag.labs.utils.PageObjectModel;
import swag.labs.utils.SwagLabsLogin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SwagLabsTestCases extends DriverInit {

    @Test(priority = 2)
    public void loginNoUsernameTest() {
        driver.findElement(PageObjectModel.passwordField).sendKeys(PageObjectModel.password);
        driver.findElement(PageObjectModel.loginButton).click();

        String expectedMessage = "Epic sadface: Username is required";
        String actualMessage = driver.findElement(PageObjectModel.loginErrorMessage).getText();

        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @Test(priority = 0)
    public void loginTest() {
        SwagLabsLogin.login(driver, wait);

        Assert.assertFalse(driver.findElements(PageObjectModel.titleHeader).isEmpty());


    }

    @Test(priority = 1)
    public void logoutTest() throws InterruptedException {
        driver.findElement(PageObjectModel.usernameField).sendKeys(PageObjectModel.username);
        driver.findElement(PageObjectModel.passwordField).sendKeys(PageObjectModel.password);

        driver.findElement(PageObjectModel.loginButton).click();

        driver.findElement(PageObjectModel.burgerMenuIcon).click();

        Thread.sleep(1500);

        driver.findElement(PageObjectModel.logoutLink).click();

        Assert.assertFalse(driver.findElements(PageObjectModel.loginButton).isEmpty());

    }


    @Test(priority = 3)
    public void sortingTest() throws InterruptedException {
        SwagLabsLogin.login(driver, wait);

        wait.until(ExpectedConditions.visibilityOfElementLocated(PageObjectModel.productPos1Button));

        //Thread.sleep(2000);

        List<WebElement> inventoryList = driver.findElement(PageObjectModel.inventoryList).findElements(By.className("inventory_item"));

        String product1Name = inventoryList.get(0).findElement(PageObjectModel.productName).getText();
        String product1Price = inventoryList.get(0).findElement(PageObjectModel.productPrice).getText();

        //driver.findElement(PageObjectModel.dropdownMenu).click();
        driver.findElement(PageObjectModel.dropdownZToA).click();

        inventoryList = driver.findElement(PageObjectModel.inventoryList).findElements(By.className("inventory_item"));

        String product2Name = inventoryList.get(0).findElement(PageObjectModel.productName).getText();
        String product2Price = inventoryList.get(0).findElement(PageObjectModel.productPrice).getText();

        Assert.assertNotEquals(product1Name, product2Name);
        Assert.assertNotEquals(product1Price, product2Price);

    }

    @Test(priority = 4)
    public void checkoutTest() {
        SwagLabsLogin.login(driver, wait);

        wait.until(ExpectedConditions.visibilityOfElementLocated(PageObjectModel.productPos1Button));

        List<WebElement> inventoryList = driver.findElement(PageObjectModel.inventoryList).findElements(By.className("inventory_item"));

        List<String> selectedItemsNames = new ArrayList<>();
        List<String> selectedItemsPrices = new ArrayList<>();

        Random r = new Random();

        Collections.shuffle(inventoryList, r);

        for (int i = 0; i < 3; i++) {
            inventoryList.get(i).findElement(By.className("btn_inventory")).click();
            selectedItemsNames.add(inventoryList.get(i).findElement(PageObjectModel.productName).getText());
            selectedItemsPrices.add(inventoryList.get(i).findElement(PageObjectModel.productPrice).getText());
        }

        driver.findElement(PageObjectModel.cartIcon).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(PageObjectModel.checkoutButton));

        List<WebElement> checkoutItems = driver.findElement(PageObjectModel.checkoutList).findElements(By.className("cart_item"));

        for (int i = 0; i < 3; i++) {
            Assert.assertEquals(selectedItemsNames.get(i), checkoutItems.get(i).findElement(PageObjectModel.productName).getText());
            Assert.assertEquals(selectedItemsPrices.get(i), checkoutItems.get(i).findElement(PageObjectModel.productPrice).getText());
        }


        driver.findElement(PageObjectModel.checkoutButton).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(PageObjectModel.continueButton));

        driver.findElement(PageObjectModel.firstNameField).sendKeys(PageObjectModel.firstName);
        driver.findElement(PageObjectModel.lastNameField).sendKeys(PageObjectModel.lastName);
        driver.findElement(PageObjectModel.zipField).sendKeys(PageObjectModel.zipCode);

        driver.findElement(PageObjectModel.continueButton).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(PageObjectModel.finishButton));

        List<WebElement> checkoutOverviewItems = driver.findElement(PageObjectModel.checkoutList).findElements(By.className("cart_item"));

        for (int i = 0; i < 3; i++) {
            Assert.assertEquals(selectedItemsNames.get(i), checkoutOverviewItems.get(i).findElement(PageObjectModel.productName).getText());
            Assert.assertEquals(selectedItemsPrices.get(i), checkoutOverviewItems.get(i).findElement(PageObjectModel.productPrice).getText());
        }

        driver.findElement(PageObjectModel.finishButton).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(PageObjectModel.backHomeButton));

        String expectedOrderCompleteText = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

        Assert.assertEquals(driver.findElement(PageObjectModel.completeOrderText).getText(), expectedOrderCompleteText);

        driver.findElement(PageObjectModel.backHomeButton).click();

        Assert.assertEquals(driver.findElement(PageObjectModel.titleHeader).getText(), "Products");

    }
}
