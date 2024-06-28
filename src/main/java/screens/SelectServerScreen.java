package screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class SelectServerScreen extends BaseScreen{
    private final WebElement tbxSearch;
    private final WebElement btnNext;
    private final static By SEARCH_LOC =id("search_edit");

    public SelectServerScreen(AndroidDriver driver) {
        super(driver, SEARCH_LOC, "Main Screen");
        tbxSearch = driver.findElement(SEARCH_LOC);
        btnNext = driver.findElement(id("btn_next"));
    }

    public void enterServer(String serverName) {
        tbxSearch.sendKeys(serverName);
        System.out.println("Ввели название сервера '%s'".formatted(serverName));
    }

    public void selectServer(String serverName) {
        var server = driver.findElement(xpath("//android.widget.TextView[@text='%s']".formatted(serverName)));
        server.click();
        System.out.println("Нажали на сервер '%s'".formatted(serverName));
    }

    public void clickNext() {
        btnNext.click();
        System.out.println("Нажали кнопку Next");
    }
}
