package screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.xpath;

public class AuthorizeScreen extends BaseScreen{

    private final WebElement btnAuthorize;
    private final static By AUTHORIZE_LOC = xpath("//android.widget.Button[@text='Authorize']");

    public AuthorizeScreen(AndroidDriver driver) {
        super(driver, AUTHORIZE_LOC, "Authorize");
        btnAuthorize = driver.findElement(AUTHORIZE_LOC);
    }

    public void clickAuthorize() {
        btnAuthorize.click();
        System.out.println("Нажали кнопку Authorize");
    }
}
