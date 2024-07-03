package screens;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.Screen;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.id;

@ScreenType(platform = PlatformName.ANDROID)
public class LoginScreen extends Screen {

    private final IButton btnLogin = getElementFactory().getButton(LOGIN_LOC, "Login");
    private final static By LOGIN_LOC = id("btn_log_in");

    public LoginScreen() {
        super(LOGIN_LOC, "Login");
    }

    public void clickLogin() {
        btnLogin.click();
    }
}
