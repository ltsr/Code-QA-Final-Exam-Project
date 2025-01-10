package swag.labs.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SwagLabsLogin {
    public static void login(WebDriver driver, WebDriverWait wait) {
        driver.findElement(PageObjectModel.usernameField).sendKeys(PageObjectModel.username);
        driver.findElement(PageObjectModel.passwordField).sendKeys(PageObjectModel.password);

        driver.findElement(PageObjectModel.loginButton).click();
    }
}
