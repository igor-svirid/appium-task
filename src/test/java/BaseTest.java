import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static io.appium.java_client.android.options.app.SupportsAppActivityOption.APP_ACTIVITY_OPTION;
import static io.appium.java_client.android.options.app.SupportsAppPackageOption.APP_PACKAGE_OPTION;
import static io.appium.java_client.remote.options.SupportsAppOption.APP_OPTION;
import static io.appium.java_client.remote.options.SupportsAutomationNameOption.AUTOMATION_NAME_OPTION;
import static io.appium.java_client.remote.options.SupportsPlatformVersionOption.PLATFORM_VERSION_OPTION;
import static io.appium.java_client.remote.options.SupportsUdidOption.UDID_OPTION;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

public class BaseTest {

    public static AndroidDriver driver;
    protected final String bundleId = "org.joinmastodon.android";

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        var capabilities = new DesiredCapabilities();
        capabilities.setCapability(PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(PLATFORM_VERSION_OPTION, "13.0");
        capabilities.setCapability(AUTOMATION_NAME_OPTION, AutomationName.ANDROID_UIAUTOMATOR2);
        capabilities.setCapability(UDID_OPTION, "ENTER DEVICE UDID");
        capabilities.setCapability(APP_OPTION, "ENTER PATH TO APP");
        capabilities.setCapability(APP_PACKAGE_OPTION, bundleId);
        capabilities.setCapability(APP_ACTIVITY_OPTION, bundleId + ".MainActivity");

        var url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(url, capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
