package alerts;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.id;

public class NotificationsAlert {

    private final WebElement btnAllowNotifications;

    public NotificationsAlert(AndroidDriver driver) {
        btnAllowNotifications = driver.findElement(id("com.android.permissioncontroller:id/permission_allow_button"));
    }

    public void clickAllow() {
        btnAllowNotifications.click();
        System.out.println("Нажали кнопку Allow");
    }
}
