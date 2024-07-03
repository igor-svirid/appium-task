package screens;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.Screen;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.id;

@ScreenType(platform = PlatformName.ANDROID)
public class MainScreen extends Screen {

    private final static By EXPLORE_LOC = id("tab_search");
    private final IButton btnExplore = getElementFactory().getButton(EXPLORE_LOC, "Explore");

    public MainScreen() {
        super(EXPLORE_LOC, "Welcome Screen");
    }

    public void clickExplore() {
        btnExplore.click();
    }
}
