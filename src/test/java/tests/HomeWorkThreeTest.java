package tests;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.AqualityServices;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.*;
import screens.alerts.NotificationsAlert;

import static aquality.appium.mobile.application.AqualityServices.getScreenFactory;
import static org.testng.Assert.assertTrue;

public class HomeWorkThreeTest {

    private static final LoginScreen LOGIN_SCREEN = getScreenFactory().getScreen(LoginScreen.class);
    private static final SelectServerScreen SELECT_SERVER_SCREEN = getScreenFactory().getScreen(SelectServerScreen.class);
    private static final AuthorizeScreen AUTHORIZE_SCREEN = getScreenFactory().getScreen(AuthorizeScreen.class);
    private static final NotificationsAlert NOTIFICATIONS_ALERT = getScreenFactory().getScreen(NotificationsAlert.class);
    private static final MainScreen MAIN_SCREEN = getScreenFactory().getScreen(MainScreen.class);
    private static final PostsScreen POSTS_SCREEN = getScreenFactory().getScreen(PostsScreen.class);
    private static final String MASTODON_SOCIAL_SERVER = "mastodon.social";

    @Test
    public void swipesAppiumVisibilityTest() {
        assertTrue(LOGIN_SCREEN.state().waitForDisplayed(), "Экран Welcome не открылся");
        login();
        MAIN_SCREEN.clickExplore();
        assertTrue(POSTS_SCREEN.state().waitForDisplayed(), "Экран Posts не открылся");
        var firstPost = POSTS_SCREEN.getFirstPostAuthorAndDate();
        var requiredPost = POSTS_SCREEN.scrollToPostByNumberAndGetAuthorAndDate(4);
        assertTrue(POSTS_SCREEN.isPostByAuthorAndDateDisplayed(requiredPost), "Пост '%s' не отображен".formatted(requiredPost));
        POSTS_SCREEN.scrollToPost(firstPost, SwipeDirection.UP);
        assertTrue(POSTS_SCREEN.isPostByAuthorAndDateDisplayed(firstPost), "Пост '%s' не отображен".formatted(requiredPost));
        //INFO: Шаг 6 отдельно не реализовывал, т.к. POSTS_SCREEN.scrollToPostByNumberAndGetAuthorAndDate может скролить до указанного поста
        //и использует DriverUtils.swipeVerticalDirectionWithoutFramework, который скроллит без использования акволити
    }

    @Test
    public void contextsTest() {
        assertTrue(LOGIN_SCREEN.state().waitForDisplayed(), "Экран Welcome не открылся");
        login();
        MAIN_SCREEN.clickExplore();
        assertTrue(POSTS_SCREEN.state().waitForDisplayed(), "Экран Posts не открылся");
        var driver = (AndroidDriver) AqualityServices.getApplication().getDriver();
        var currentContext = driver.getContext();
        var all = driver.getContextHandles();
        if (all.size() > 1) {
            driver.context(all.stream().filter(context -> !context.equals(currentContext)).findFirst().orElseThrow());
        }
    }

    private void login() {
        LOGIN_SCREEN.clickLogin();
        SELECT_SERVER_SCREEN.enterServer(MASTODON_SOCIAL_SERVER);
        SELECT_SERVER_SCREEN.selectServer(MASTODON_SOCIAL_SERVER);
        SELECT_SERVER_SCREEN.clickNext();
        AUTHORIZE_SCREEN.clickAuthorize();
        NOTIFICATIONS_ALERT.clickAllow();
        assertTrue(MAIN_SCREEN.state().waitForDisplayed(), "Экран Home не открылся");
    }

    @AfterMethod
    private void tearDown() {
        AqualityServices.getApplication().getDriver().quit();
    }
}
