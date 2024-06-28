package screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.id;

public class LoginScreen extends BaseScreen {

    private final WebElement btnLogin;
    private final static By LOGIN_LOC = id("btn_log_in");

    public LoginScreen(AndroidDriver driver) {
        super(driver, LOGIN_LOC, "Login");
        btnLogin = driver.findElement(LOGIN_LOC);
    }

    public void clickLogin() {
        btnLogin.click();
        System.out.println("Нажали кнопку Login");
    }
}
