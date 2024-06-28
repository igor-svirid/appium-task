package screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.xpath;

public class SinglePostScreen extends BaseScreen {


    private final WebElement lblPostText;
    private final static By POST_LOC =
            xpath("//android.widget.TextView[ends-with(@resource-id,'/text') and not(contains(@text, 'in reply to'))]");

    public SinglePostScreen(AndroidDriver driver) {
        super(driver, POST_LOC, "Post");
        lblPostText = driver.findElement(POST_LOC);
    }

    public String getPostText() {
        System.out.println("Получаем текст поста");
        return lblPostText.getText();
    }
}
