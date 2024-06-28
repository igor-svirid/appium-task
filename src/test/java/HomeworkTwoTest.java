import alerts.NotificationsAlert;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.*;

import java.awt.*;

public class HomeworkTwoTest extends BaseTest {
    private final String MASTODON_SOCIAL_SERVER = "mastodon.social";

    @Test
    public void searchItemsTest() {

        Assert.assertEquals(driver.getCurrentPackage(), bundleId, "Приложение не открылось");
        driver.terminateApp(bundleId);
        Assert.assertNotEquals(driver.currentActivity(), bundleId, "Приложение не закрылось");
        driver.activateApp(bundleId);

        login();

        var homeScreen = new HomeScreen(driver);
        Assert.assertTrue(homeScreen.isScreenOpened(), "Экран Home не открыт");
        homeScreen.clickExplore();

        var postsScreen = new PostsScreen(driver);
        Assert.assertTrue(postsScreen.isScreenOpened(), "Экран Posts не открыт");
        var postText = postsScreen.openFirstPostAndGetText();
        var selectedPostScreen = new SinglePostScreen(driver);
        Assert.assertTrue(selectedPostScreen.isScreenOpened(), "Экран Single Post не открыт");
        var openedPostText = selectedPostScreen.getPostText();
        Assert.assertEquals(openedPostText, postText, "Открылся не экран неправильного поста");
    }

    @Test
    public void interactionWithElementsTest() {
        login();
        var homeScreen = new HomeScreen(driver);
        Assert.assertTrue(homeScreen.isScreenOpened(), "Экран Home не открыт");
        homeScreen.clickExplore();

        var postsScreen = new PostsScreen(driver);
        Assert.assertTrue(postsScreen.isScreenOpened(), "Экран Posts не открыт");
        Assert.assertTrue(postsScreen.arePostsDisplayed(), "Не все посты в статусе Displayed");
        var searchTbxInvalidPosition = new Point(0, 0);
        Assert.assertNotEquals(postsScreen.getSearchTbxPosition(), searchTbxInvalidPosition, "Координаты строки поиска (0, 0)");

        var searchText = "tests";
        postsScreen.activateSearch();
        postsScreen.enterActiveSearchText(searchText);
        Assert.assertEquals(postsScreen.getActiveSearchText(), searchText, "Текст не введен в поле поиска");
        postsScreen.clearActiveSearch();
        Assert.assertEquals(postsScreen.getActiveSearchText(), "Search Mastodon", "Поле поиска не очистилось");
    }

    @Test
    public void keyBoardProcessingTest() {
        login();

        var homeScreen = new HomeScreen(driver);
        Assert.assertTrue(homeScreen.isScreenOpened(), "Экран Home не открыт");
        homeScreen.clickExplore();
        var postsScreen = new PostsScreen(driver);
        Assert.assertTrue(postsScreen.isScreenOpened(), "Экран Posts не открыт");
        postsScreen.activateSearch();

        //INFO: закрытие клавиатуры выполнить путем прямого нажатия кнопки поиска на клавиатуре не выполнил,
        //т.к. андроид не дает возможности прямого взаимодействия с клавиатурой
        driver.pressKey(new KeyEvent().withKey(AndroidKey.ENTER));
        var isKeyBoardShown = driver.isKeyboardShown();
        System.out.println("Клавиатура " + (isKeyBoardShown ? "отображается" : " не отображается"));
        if(!isKeyBoardShown) {
            postsScreen.clickSearchTbx();
        }
        driver.hideKeyboard();
        isKeyBoardShown = driver.isKeyboardShown();
        System.out.println("Клавиатура " + (isKeyBoardShown ? "отображается" : " не отображается"));
        if(!isKeyBoardShown) {
            postsScreen.clickSearchTbx();
        }
        pressKeyboardKey(AndroidKey.T);
        pressKeyboardKey(AndroidKey.E);
        pressKeyboardKey(AndroidKey.S);
        pressKeyboardKey(AndroidKey.T);
        Assert.assertEquals(postsScreen.getActiveSearchText(), "test", "Введенные буквы не отображаются");
    }

    private void login() {
        var loginScreen = new LoginScreen(driver);
        Assert.assertTrue(loginScreen.isScreenOpened(), "Экран Login не открыт");
        loginScreen.clickLogin();
        var selectServerScreen = new SelectServerScreen(driver);
        selectServerScreen.enterServer(MASTODON_SOCIAL_SERVER);
        selectServerScreen.selectServer(MASTODON_SOCIAL_SERVER);
        selectServerScreen.clickNext();
        var authorizeScreen = new AuthorizeScreen(driver);
        authorizeScreen.clickAuthorize();
        var notificationsAlert = new NotificationsAlert(driver);
        notificationsAlert.clickAllow();
    }

    private void pressKeyboardKey(AndroidKey key) {
        driver.pressKey(new KeyEvent().withKey(key));
    }
}
