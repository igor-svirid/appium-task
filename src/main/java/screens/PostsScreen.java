package screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.By.*;

public class PostsScreen extends BaseScreen {

    private final static By POSTS_LOC =
            xpath("//*[./android.widget.TextView[ends-with(@resource-id,'/text')] or ./android.widget.ImageView[ends-with(@resource-id,'/photo')]]");

    public PostsScreen(AndroidDriver driver) {
        super(driver, POSTS_LOC, "Posts");
    }

    public String openFirstPostAndGetText() {
        var topPost = getDisplayedPosts().stream().findFirst().orElseThrow(() -> new NotFoundException("No posts were found"));
        var postText = topPost.findElement(xpath("//android.widget.TextView")).getText();
        topPost.click();
        System.out.println("Открыли первый пост из списка");
        return postText;
    }

    public boolean arePostsDisplayed() {
        System.out.println("Проверяем отображаются ли посты");
        return getDisplayedPosts().stream().allMatch(WebElement::isDisplayed);
    }

    public Point getSearchTbxPosition() {
        System.out.println("Получаем координаты строки поиска");
        return getSearchField().getLocation();
    }

    public void enterActiveSearchText(String text) {
        getTbxSearch().sendKeys(text);
        System.out.println("Вводим текст '%s'".formatted(text));
    }

    public String getActiveSearchText() {
        System.out.println("Получаем текст строки поиска");
        return getTbxSearch().getText();
    }

    public void clearActiveSearch() {
        getTbxSearch().clear();
        System.out.println("Очистили строку поиска");
    }

    public void activateSearch() {
        getSearchField().click();
        System.out.println("Открываем меню поиска");
    }

    public void clickSearchTbx() {
        getTbxSearch().click();
        System.out.println("Нажимаем на поле поиска");
    }

    private List<WebElement> getDisplayedPosts() {
        return driver.findElements(POSTS_LOC);
    }

    private WebElement getTbxSearch() {
        return driver.findElement(className("android.widget.EditText"));
    }

    private WebElement getSearchField() {
        return driver.findElement(id("search_text"));
    }
}
