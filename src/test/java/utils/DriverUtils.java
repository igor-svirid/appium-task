package utils;


import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.AqualityServices;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;

public class DriverUtils {

    public static void swipeVerticalDirection(SwipeDirection swipeDirection) {
        var screenSize = AqualityServices.getApplication().getDriver().manage().window().getSize();
        var screenWidthMiddle = screenSize.getWidth() / 2;
        var screenHeight = screenSize.getHeight();
        var bottomPoint = new Point(screenWidthMiddle, screenHeight - screenHeight / 5);
        var topPoint = new Point(screenWidthMiddle, screenHeight - (screenHeight / 5) * 4);
        switch (swipeDirection) {
            case UP -> AqualityServices.getTouchActions().swipe(topPoint, bottomPoint);
            case DOWN -> AqualityServices.getTouchActions().swipe(bottomPoint, topPoint);
            default -> throw new UnsupportedOperationException("Horizontal swipe not supported");
        }
    }

    public static void swipeVerticalDirectionWithoutFramework(SwipeDirection swipeDirection) {
        var screenSize = AqualityServices.getApplication().getDriver().manage().window().getSize();
        var screenWidthMiddle = screenSize.getWidth() / 2;
        var screenHeight = screenSize.getHeight();
        var bottomPoint = new Point(screenWidthMiddle, screenHeight - screenHeight / 5);
        var topPoint = new Point(screenWidthMiddle, screenHeight - (screenHeight / 5) * 4);

        switch (swipeDirection) {
            case UP -> swipe(topPoint, bottomPoint);
            case DOWN -> swipe(bottomPoint, topPoint);
            default -> throw new UnsupportedOperationException("Horizontal swipe not supported");
        }
    }

    private static void swipe(Point startPoint, Point endPoint) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var sequence = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startPoint))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endPoint))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        AqualityServices.getApplication().getDriver().perform(Collections.singletonList(sequence));
    }
}
