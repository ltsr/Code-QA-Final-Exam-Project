package swag.labs.utils;

import org.openqa.selenium.By;

public class PageObjectModel {
    public static String username = "standard_user";
    public static String password = "secret_sauce";

    public static String firstName = "Marko";
    public static String lastName = "Petrovic";
    public static String zipCode = "99832";

    public static By usernameField = By.id("user-name");
    public static By passwordField = By.id("password");

    public static By loginButton = By.id("login-button");

    public static By loginErrorMessage = By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[3]/h3");

    public static By titleHeader = By.cssSelector(".title");

    public static By burgerMenuIcon = By.id("react-burger-menu-btn");

    public static By logoutLink = By.id("logout_sidebar_link");

    public static By dropdownMenu = By.xpath("/html/body/div/div/div/div[1]/div[2]/div/span/select");
    public static By dropdownAToZ = By.cssSelector("option[value='az']");
    public static By dropdownZToA = By.cssSelector("option[value='za']");
    public static By dropdownLoHi = By.cssSelector("option[value='lohi']");
    public static By dropdownHiLo = By.cssSelector("option[value='hilo']");

    public static By inventoryList = By.cssSelector(".inventory_list");

    public static By productName = By.className("inventory_item_name");
    public static By productPrice = By.className("inventory_item_price");

    public static By productPos1Button = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/button[1]");

    public static By cartIcon = By.cssSelector(".shopping_cart_link");
    public static By checkoutButton = By.id("checkout");

    public static By checkoutList = By.cssSelector(".cart_list");

    public static By firstNameField = By.id("first-name");
    public static By lastNameField = By.id("last-name");
    public static By zipField = By.id("postal-code");
    public static By continueButton = By.id("continue");

    public static By finishButton = By.id("finish");

    public static By completeOrderText = By.cssSelector(".complete-text");
    public static By backHomeButton = By.id("back-to-products");


}
