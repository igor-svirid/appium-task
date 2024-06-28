package screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BaseScreen {
    protected final AndroidDriver driver;
    private final WebElement screenElement;
    private final String name;

    public BaseScreen(AndroidDriver driver, By locator, String screenName) {
        this.driver = driver;
        screenElement = driver.findElement(locator);
        name = screenName;
    }

    public boolean isScreenOpened() {
        System.out.println("Проверяем открыт ли экран '%s'".formatted(name));
        return screenElement.isDisplayed();
    }
}
