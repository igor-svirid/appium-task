package screens;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.Screen;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.xpath;

@ScreenType(platform = PlatformName.ANDROID)
public class AuthorizeScreen extends Screen {

    private final static By AUTHORIZE_LOC = xpath("//android.widget.Button[@text='Authorize']");
    private final IButton btnAuthorize = getElementFactory().getButton(AUTHORIZE_LOC, "Authorize");

    public AuthorizeScreen() {
        super(AUTHORIZE_LOC, "Authorize");
    }

    public void clickAuthorize() {
        btnAuthorize.click();
    }
}
