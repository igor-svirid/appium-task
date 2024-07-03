package screens;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import aquality.appium.mobile.screens.Screen;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

@ScreenType(platform = PlatformName.ANDROID)
public class SelectServerScreen extends Screen {

    private final static By SEARCH_LOC = id("search_edit");
    private final ITextBox tbxSearch = getElementFactory().getTextBox(SEARCH_LOC, "Search");
    private final IButton btnNext = getElementFactory().getButton(id("btn_next"), "Next");

    public SelectServerScreen() {
        super(SEARCH_LOC, "Main Screen");
    }

    public void enterServer(String serverName) {
        tbxSearch.sendKeys(serverName);
    }

    public void selectServer(String serverName) {
        getElementFactory().getLabel(xpath("//android.widget.TextView[@text='%s']".formatted(serverName)), serverName)
                .click();
    }

    public void clickNext() {
        btnNext.click();
    }
}
