package screens;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.Screen;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import utils.DriverUtils;

import java.util.List;

import static org.openqa.selenium.By.xpath;

@ScreenType(platform = PlatformName.ANDROID)
public class PostsScreen extends Screen {

    private final static By POSTS_AUTHOR_AND_DATE_LOC = xpath(
            "//android.widget.TextView[contains(@resource-id,'time_and_username')]");

    public PostsScreen() {
        super(POSTS_AUTHOR_AND_DATE_LOC, "Posts");
    }

    public boolean isPostByAuthorAndDateDisplayed(String authorAndDateString) {
        return getPostLabelByAuthorAndDate(authorAndDateString).state().isDisplayed();
    }

    public String scrollToPostByNumberAndGetAuthorAndDate(int postNumber) {
        var availablePosts = getAvailablePosts();
        var authorsAndDatesList = new java.util.ArrayList<>(availablePosts.stream().map(ILabel::getText).toList());
        while (authorsAndDatesList.size() < postNumber) {
            DriverUtils.swipeVerticalDirectionWithoutFramework(SwipeDirection.DOWN);
            var newPosts = getAvailablePosts();
            newPosts.forEach(post -> {
                var authorAndDate = post.getText();
                if (!authorsAndDatesList.contains(authorAndDate)) {
                    availablePosts.add(post);
                    authorsAndDatesList.add(authorAndDate);
                }
            });
        }
        var swipeDirection = authorsAndDatesList.size() > postNumber ? SwipeDirection.UP : SwipeDirection.DOWN;
        var requiredPostAuthorAndDate = authorsAndDatesList.get(postNumber - 1);
        getPostLabelByAuthorAndDate(requiredPostAuthorAndDate).getTouchActions().scrollToElement(swipeDirection);
        return requiredPostAuthorAndDate;
    }

    public String getFirstPostAuthorAndDate() {
        return getAvailablePosts().stream().findFirst().orElseThrow().getText();
    }

    public void scrollToPost(String authorAndDateString, SwipeDirection swipeDirection) {
        AqualityServices.getConditionalWait().waitFor(() -> {
            if(!isPostByAuthorAndDateDisplayed(authorAndDateString)){
                DriverUtils.swipeVerticalDirection(swipeDirection);
            }
            return isPostByAuthorAndDateDisplayed(authorAndDateString);
        });
    }

    private List<ILabel> getAvailablePosts() {
        return getElementFactory().findElements(POSTS_AUTHOR_AND_DATE_LOC, "Post", ILabel.class);
    }

    private ILabel getPostLabelByAuthorAndDate(String authorAndDateString) {
        return getElementFactory().getLabel(xpath("//android.widget.TextView[@text='%s']".formatted(authorAndDateString)),
                "Пост от '%s'".formatted(authorAndDateString));
    }
}
