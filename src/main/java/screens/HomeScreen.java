package screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.id;

public class HomeScreen extends BaseScreen{

    private final WebElement btnExplore;
    private final static By EXPLORE_LOC = id("tab_search");

    public HomeScreen(AndroidDriver driver) {
        super(driver, EXPLORE_LOC, "Home Screen");
        btnExplore = driver.findElement(EXPLORE_LOC);
    }

    public void clickExplore() {
        btnExplore.click();
        System.out.println("Нажали кнопку Explore");
    }
}
