package screens.alerts;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.Screen;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.id;

@ScreenType(platform = PlatformName.ANDROID)
public class NotificationsAlert extends Screen {

    private static final By ALLOW_LOC = id("com.android.permissioncontroller:id/permission_allow_button");
    private final IButton btnAllowNotifications = getElementFactory().getButton(ALLOW_LOC, "Allow");

    public NotificationsAlert() {
        super(ALLOW_LOC, "Notification Alert");
    }

    public void clickAllow() {
        btnAllowNotifications.click();
    }
}
